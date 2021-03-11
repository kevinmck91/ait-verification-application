package com.verificationapplication.poc.controllers;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.verificationapplication.poc.dataobjects.FailedVerificationReasons;
import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.VerificationInput;
import com.verificationapplication.poc.dataobjects.VerificationOutput;
import com.verificationapplication.poc.qr_utils.ReadQRCode;
import com.verificationapplication.poc.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
	
}
