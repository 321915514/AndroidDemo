package com.example.myapplication.event;

public class MessageWrap {
    private String message;

    public String getMessage() {
        return message;
    }
    private MessageWrap(String message){
        this.message = message;
    }
    public static MessageWrap getInstance(String message){
        return new MessageWrap(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
