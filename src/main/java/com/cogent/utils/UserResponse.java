package com.cogent.utils;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046834L;

    private final String token;
    private final boolean voterPrimeMinister;
    private final boolean voteredPresident;
    private final boolean voteredMayor;
    private final String state;
    private final String city;
    private final String role;
    private final String imageUrl;

    public UserResponse(String token, boolean vP, boolean vPrime, boolean vM,
                        String state, String city, String role, String imageUrl){
        this.token = token;
        this.city = city;
        this.voteredMayor = vM;
        this.state = state;
        this.voterPrimeMinister = vPrime;
        this.voteredPresident = vP;
        this.role = role;
        this.imageUrl = imageUrl;
    }

    public String getToken() {
        return token;
    }

    public boolean isVoterPrimeMinister() {
        return voterPrimeMinister;
    }

    public boolean isVoteredPresident() {
        return voteredPresident;
    }

    public boolean isVoteredMayor() {
        return voteredMayor;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getRole() {
        return role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
