package com.cogent.services;

import com.cogent.backend.domains.Role;
import com.cogent.backend.domains.User;
import com.cogent.backend.repositories.ClientRepository;
import com.cogent.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ClientRepository voterRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public String createAdmin(User admin, Role role){

            String password = admin.getPassword();
            admin.setPassword(bCryptPasswordEncoder.encode(password));
            roleRepository.save(role);
            voterRepository.save(admin);
            return "User successfully created!";
    }


}
