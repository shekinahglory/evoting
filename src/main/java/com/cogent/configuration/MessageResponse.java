package com.cogent.configuration;

import java.io.Serializable;

public class MessageResponse  implements Serializable {

    public String message;

    public MessageResponse(String message){
        this.message = message;
    }

    public String getMessage(){return this.message;}

}
