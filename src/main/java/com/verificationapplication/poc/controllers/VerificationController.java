package com.verificationapplication.poc.controllers;

import com.sun.codemodel.internal.JForEach;
import com.verificationapplication.poc.dataobjects.FailedVerificationReasons;
import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.VerificationInput;
import com.verificationapplication.poc.dataobjects.VerificationOutput;
import com.verificationapplication.poc.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class VerificationController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/teamVerification")
    public VerificationOutput teamVerification(@RequestBody VerificationInput input) {

        VerificationOutput verificationOutput = new VerificationOutput();
        boolean allPlayersValid = true;

        for(Player player : input.getPlayers()){
            FailedVerificationReasons failedReasons = new FailedVerificationReasons();
            boolean playerValid = true;

            //Use Case 1: Send in a list of players for a team and check that they their membership id exists in our database
             List<Player> thePlayer = playerRepository.findByMembershipId(Optional.of(player.getMembershipId()));
             if(thePlayer.size() == 0){
                 allPlayersValid = false;
                 playerValid = false;
                 failedReasons.setFirstname("Joe");
                 failedReasons.setLastname("Bloggs");
                 failedReasons.setId(-1);

                 System.out.println("Player does not exist");
             }else{
                 System.out.println("Player exists");
             }

            //Use Case 2:

            //Use Case 3:

            if(!playerValid){
                verificationOutput.setVerificationIssues(failedReasons);
            }

        }

        verificationOutput.setAllPlayersValid(allPlayersValid);
        return verificationOutput;

    }
}
