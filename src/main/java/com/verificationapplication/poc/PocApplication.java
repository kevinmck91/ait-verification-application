package com.verificationapplication.poc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.repositories.PlayerRepository;

@SpringBootApplication
public class PocApplication implements CommandLineRunner {
	
	@Autowired
    private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}
	
	public void run(String... args) {
		
		// Add all players to the database manually 
		// TODO convert this to a script
		playerRepository.save(new Player(123, "firstname", "lastname", "dateOfBirth", "image", "qrCode", 123, true, 21));
		playerRepository.save(new Player(321, "firstname", "lastname", "dateOfBirth", "image", "qrCode", 123, true, 21));
		playerRepository.save(new Player(456, "firstname", "lastname", "dateOfBirth", "image", "qrCode", 123, true, 21));

	}
}
