package com.cogent.services;


import com.cogent.backend.domains.Candidate;
import com.cogent.backend.domains.Elections;
import com.cogent.backend.repositories.CandidateRepository;
import com.cogent.backend.repositories.ElectionRepository;
import com.cogent.utils.models.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ElectionsService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;


    public String createMayorElections(String state, String city, Date startDate, Date endDate){

        List<Candidate> candidates =
                candidateRepository.findCandidatesByCityAndElectionType(city,"Mayor");

        Elections mayorElections = new Elections();
        mayorElections.setElectionType("Mayor");
        mayorElections.setCity(city);
        LocalDateTime startingDate = LocalDateTime.of(startDate.getYear(),startDate.getMonth(),
                startDate.getDayOfMonth(),startDate.getHours(),startDate.getMinutes());
        LocalDateTime endingDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(),
                endDate.getDayOfMonth(), endDate.getHours(),endDate.getMinutes());
        mayorElections.setStart(startingDate);
        mayorElections.setEnd(endingDate);
        if (candidates != null){
            mayorElections.getCandidate().addAll(candidates);
        }
        Elections electionRespond =electionRepository.save(mayorElections);
        if (electionRespond == null){
            return "Election not created!";
        } else {
            return "Election created!";
        }

    }

    public String createPresidentialElections( Date startDate, Date endDate){
        List<Candidate> candidates =
                candidateRepository.findCandidatesByElectionType("Presidential");
        Elections presidentElections = new Elections();
        presidentElections.setElectionType("Presidential");
        LocalDateTime startingDate = LocalDateTime.of(startDate.getYear(),startDate.getMonth(),
                startDate.getDayOfMonth(),startDate.getHours(),startDate.getMinutes());
        LocalDateTime endingDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(),
                endDate.getDayOfMonth(), endDate.getHours(),endDate.getMinutes());
        presidentElections.setStart(startingDate);
        presidentElections.setEnd(endingDate);
        if (candidates != null){
            presidentElections.getCandidate().addAll(candidates);
        }
        Elections electionRespond =electionRepository.save(presidentElections);

        if (electionRespond == null){
            return "Election not created!";
        } else {
            return "Election created!";
        }
    }

    public String createPriMinisterElections(String state, String city, Date startDate, Date endDate){
        List<Candidate> candidates =
                candidateRepository.findCandidatesByElectionType("Priminister");

        Elections priMinisterElections = new Elections();
        priMinisterElections.setElectionType("Priminister");
        LocalDateTime startingDate = LocalDateTime.of(startDate.getYear(),startDate.getMonth(),
                startDate.getDayOfMonth(),startDate.getHours(),startDate.getMinutes());
        LocalDateTime endingDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(),
                endDate.getDayOfMonth(), endDate.getHours(),endDate.getMinutes());
        priMinisterElections.setStart(startingDate);
        priMinisterElections.setEnd(endingDate);

        if (candidates != null){
            priMinisterElections.getCandidate().addAll(candidates);
        }

        Elections electionRespond =electionRepository.save(priMinisterElections);

        if (electionRespond == null){
            return "Election not created!";
        } else {
            return "Election created!";
        }
    }

    public Elections findAllPresidentialCandidates(){
        Elections presidentialCandidates = electionRepository.findByElectionType("Presidential");
        return presidentialCandidates;
    }

    public Elections findAllPrimeCandidates(){
        Elections primeCandidate = electionRepository.findByElectionType("Priminister");
        return primeCandidate;
    }


    public List<Elections> findAllElectionDate(){
        List<Elections> elections = electionRepository.findAll();
        return elections;
    }

    public Elections findAllMayorCandidates(){
        Elections mayorCandidates = electionRepository.findByElectionType("Mayor");
        return  mayorCandidates;
    }


//    public


}
