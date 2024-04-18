package com.example.camel.observability;

import org.apache.camel.observation.starter.CamelObservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CamelObservation
public class AsyncServer {
    public static void main(String[] args) {
        SpringApplication.run(AsyncServer.class, args);
    }
}

