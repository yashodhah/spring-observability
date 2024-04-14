package com.example.client;

import com.example.client.model.Post;
import com.example.client.services.IExternalHttpService;
import com.sun.source.tree.TryTree;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class ClientApplication {
    Logger logger = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    IExternalHttpService externalHttpService() {
        RestClient restClient = RestClient.create("http://localhost:8080");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(IExternalHttpService.class);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    @Observed(contextualName="client.externalHttpService", lowCardinalityKeyValues = {"operation", "findAll"})
//    CommandLineRunner commandLineRunner(IExternalHttpService externalHttpService) {
//        return args -> {
//            List<Post> posts = externalHttpService.findAll();
//            logger.info("Number of Posts: {}", posts.size());
//        };
//    }
}
