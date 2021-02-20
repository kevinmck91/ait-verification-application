package com.verificationapplication.poc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.Team;
import com.verificationapplication.poc.dataobjects.VerificationInput;
import com.verificationapplication.poc.repositories.PlayerRepository;

@RestController
public class PlayerController {

	@Autowired
    private PlayerRepository playerRepository;
	private String membershipId;

	@GetMapping("players")
	public List<Player> getData(@RequestParam("firstname") Optional<String> firstname, @RequestParam("membershipId") Optional<Integer> membershipId) {
		if(firstname.isPresent()) {
			return playerRepository.findByFirstname(firstname);
		} else if (membershipId.isPresent()) {
			return playerRepository.findByMembershipId(membershipId);
		} else {
			return playerRepository.findAll();
		}
	}
	
	
	/*
	 
	 	http://localhost:8080/verify_player_by_image
	 
	 	{
			"membershipId":123,
			"image":"This will return false"
		}
		
			
		{
			"membershipId":123,
			"image":"myimage.jpeg"
		}
			
	 */
	
	@PostMapping("/verify_player_by_image")
	public Boolean verifyPlayerByImage(@RequestBody VerificationInput input) {
		
		// Capture the inputs
		int inputMembershipId = input.getMembershipId();
		String inputImage = input.getImage();
		
		// Get all the players, loop over them and check if input id/image match
		List<Player> listOfPlayers =  playerRepository.findAll();
		
		boolean result = false;
		
		for (Player player : listOfPlayers) {
			
			if(player.getMembershipId() == inputMembershipId) {
				
				if(player.getImage().equals(inputImage)) {
					
					result = true;

				}
				
			}
	
		}
		
		System.out.println(result);
		
		return result;
		

	}
}
