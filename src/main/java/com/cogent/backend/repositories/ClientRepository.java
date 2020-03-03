package com.cogent.backend.repositories;

import com.cogent.backend.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<User, Long> {


    public User findByUsername(String username);

    public List<User> findAllByState(String state);

    public List<User> findAllByCity(String city);


}
