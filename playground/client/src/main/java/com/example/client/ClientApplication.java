package com.example.client;

import com.example.client.services.IExternalHttpService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class ClientApplication {
    Logger logger = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    IExternalHttpService externalHttpService(RestTemplate restTemplate) {
//        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8080").build();
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
//        return factory.createClient(IExternalHttpService.class);

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));
        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(IExternalHttpService.class);
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
