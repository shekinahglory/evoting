package com.cogent.backend.repositories;

import com.cogent.backend.domains.Candidate;
import com.cogent.backend.domains.Elections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Elections, Integer> {

      public Elections findByElectionType(String electionType);

//    @Query("select can from Elections can where ? between can.start and can.end")
//    public Candidate findAllByStartBetween(LocalDateTime date);

//    public Candidate findAllByStartIsLessThanEqualAndEndGreaterThanEqual(LocalDateTime date);

//    @Query("select ptr from PasswordResetToken ptr inner join  ptr.user u where ptr.user.id = ?1")


}
