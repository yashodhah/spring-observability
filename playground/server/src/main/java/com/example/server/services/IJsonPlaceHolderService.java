package com.example.server.services;

import com.example.server.model.Post;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface IJsonPlaceHolderService {

    @GetExchange("/posts")
    List<Post> findAll();

    @GetExchange("/posts/{id}")
    Post findById(@PathVariable Integer id);
}
