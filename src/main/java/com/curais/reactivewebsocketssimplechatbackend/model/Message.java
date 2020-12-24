package com.curais.reactivewebsocketssimplechatbackend.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Message {
    private String message;
    private String user;
    private final String timestamp = LocalDateTime.now().toString();

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
