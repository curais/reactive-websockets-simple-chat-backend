package com.curais.reactivewebsocketssimplechatbackend.websocket;

import java.time.Duration;

import com.curais.reactivewebsocketssimplechatbackend.model.Message
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Component
public class ChatWSHandler implements WebSocketHandler {

    @Autowired
    private ObjectMapper json;

    private Many<Message> sink = Sinks.many().replay().limit(100);
    private Flux<?> outputMessages = sink.asFlux();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        session.receive()
            .map( msg -> msg.getPayloadAsText() )
            .map( msg -> {
                    try {
                        return json.readValue(msg, Message.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
            .onErrorMap((Throwable error) -> error)
            .subscribe(
                message -> {
                    sink.tryEmitNext(message);
                },
                error -> sink.tryEmitError(error)
            );
        return session.send(
            Mono.delay(Duration.ofMillis(100))
                .thenMany(outputMessages.map(
                    message -> {
						try {
							return session.textMessage(json.writeValueAsString(message));
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
                    })
                .doOnError(error -> System.err.println(error))
        ));
    }
    
}
