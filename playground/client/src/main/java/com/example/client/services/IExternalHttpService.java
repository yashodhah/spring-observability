package com.example.client.services;

import com.example.client.model.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;


public interface IExternalHttpService {
    @GetExchange("/api/posts")
    List<Post> findAll();

    @GetExchange("/api/posts/{id}")
    Post findById(@PathVariable Integer id);

}
