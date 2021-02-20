package com.verificationapplication.poc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verificationapplication.poc.dataobjects.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	//Player find 
	
}
