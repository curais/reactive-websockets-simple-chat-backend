package com.curais.reactivewebsocketssimplechatbackend.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Chat {

    private String name;
    
    private List<Message> history;

    public List<Message> getHistory() {
        return history;
    }

    public void setHistory(List<Message> history) {
        this.history = history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
