package com.cogent.configuration;

import com.cogent.configuration.jwt.config.JwtAuthenticationEntryPoint;
import com.cogent.configuration.jwt.config.JwtRequestFilter;
import com.cogent.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String salt = "asdfahghjhj89asdf";

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Autowired
    private JwtRequestFilter filter;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    private static final String[] MATCHES = {
            "/authenticate",
      };

    @Bean
    public BCryptPasswordEncoder encoder(){

        return new BCryptPasswordEncoder(12, new SecureRandom(salt.getBytes()));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
            http
                    .authorizeRequests()
                    .antMatchers(MATCHES)
                    .permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                     .exceptionHandling().authenticationEntryPoint(entryPoint)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


//        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
         auth
                 .userDetailsService(userDetailsService)
                 .passwordEncoder(encoder());
    }
}
