package com.cogent.utils;

import java.io.Serializable;

public class VoterRequest implements Serializable {

    private String candName;
    private String voterName;

    public VoterRequest(){}

    public String getCandName() {
        return candName;
    }

    public void setCandName(String candName) {
        this.candName = candName;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }
}
