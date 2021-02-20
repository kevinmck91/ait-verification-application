package com.verificationapplication.poc.controllers;
import com.verificationapplication.poc.dataobjects.Team;
import com.verificationapplication.poc.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {


    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("teams")
    public List<Team> getAllTeam() {

        List<Team> team = teamRepository.findAll();

        return teamRepository.findAll();

    }

}