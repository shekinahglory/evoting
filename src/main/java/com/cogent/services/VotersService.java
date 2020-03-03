package com.cogent.services;

import com.cogent.backend.domains.Role;
import com.cogent.backend.domains.User;
import com.cogent.backend.repositories.ClientRepository;
import com.cogent.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotersService {

    @Autowired
    private ClientRepository voterRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public String createVoter(User voter){

        User ifVoterExist = voterRepository.findByUsername(voter.getUsername());
        Role role = new Role(1,"ROLE_USER");
        if (ifVoterExist == null){
            String password = voter.getPassword();
            voter.setRole(role);
            voter.setPassword(bCryptPasswordEncoder.encode(password));
            roleRepository.save(role);
            voterRepository.save(voter);
            return "User successfully created!";

        } else {
            return "Username " +voter.getUsername() + "already exist!";
        }
    }

    public List<User> findAllVoterByState(String state){
        List<User> stateVoter;
        stateVoter = voterRepository.findAllByState(state);
        return stateVoter;
    }

    public List<User> findAllVoterByCity(String city){
        List<User> cityVoter;
        cityVoter = voterRepository.findAllByCity(city);
        return cityVoter;

    }

    public String deleteVoterByHisId(long id){
         voterRepository.deleteById(id);
         return "User deleted";
    }

    public String deleteAllVoter(){
        voterRepository.deleteAll();
        return "All voter have been deleted";
    }

    public User findUserByUsername(String username){
        User user = voterRepository.findByUsername(username);
        return user;
    }

    public List<User> findAllVoter(){
        List<User> voters;
        voters = voterRepository.findAll();
        return voters;
    }

    public User findVoterById(long id){
        Optional<User> voter = voterRepository.findById(id);
        return voter.get();
    }
}
