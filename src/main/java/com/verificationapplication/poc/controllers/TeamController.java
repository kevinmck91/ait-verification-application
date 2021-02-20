package com.verificationapplication.poc.controllers;
import com.verificationapplication.poc.dataobjects.Team;
import com.verificationapplication.poc.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @DeleteMapping("teams/{clubId}")
    public void deleteTeam(@PathVariable Optional<String> clubId){
        List<Team> theTeam = teamRepository.findByClubId(clubId);
        if(theTeam.size() == 1){
            teamRepository.deleteById(theTeam.get(0).getId());
            System.out.println("Team for ClubId [" + clubId + "] Removed from Team Table");
        }else{
            System.out.println("Team for ClubId [" + clubId + "] Does not exist");
        }
    }

    @PostMapping("teams/")
    public ResponseEntity createTeam(@RequestBody Team newTeam){
        teamRepository.save(newTeam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").query("clubId={keyword}").buildAndExpand(newTeam.getClubId()).toUri();
        return ResponseEntity.created(location).build();
    }


}