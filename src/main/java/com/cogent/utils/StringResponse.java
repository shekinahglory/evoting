package com.cogent.utils;

import java.io.Serializable;

public class StringResponse implements Serializable {

    private final String result;

    public StringResponse(String result){
        this.result = result;
    }


    public String getResult() {
        return this.result;
    }
}
