package com.cogent.backend.domains;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String state;
    private String password;
    private boolean voterPrimeMinister;
    private String username;
    private String firstname;
    private String lastname;
    private String imageUrl;
    @Column(name = "email_address")
    private String emailAddress;
    private String city;
    @Column(name = "voter_number")
    private String voterNumber;
    private boolean voteredPresident;
    private boolean voteredMayor;

    @ManyToOne
    private Role role;

    public User(){}

    public User(String username, String firstname, String lastname, String emailAddress,
                String city, String state, String voterNumber,String password, Role role){
        this.username = username;
        this.city = city;
        this.emailAddress = emailAddress;
        this.firstname = firstname;
        this.lastname = lastname;
        this.state = state;
        this.voterNumber = voterNumber;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new Authority(role.getRole()));
            return authorities;

    }

    public boolean isVoteredPresident() {
        return voteredPresident;
    }

    public void setVoteredPresident(boolean voteredPresident) {
        this.voteredPresident = voteredPresident;
    }

    public boolean isVoteredMayor() {
        return voteredMayor;
    }

    public void setVoteredMayor(boolean voteredMayor) {
        this.voteredMayor = voteredMayor;
    }

    public boolean isVoterPrimeMinister() {
        return voterPrimeMinister;
    }

    public void setVoterPrimeMinister(boolean voterPrimeMinister) {
        this.voterPrimeMinister = voterPrimeMinister;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVoterNumber() {
        return voterNumber;
    }

    public void setVoterNumber(String voterNumber) {
        this.voterNumber = voterNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                voterPrimeMinister == user.voterPrimeMinister &&
                voteredPresident == user.voteredPresident &&
                voteredMayor == user.voteredMayor &&
                Objects.equals(state, user.state) &&
                Objects.equals(password, user.password) &&
                Objects.equals(username, user.username) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(imageUrl, user.imageUrl) &&
                Objects.equals(emailAddress, user.emailAddress) &&
                Objects.equals(city, user.city) &&
                Objects.equals(voterNumber, user.voterNumber) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, password, voterPrimeMinister, username, firstname, lastname, imageUrl, emailAddress, city, voterNumber, voteredPresident, voteredMayor, role);
    }
}
