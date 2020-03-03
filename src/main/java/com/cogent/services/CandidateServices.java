package com.cogent.services;


import com.cogent.backend.domains.Candidate;
import com.cogent.backend.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String createCandidate(Candidate candidate){
        Candidate checkUsername = candidateRepository.findCandidateByName(candidate.getName());

        if (checkUsername != null) {

            return "Candidate " + checkUsername.getName() + " already exist!";
        } else {
                String userPassword = candidate.getPassword();
                candidate.setPassword(bCryptPasswordEncoder.encode(userPassword));
                candidateRepository.save(candidate);
                return "Candidate registered";
        }

    }

    public Candidate findCandidateById(int id){
        Optional<Candidate> candidate = candidateRepository.findById(id);
        return  candidate.get();
    }

    public Candidate getPresidentialResult(){
        Candidate candidate = candidateRepository.findByElectionTypeOrderByVotesDesc("Presidential").get(0);
        return  candidate;
    }

    public Candidate findCandidateByUsername(String username){
        Candidate candidate = candidateRepository.findCandidateByName(username);
        return candidate;
    }

    public List<Candidate> findAllCandidateByState(String state){
        List<Candidate> stateCandidate = candidateRepository.findCandidatesByState(state);
        return  stateCandidate;
    }

    public List<Candidate> findAllCandidateByStateAndCity(String city, String state){
        List<Candidate> cityAndStateCandidate = candidateRepository.findCandidatesByCityAndState(city,state);
        return  cityAndStateCandidate;
    }
    public List<Candidate> findAllCandidateByStateAndElectionType(String state, String electionType){
        List<Candidate> stateCandidate = candidateRepository.findCandidatesByStateAndElectionType(state, electionType);
        return  stateCandidate;
    }

    public List<Candidate> findAllCandidate(){
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates;
    }

    public String deleteCandidate(int id, String name){
        candidateRepository.deleteById(id);
        return "Candidate " + name + "deleted";
    }

    public String deleteAllCandidate(){
        candidateRepository.deleteAll();
        return "All Candidates have been deleted";
    }
}
