package com.cogent.services;


import com.cogent.backend.domains.User;
import com.cogent.backend.repositories.ClientRepository;
import com.cogent.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class JwtUserDetailsService implements UserDetailsService {

//    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = clientRepository.findByUsername(username);

        if (user == null){
            System.out.println("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }

    private Set getAuthority(User user){
        Set authorities = new HashSet();
        authorities.add(new SimpleGrantedAuthority(user.getRole().role));
        return authorities;
    }

}
