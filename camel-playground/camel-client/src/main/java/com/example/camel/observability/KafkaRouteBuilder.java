package com.example.camel.observability;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:kafka-in")
                .setHeader(KafkaConstants.KEY, constant("Camel")) // Key of the message
                .to("kafka:test1?brokers=localhost:9092");
    }

}
