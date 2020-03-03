package com.cogent.controllers;


import com.cogent.backend.domains.Candidate;
import com.cogent.backend.domains.Elections;
import com.cogent.configuration.MessageResponse;
import com.cogent.services.CandidateServices;
import com.cogent.services.ElectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class CandidatesController {

     @Autowired
     private CandidateServices candidateServices;

     @Autowired
     private ElectionsService electionsService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> register(@RequestBody  Candidate candidate){

        String response =    candidateServices.createCandidate(candidate);
          return ResponseEntity.ok(new MessageResponse(response));
      }
    @GetMapping("/candidate")
    public Candidate getCandidates(@RequestParam int id){
        Candidate candidate = candidateServices.findCandidateById(id);
        return candidate;
    }

    @GetMapping("/presidential-result")
    public Candidate getPresidentialResult(){
        Candidate candidate = candidateServices.getPresidentialResult();
        return candidate;
    }

    @GetMapping("/deleteOneCandidate")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCandidate(@RequestParam int id){
        Candidate candidate = candidateServices.findCandidateById(id);
        Elections elections = electionsService.findAllPresidentialCandidates();
        Set<Candidate> candidates = new HashSet<>();
        candidates.addAll(elections.getCandidate());
        if (candidates.contains(candidate)){
            elections.getCandidate().remove(candidate);

        }
            String message = candidateServices.deleteCandidate(id, candidate.getName());
            return message;

    }

    @GetMapping("/city-state-candidate/{state}/{city}")
    public List<Candidate> getCandidatesByStateAndCity(@PathVariable String state, @PathVariable String city){
        List<Candidate> cityCandidates = candidateServices.findAllCandidateByStateAndCity(city, state);
        return cityCandidates;
    }

    @GetMapping("/candidate-by-state")
    public List<Candidate> getCandidatesByState(@RequestParam String state){
          List<Candidate> stateCandidates = candidateServices.findAllCandidateByState(state);
          return  stateCandidates;
    }
    @RequestMapping(value = "/candidate-by-state-election/{state}/{electionType}", method = RequestMethod.GET)
    public List<Candidate> getCandidatesByStateAndElectionType(@PathVariable String state, @PathVariable String electionType){
        List<Candidate> stateCandidates = candidateServices.findAllCandidateByStateAndElectionType(state, electionType);
        return  stateCandidates;
    }

    @GetMapping("/deleteAllCandidate")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAllCandidate(){
        String message =  candidateServices.deleteAllCandidate();
        return message;
    }


    @GetMapping("/candidates")
    public List<Candidate> getUsers(){
          List<Candidate> candidates = candidateServices.findAllCandidate();
          return candidates;
    }


}
