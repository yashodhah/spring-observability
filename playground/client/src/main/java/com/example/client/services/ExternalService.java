package com.example.client.services;

import com.example.client.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalService {
    private final RestTemplate template;

    public ExternalService(RestTemplate template) {
        this.template = template;
    }

    public List<Post> findAll() {
        Post[] forObject = template.getForObject("http://localhost:8080/api/posts", Post[].class);
        return Arrays.asList(forObject);
    }
}
