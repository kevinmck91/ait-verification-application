package com.verificationapplication.poc.repositories;

import com.verificationapplication.poc.dataobjects.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findByName(Optional<String> name);
    List<Team> findByLocation(Optional<String> location);
    List<Team> findByClubId(Optional<String> clubId);

}
