package com.cogent.configuration.jwt.config;

import com.cogent.services.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtilities jwtTokenUtilities;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

          final String requestHeader = request.getHeader("Authorization");
          String username = null;
          String token = null;

          if (requestHeader != null && requestHeader.startsWith("Bearer")){
              token = requestHeader.substring(7);
              try {
                  username = jwtTokenUtilities.getUsernameFromToken(token);

              } catch (IllegalArgumentException e){
                  System.out.println("Unable to get JWT Token");
              }
              catch (ExpiredJwtException e){

              }
          } else {
              logger.warn(" JWT Token does not begin with Bearer String");
          }

          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
              UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
              if(jwtTokenUtilities.validateToken(token, userDetails)){
                  UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                          userDetails,null, userDetails.getAuthorities()
                  );
                  upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(upat);
              }
          }

          filterChain.doFilter(request, response);


    }
}

