package com.cogent.backend.repositories;

import com.cogent.backend.domains.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {


       public Candidate findCandidateByName(String username);

       public List<Candidate> findByElectionTypeOrderByVotesDesc(String electionType);

       public List<Candidate> findCandidatesByElectionType(String electionType);

       public List<Candidate> findCandidatesByState(String state);

       public List<Candidate> findCandidatesByCityAndState(String city, String state);

       public List<Candidate> findCandidatesByCityAndElectionType(String city, String electionType);

       public List<Candidate> findCandidatesByStateAndElectionType(String state, String electionType);
}
