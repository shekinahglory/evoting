package com.cogent.services;


import com.cogent.backend.domains.Candidate;
import com.cogent.backend.domains.Elections;
import com.cogent.backend.domains.User;
import com.cogent.backend.repositories.CandidateRepository;
import com.cogent.backend.repositories.ClientRepository;
import com.cogent.backend.repositories.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;

@Service
public class VotingService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private ClientRepository clientRepository;

    private boolean votered;

    public String vote(String candidateName, String voterName, String type) {

        Candidate candidate = candidateRepository.findCandidateByName(candidateName);
        if (type.equals("Presidential")) {
            Elections elections = electionRepository.findByElectionType("Presidential");
            elections.getCandidate().remove(candidate);
        } else if (type.equals("Mayor")) {
            Elections elections = electionRepository.findByElectionType("Mayor");
            elections.getCandidate().remove(candidate);
        } else if (type.equals("Primeminister")) {
            Elections elections = electionRepository.findByElectionType("Primeminister");
            elections.getCandidate().remove(candidate);
        }

        long votes = candidate.getVotes();
        User voter = clientRepository.findByUsername(voterName);
        if (voter != null) {
            if (type.equals("Presidential")) {
                votered = voter.isVoteredPresident();
                if (votered == false) {
                    votes = votes + 1;
                    candidate.setVotes(votes);
                    voter.setVoteredPresident(true);
                    candidateRepository.save(candidate);
                    clientRepository.save(voter);
                    return "Voted";
                } else return "Already voted";
            } else if (type.equals("Mayor")) {
                votered = voter.isVoteredMayor();
                if (votered == false) {
                    votes = votes + 1;
                    candidate.setVotes(votes);
                    voter.setVoteredMayor(true);
                    candidateRepository.save(candidate);
                    clientRepository.save(voter);
                    return "Voted";
                } else return "Already voted";
            } else if (type.equals("Primeminister")) {
                votered = voter.isVoterPrimeMinister();
                if (votered == false) {
                    votes = votes + 1;
                    candidate.setVotes(votes);
                    voter.setVoterPrimeMinister(true);
                    candidateRepository.save(candidate);
                    clientRepository.save(voter);
                    return "Voted";
                } else return "Already voted";
            }

        }
        return "Can't vote";
    }

}
