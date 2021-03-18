package com.verificationapplication.poc.controllers;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.transform.CompareFacesResultJsonUnmarshaller;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.verificationapplication.poc.dataobjects.FailedVerificationReasons;
import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.VerificationInput;
import com.verificationapplication.poc.dataobjects.VerificationOutput;
import com.verificationapplication.poc.qr_utils.ReadQRCode;
import com.verificationapplication.poc.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilderFactory;


import java.io.File;
import java.io.IOException;
import java.util.*;

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
	
	@PostMapping("/qrcode")
	public String qrDecoder(@RequestBody String input) {
		
		String result = "";
		
		try {
			
			result = ReadQRCode.readQRCode(input);
		
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//TODO: Call the 'Get Player method' using the QRcode result and return a player instead of a string
		
		return result;
	}


	@PostMapping("faceCompare")
	public ResponseEntity<Object> compareFaces(@RequestParam("image1") MultipartFile multipartFile1, @RequestParam("image2") MultipartFile multipartFile2){
		String url = "http://18.205.162.30:8082/faceCompare";
		RestTemplate restTemplate = new RestTemplate();

		Resource invoicesResource1 = multipartFile1.getResource();
		Resource invoicesResource2 = multipartFile2.getResource();
		LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("image1", invoicesResource1);
		parts.add("image2", invoicesResource2);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, httpHeaders);

		//ResponseEntity<CompareFacesResult> result = restTemplate.postForEntity(url, httpEntity, CompareFacesResult.class);
		ResponseEntity<Object> result = restTemplate.postForEntity(url, httpEntity, Object.class);

	//	CompareFacesResult result1 = new CompareFacesResult();
		System.err.println(result.getBody());
		String body = result.getBody();
		ResponseMetadata responseMetadata = new ResponseMetadata(result.getBody());


		return result;
	}




	}
