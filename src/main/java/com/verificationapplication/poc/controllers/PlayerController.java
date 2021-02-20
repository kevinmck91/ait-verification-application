package com.verificationapplication.poc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.verificationapplication.poc.dataobjects.Player;
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
}
