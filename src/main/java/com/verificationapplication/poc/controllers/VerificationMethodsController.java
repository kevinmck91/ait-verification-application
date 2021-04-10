package com.verificationapplication.poc.controllers;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.verificationapplication.poc.dataobjects.Player;
import com.verificationapplication.poc.dataobjects.PlayerInputData;
import com.verificationapplication.poc.repositories.PlayerRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class VerificationMethodsController {
    final Logger log = LoggerFactory.getLogger(VerificationMethodsController.class);

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/method1")
    public boolean verifyMembershipId(@RequestBody PlayerInputData playerInputData){
        log.info("Method 1: Verifying the player's membership ID");
        log.info("Incoming Data : "+ playerInputData.toString());

        Player playerDetails = playerRepository.findByMembershipIdAndClubIdAndAgeGroup(playerInputData.getMembershipId(),
                playerInputData.getClubId(), playerInputData.getAgeGroup());

        if(playerDetails != null){
            log.info("Player has successfully PASSED verification for MembershipID:  For given membershipId, clubId and ageGroup");
            return true;
        }else{
            log.info("Player has FAILED verification for MembershipID:  For given membershipId, clubId and ageGroup");
            return false;
        }
    }

    @PostMapping("/method2")
    public boolean verifyActiveMembership(@RequestBody PlayerInputData playerInputData){
        log.info("Method 2: Verifying the player's membership status");
        log.info("Incoming Data : "+ playerInputData.toString());

        Player playerDetails = playerRepository.findByMembershipIdAndClubIdAndAgeGroup(playerInputData.getMembershipId(),
                playerInputData.getClubId(), playerInputData.getAgeGroup());

        if(playerDetails != null){
            if(playerDetails.isActiveMembership()){
                log.info("Player has successfully PASSED verification for Membership Status:  For given membershipId, clubId and ageGroup");
                return true;
            }else{
                log.info("Player has FAILED verification for Membership Status:  For given membershipId, clubId and ageGroup");
                return false;
            }
        }else{
            log.info("Player has FAILED verification for Membership Status:  For given membershipId, clubId and ageGroup");
            return false;
        }
    }

    @PostMapping("/method3")
    public boolean verifyAge(@RequestBody PlayerInputData playerInputData){
        log.info("Method 3: Verifying the player's age");
        log.info("Incoming Data : "+ playerInputData.toString());

        Player playerDetails = playerRepository.findByMembershipIdAndClubIdAndAgeGroup(playerInputData.getMembershipId(),
                playerInputData.getClubId(), playerInputData.getAgeGroup());

        if(playerDetails != null){
            if(playerDetails.getAgeGroup() >= playerInputData.getAgeGroup()){
                log.info("Player has successfully PASSED verification for Age Group:  For given membershipId, clubId and ageGroup");
                return true;
            }else{
                log.info("Player has FAILED verification for Age Group:  For given membershipId, clubId and ageGroup");
                return false;
            }
        }else{
            log.info("Player has FAILED verification for Age Group:  For given membershipId, clubId and ageGroup");
            return false;
        }
    }

    @PostMapping("/method4/{ageGroup}")
    public boolean verifyQRCode(@RequestParam("qrcodeimage") MultipartFile multipartFile, @PathVariable int ageGroup){
        log.info("Method 4: Verifying the player by QR Code");
        PlayerInputData playerInputData = new PlayerInputData();
        playerInputData.setAgeGroup(ageGroup);

        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(ImageIO.read(multipartFile.getInputStream()))));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            String[] qrCodeData = result.getText().split(";");
            playerInputData.setMembershipId(Integer.parseInt(qrCodeData[0]));
            playerInputData.setClubId(Integer.parseInt(qrCodeData[1]));
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }

        log.info("Incoming Data : " + playerInputData.toString());

        Player playerDetails = playerRepository.findByMembershipIdAndClubIdAndAgeGroup(playerInputData.getMembershipId(),
                playerInputData.getClubId(), playerInputData.getAgeGroup());

        if(playerDetails != null){
            if((playerDetails.getAgeGroup() >= playerInputData.getAgeGroup()) && (playerDetails.isActiveMembership())){
                log.info("Player has successfully PASSED verification for QR Code:  For given membershipId, clubId and ageGroup");
                return true;
            }else{
                log.info("Player has FAILED verification for QR Code:  For given membershipId, clubId and ageGroup");
                return false;
            }
        }else{
            log.info("Player has FAILED verification for QR Code:  For given membershipId, clubId and ageGroup");
            return false;
        }
    }

    @PostMapping("/method5/{ageGroup}/{clubId}")
    public boolean verifyFacialRecognition(@RequestParam("playerPhoto") MultipartFile multipartFile, @PathVariable int ageGroup, @PathVariable int clubId){
        log.info("Method 5: Verifying the player by Facial Recognition");
        String url = "http://18.205.162.30:8082/faceCompare";
        //String url = "http://127.0.0.1:8080/faceCompare1";

        //Get all images for Club ID for Given Year
        List<Player> playersPhotosForClub = playerRepository.findByClubIdAndAgeGroup(clubId, ageGroup);

        ArrayList<Player> playersThatMatch = new ArrayList<>();
        int count = 0;

        for (Player player : playersPhotosForClub) {
            try {
                String filename = "src/main/resources/targetFile"+count+".png";
                File file = new File(filename);
                FileUtils.copyInputStreamToFile(player.getImage().getBinaryStream(), file);
                FileInputStream input = new FileInputStream(file);
                MultipartFile playerPhotoFromDatabase = new MockMultipartFile("file", file.getName(), "image/png", IOUtils.toByteArray(input));

                Resource playerPhotoFromDatabaseResource = playerPhotoFromDatabase.getResource();
                Resource inputPhotoToCheck = multipartFile.getResource();

                LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
                parts.add("image1", inputPhotoToCheck);
                parts.add("image2", playerPhotoFromDatabaseResource);

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object> result = null;

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
                HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, httpHeaders);

                result = restTemplate.postForEntity(url, httpEntity, Object.class);
                LinkedHashMap linkedHashMap = (LinkedHashMap) result.getBody();
                LinkedHashMap sourceImageFace = (LinkedHashMap) linkedHashMap.get("sourceImageFace");
                Double confidence = (Double) sourceImageFace.get("confidence");

                ArrayList faceMatches = (ArrayList) linkedHashMap.get("faceMatches");
                if (faceMatches.size() != 0) {
                    LinkedHashMap values = (LinkedHashMap) faceMatches.get(0);
                    Double sim = (Double) values.get("similarity");
                    log.info("SUCCESS: We do have a match, with Similarity Score of "+ sim);
                    Player playerMatch = new Player();
                    playerMatch.setMembershipId(player.getMembershipId());
                    playerMatch.setSimScore(sim);
                    playerMatch.setActiveMembership(player.isActiveMembership());
                    playerMatch.setAgeGroup(player.getAgeGroup());
                    playersThatMatch.add(playerMatch);
                } else {
                    log.info("FAILED: We do not have a match");
                   // PlayerMatch playerMatch = new PlayerMatch(player.getMembershipId(), false,0);
                   // playersThatMatch.add(playerMatch);
                }
            } catch (IOException | SQLException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("Remote Server is down");
            }
            count++;
        }

        if(playersThatMatch.size() != 0){
            Player thePlayer = playersThatMatch.get(0);
            if((thePlayer.getAgeGroup() >= ageGroup) && (thePlayer.isActiveMembership())){
                log.info("Player has successfully PASSED verification for Facial Recognition:  For given membershipId, clubId and ageGroup");
                return true;
            }else{
                log.info("Player has FAILED verification for Facial Recognition:  For given photo related membershipId, clubId and ageGroup");
                return false;
            }
        }else{
            log.info("Player has FAILED verification for Facial Recognition:  For given photo related membershipId, clubId and ageGroup");
            return false;
        }
    }



}//end of program
