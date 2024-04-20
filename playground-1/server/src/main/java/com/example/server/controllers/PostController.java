package com.example.server.controllers;

import com.example.server.services.IJsonPlaceHolderService;
import com.example.server.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    Logger  logger = LoggerFactory.getLogger(PostController.class);
    private final IJsonPlaceHolderService jsonPlaceHolderService;

    public PostController(IJsonPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    @GetMapping("")
    List<Post> findAll(){
        logger.info("Post service has been called");
        return jsonPlaceHolderService.findAll();
    }

    @GetMapping("/{id}")
    Post findById(@PathVariable Integer id) {
         return jsonPlaceHolderService.findById(id);
    }
}
