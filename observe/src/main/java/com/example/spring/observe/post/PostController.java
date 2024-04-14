package com.example.spring.observe.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final IJsonPlaceHolderService jsonPlaceHolderService;

    public PostController(IJsonPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    @GetMapping("")
    List<Post> findAll(){
        return jsonPlaceHolderService.findAll();
    }

    @GetMapping("/{id}")
    Post findById(@PathVariable Integer id) {
         return jsonPlaceHolderService.findById(id);
    }
}
