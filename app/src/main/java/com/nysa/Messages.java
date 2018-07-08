package com.nysa;

/**
 * Created by Vlad on 28/05/2018 0028.
 */

public class Messages {

      private String from, message;
    public Messages(String from, String message){
        this.from=from;
        this.message=message;

    }
    public Messages(){

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
