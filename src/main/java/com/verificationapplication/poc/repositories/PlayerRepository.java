package com.verificationapplication.poc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verificationapplication.poc.dataobjects.Player;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByFirstname(Optional<String> firstname);

    List<Player> findByMembershipId(Optional<Integer> membershipId);

    List<Player> findByLastname(String lastname);

    List<Player> findByClubIdAndAgeGroup(int clubId, int ageGroup);

	
}
