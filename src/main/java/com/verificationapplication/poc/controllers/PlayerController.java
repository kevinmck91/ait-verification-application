package com.verificationapplication.poc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.repositories.PlayerRepository;

@RestController
public class PlayerController {

	
	@Autowired
    private PlayerRepository playerRepository;

	@GetMapping("players")
	public List<Player> getAllPlayers() {

		List<Player> players = playerRepository.findAll();
		
		return playerRepository.findAll();

	}
	
}
