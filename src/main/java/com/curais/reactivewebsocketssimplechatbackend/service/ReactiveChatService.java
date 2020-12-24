package com.curais.reactivewebsocketssimplechatbackend.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.curais.reactivewebsocketssimplechatbackend.model.Chat;
import com.curais.reactivewebsocketssimplechatbackend.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ReactiveChatService implements IChatService {

    @Autowired
    private ReactiveMongoTemplate template;

    @Override
    public Mono<Chat> pushMessage(Message message, String name) {
        return template.findAndModify(
            query(where("name").is(name)), 
            new Update().push("history", message), 
            Chat.class);
    }

    @Override
    public Mono<Chat> retrieveChat(String name) {
        return template.findOne(
            query(where("name").is(name)), 
            Chat.class);
    }
    
}
