package com.curais.reactivewebsocketssimplechatbackend.service;

import com.curais.reactivewebsocketssimplechatbackend.model.Chat;
import com.curais.reactivewebsocketssimplechatbackend.model.Message;

import reactor.core.publisher.Mono;

public interface IChatService {
    public Mono<Chat> pushMessage(Message message, String name);
    public Mono<Chat> retrieveChat (String name);
}