package com.curais.reactivewebsocketssimplechatbackend.functional;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.curais.reactivewebsocketssimplechatbackend.model.Chat;
import com.curais.reactivewebsocketssimplechatbackend.service.IChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FunctionalEndpointsConfig {

    @Autowired
    private IChatService service;
    //TODO to work on create chat functional endpoint
    @Bean
    RouterFunction<ServerResponse> getChatRoute() {
      return route(GET("/chat/{name}"), 
        req -> ok().body(
          service.retrieveChat(req.pathVariable("name")), Chat.class));
    }
}


