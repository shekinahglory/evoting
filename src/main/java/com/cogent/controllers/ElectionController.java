package com.cogent.controllers;

import com.cogent.backend.domains.Candidate;
import com.cogent.backend.domains.Elections;
import com.cogent.services.ElectionsService;
import com.cogent.utils.DateTransform;
import com.cogent.utils.StringResponse;
import com.cogent.utils.models.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class ElectionController {
    @Autowired
    private ElectionsService electionsService;

    @PostMapping("/create-election")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createElection(@RequestBody Elections elections){
        String stringStartDate = elections.getStart().toString();
        String stringEndDate = elections.getEnd().toString();
        Date startDate = DateTransform.transform(stringStartDate);
        Date endDate = DateTransform.transform(stringEndDate);
        String res = electionsService.createPresidentialElections(startDate, endDate);
        if (res.equals("Election not created!")){
            return ResponseEntity.ok(new StringResponse("Error occuped. Election not created"));
        } else {
            return ResponseEntity.ok(new StringResponse("Election crated"));
        }
    }

    @GetMapping("/get-electionDate")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Elections> getElections(){
        return electionsService.findAllElectionDate();
    }



    @GetMapping("/presidential-candidate")
    public Set<Candidate> getPresidentialCandidate(){

        Elections elections = electionsService.findAllPresidentialCandidates();
        Set<Candidate> candidates = new HashSet<>();
        candidates.addAll(elections.getCandidate());


        return candidates;
    }

    @GetMapping("/mayor-candidate")
    public Set<Candidate> getMayorCandidate(){
        Elections elections = electionsService.findAllMayorCandidates();
        Set<Candidate> candidates = new HashSet<>();
        candidates.addAll(elections.getCandidate());
        return candidates;
    }

    @GetMapping("/prime-candidate")
    public Set<Candidate> getPrimeCandidate(){
        Elections electionse = electionsService.findAllPrimeCandidates();
        Set<Candidate> candidates = new HashSet<>();
        candidates.addAll(electionse.getCandidate());
        return  candidates;
    }
}
