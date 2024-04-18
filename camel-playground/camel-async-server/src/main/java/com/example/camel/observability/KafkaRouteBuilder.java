package com.example.camel.observability;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:test1?brokers=localhost:9092")
                .log("Message received from Kafka : ${body}");
    }
}
