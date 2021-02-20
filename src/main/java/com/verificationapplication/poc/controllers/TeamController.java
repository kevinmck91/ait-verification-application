package com.verificationapplication.poc.controllers;
import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.Team;
import com.verificationapplication.poc.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("teams")
    public List<Team> getData(@RequestParam("name") Optional<String> name,
                              @RequestParam("location") Optional<String> location,
                              @RequestParam("clubId") Optional<String> clubId) {
        if(name.isPresent()){
            return teamRepository.findByName(name);
        } else if (location.isPresent()) {
            return teamRepository.findByLocation(location);
        } else if (clubId.isPresent()) {
            return teamRepository.findByClubId(clubId);
        } else {
            return teamRepository.findAll();
        }
    }


}