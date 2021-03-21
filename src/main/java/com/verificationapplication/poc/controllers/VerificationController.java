package com.verificationapplication.poc.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.verificationapplication.poc.dataobjects.FailedVerificationReasons;
import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.PlayerResponseQrCode;
import com.verificationapplication.poc.dataobjects.VerificationInput;
import com.verificationapplication.poc.dataobjects.VerificationOutput;
import com.verificationapplication.poc.repositories.PlayerRepository;

@RestController
public class VerificationController {

	@Autowired
	private PlayerRepository playerRepository;

	@PostMapping("/teamVerification")
	public VerificationOutput teamVerification(@RequestBody VerificationInput input) {

		VerificationOutput verificationOutput = new VerificationOutput();
		boolean allPlayersValid = true;

		for (Player player : input.getPlayers()) {

			FailedVerificationReasons failedVerificationReasons = new FailedVerificationReasons();
			boolean playerValid = true;

			// Verification of entire team on the membership Id
			List<Player> thePlayer = playerRepository.findByMembershipId(Optional.of(player.getMembershipId()));

			if (thePlayer.size() == 0) {

				allPlayersValid = false;
				playerValid = false;
				failedVerificationReasons.setFirstname(player.getFirstname());
				failedVerificationReasons.setLastname(player.getLastname());
				failedVerificationReasons.setId(-1);
				failedVerificationReasons.setReasons("Membership ID does not exist");

			}

			// Use Case 2: Verification of entire team on the membership active status

			if (thePlayer.size() == 0) {
				failedVerificationReasons.setReasons("Membership is not active");
			} else {

				if (thePlayer.get(0).isActiveMembership() == false) {
					failedVerificationReasons.setReasons("Membership is not active");
					playerValid = false;
					allPlayersValid = false;
					failedVerificationReasons.setFirstname(player.getFirstname());
					failedVerificationReasons.setLastname(player.getLastname());
					failedVerificationReasons.setId(thePlayer.get(0).getMembershipId());
				}

			}

			// Use Case 3: Verfication of entire team on the date of birth

			if (thePlayer.size() != 0) {

				if ((thePlayer.get(0).getDateOfBirth().getYear() + 1900) < input.getAgeGroup()) {
					failedVerificationReasons.setReasons("Player is older than age group");
					playerValid = false;
					allPlayersValid = false;
					failedVerificationReasons.setFirstname(player.getFirstname());
					failedVerificationReasons.setLastname(player.getLastname());
					failedVerificationReasons.setId(thePlayer.get(0).getMembershipId());
				}

			}

			if (!playerValid) {
				verificationOutput.setVerificationIssues(failedVerificationReasons);
			}

		}

		verificationOutput.setAllPlayersValid(allPlayersValid);
		return verificationOutput;

	}

	@PostMapping("faceCompare")
	public ResponseEntity<Object> compareFaces(@RequestParam("image1") MultipartFile multipartFile1,
			@RequestParam("image2") MultipartFile multipartFile2) {
		String url = "http://18.205.162.30:8082/faceCompare";
		// http://127.0.0.1:8080/faceCompare
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> result = null;

		Resource invoicesResource1 = multipartFile1.getResource();
		Resource invoicesResource2 = multipartFile2.getResource(); // TODO: Get from Database for Player
		LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("image1", invoicesResource1);
		parts.add("image2", invoicesResource2);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, httpHeaders);

		try {
			result = restTemplate.postForEntity(url, httpEntity, Object.class);
			LinkedHashMap linkedHashMap = (LinkedHashMap) result.getBody();
			LinkedHashMap sourceImageFace = (LinkedHashMap) linkedHashMap.get("sourceImageFace");
			Double confidence = (Double) sourceImageFace.get("confidence");

			ArrayList faceMatches = (ArrayList) linkedHashMap.get("faceMatches");
			if (faceMatches.size() != 0) {
				LinkedHashMap values = (LinkedHashMap) faceMatches.get(0);
				Double sim = (Double) values.get("similarity");
				System.err.println("Does match : Sim Score = " + sim);
			} else {
				System.err.println("Does not match");
			}
		} catch (Exception e) {
			System.err.println("Remote Server is down");
		}

		return result;
	}

	@PostMapping("/qrcode")
	public PlayerResponseQrCode qrDecoder(@RequestParam("qrcodeimage") MultipartFile multipartFile1) {

		Optional<Integer> membershipId;
		Result result = null;
		
		try {
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
					new BufferedImageLuminanceSource(ImageIO.read(multipartFile1.getInputStream()))));
			result = new MultiFormatReader().decode(binaryBitmap);

			System.out.println(result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

		membershipId = Optional.ofNullable(Integer.parseInt(result.getText()));
		
		List<Player> players = playerRepository.findByMembershipId(membershipId);
		PlayerResponseQrCode playerResponse = new PlayerResponseQrCode();

		if(players.size() != 0 ) {
			
			playerResponse.setClubId(players.get(0).getClubId());
			playerResponse.setFirstname(players.get(0).getFirstname());
			playerResponse.setLastname(players.get(0).getLastname());
			playerResponse.setYearOfBirth(players.get(0).getDateOfBirth().getYear());
			
		} else {
			//TODO
		}

		return playerResponse;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}


}
