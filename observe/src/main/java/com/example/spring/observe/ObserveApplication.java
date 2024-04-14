package com.example.spring.observe;

import com.example.spring.observe.post.IJsonPlaceHolderService;
import com.example.spring.observe.post.Post;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;


@SpringBootApplication
public class ObserveApplication {

	Logger logger = LoggerFactory.getLogger(ObserveApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ObserveApplication.class, args);
	}

	@Bean
	IJsonPlaceHolderService jsonPlaceHolderService() {
		// TODO: [YD] Study on spring boot rest client
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(IJsonPlaceHolderService.class);
	}

	@Bean
	@Observed(contextualName = "json-get-all-posts-")
	CommandLineRunner commandLineRunner(IJsonPlaceHolderService jsonPlaceHolderService
	) {
		return args -> {
			List<Post> all = jsonPlaceHolderService.findAll();
			logger.info("All data fetched, number of posts : {}", all.size());
		};
	}

}
