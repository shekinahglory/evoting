package com.cogent.controllers;


import com.cogent.backend.domains.User;
import com.cogent.services.VotersService;
import com.cogent.services.VotingService;
import com.cogent.configuration.MessageResponse;
import com.cogent.utils.VoterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class VoterController {

    @Autowired
    private VotersService votersService;

    @Autowired
    private VotingService votingService;

    @PostMapping("/register-voter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> register(@RequestBody User voter){

        String response =   votersService.createVoter(voter);
        return ResponseEntity.ok(new MessageResponse(response));

    }

    @PostMapping("/vote-president")
    public String vote(@RequestBody VoterRequest vote){
        String res = votingService.vote(vote.getCandName(), vote.getVoterName(), "Presidential");
        return res;
    }

    @GetMapping("/voter")
    public User getVoterById(@RequestParam int id){
        User voter = votersService.findVoterById(id);
        return voter;
    }

    @GetMapping("/deleteOneVoter")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteVoterByHisId(@RequestParam long id){
        User voter = votersService.findVoterById(id);
        String message = votersService.deleteVoterByHisId(id);
        return message;
    }

    @GetMapping("/voter-by-state")
    public List<User> getVotersByState(@RequestParam String state){
        List<User> stateVoters = votersService.findAllVoterByState(state);
        return  stateVoters;
    }

    @GetMapping("/voter-by-city")
    public List<User> getVotersByCity(@RequestParam String city){
        List<User> stateVoters = votersService.findAllVoterByCity(city);
        return  stateVoters;
    }
    @GetMapping("/deleteAllVoters")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAllVoters(){
        String message =  votersService.deleteAllVoter();
        return message;
    }

    @GetMapping("/voters")
    public List<User> getAllVoters(){
        List<User> voters = votersService.findAllVoter();
        return voters;
    }



}
