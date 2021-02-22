package com.verificationapplication.poc.controllers;

import com.verificationapplication.poc.dataobjects.VerificationInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @PostMapping("/teamVerification")
    public Boolean teamVerification(@RequestBody VerificationInput input) {

        System.out.println(input.toString());

        //Use Case 1:

        //Use Case 2:

        //Use Case 3:

        //Define the verificationOutput object model to return

        return false;

    }
}
