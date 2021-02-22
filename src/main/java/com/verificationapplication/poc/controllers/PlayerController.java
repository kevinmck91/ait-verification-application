package com.verificationapplication.poc.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.Team;
import com.verificationapplication.poc.dataobjects.VerificationInput;
import com.verificationapplication.poc.repositories.PlayerRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@DeleteMapping("players/{membershipId}")
	public void deletePlayer(@PathVariable Optional<Integer> membershipId){
		List<Player> thePlayer = playerRepository.findByMembershipId(membershipId);
		if(thePlayer.size() == 1){
			playerRepository.deleteById(thePlayer.get(0).getId());
			System.out.println("Player with membershipId [" + membershipId + "] Removed from Player Table");
		}else{
			System.out.println("Player for membershipId [" + membershipId + "] Does not exist");
		}
	}

	@PostMapping("players/")
	public ResponseEntity createPlayer(@RequestBody Player newPlayer){
		playerRepository.save(newPlayer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").query("membershipId={keyword}").buildAndExpand(newPlayer.getMembershipId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
