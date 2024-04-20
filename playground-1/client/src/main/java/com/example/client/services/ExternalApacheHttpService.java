package com.example.client.services;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.transport.RequestReplySenderContext;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ExternalApacheHttpService {

    private final ObservationRegistry registry;

    ExternalApacheHttpService(ObservationRegistry registry) {
        this.registry = registry;
    }

    public String sendRequest() {

        // RequestReplySenderContext is a special type of context used for request-reply
        // communication. It requires to define what the Request type is and how we can
        // instrument it. It also needs to know what the Response type is
        RequestReplySenderContext<HttpUriRequestBase, ClassicHttpResponse> context = new RequestReplySenderContext<>((carrier, key, value) -> Objects.requireNonNull(carrier).addHeader(key, value));

        // We're instrumenting the Apache HTTPClient
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // The HttpGet is our carrier (we can mutate it to instrument the headers)
            HttpGet httpget = new HttpGet("http://localhost:8080/api/posts" + "/foo");
            // We must set the carrier BEFORE we run <Observation#start>
            context.setCarrier(httpget);
            // You can set the remote service address to provide more debugging
            // information
            context.setRemoteServiceAddress("http://localhost:8080/api/posts");

            // Examples of setting key values from the request
            Observation observation = Observation.createNotStarted("http.client.requests", () -> context, registry)
                    .contextualName("HTTP " + httpget.getMethod())
                    .lowCardinalityKeyValue("http.url", "http://localhost:8080/api/posts/{id}")
                    .highCardinalityKeyValue("http.full-url", httpget.getRequestUri());

            observation.observeChecked(() -> {
                String response = httpclient.execute(httpget, classicHttpResponse -> {
                    // We should set the response before we stop the observation
                    context.setResponse(classicHttpResponse);
                    // Example of setting key values from the response
                    observation.highCardinalityKeyValue("http.content.length", String.valueOf(classicHttpResponse.getEntity().getContentLength()));
                    return EntityUtils.toString(classicHttpResponse.getEntity());
                });

//                then(response).isEqualTo("OK");
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return "";
    }

}
