package com.verificationapplication.poc.repositories;

import com.verificationapplication.poc.dataobjects.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
