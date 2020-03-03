package com.cogent.controllers;

import com.cogent.backend.domains.Role;
import com.cogent.backend.domains.User;
import com.cogent.configuration.jwt.config.JwtTokenUtilities;
import com.cogent.configuration.jwt.models.JwtRequest;
import com.cogent.configuration.jwt.models.JwtResponse;
import com.cogent.services.JwtUserDetailsService;
import com.cogent.services.VotersService;
import com.cogent.utils.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtilities jwtTokenUtilities;

    @Autowired
    private VotersService votersService;

    private VotersService userService;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) throws Exception{
        authenticate(authRequest.getUsername(), authRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenUtilities.generateToken(userDetails);

        User user = votersService.findUserByUsername(authRequest.getUsername());

        return ResponseEntity.ok(new UserResponse(token,user.isVoteredPresident(),
                user.isVoterPrimeMinister(),user.isVoteredMayor(),user.getState(), user.getCity(), user.getRole().getRole(), user.getImageUrl()));
    }

    private void authenticate(String username, String password) throws Exception {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e){
            throw new Exception("INVALID CREDENTIALS", e);
        }
    }
}
