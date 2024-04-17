package com.example.client.controllers;

import com.example.client.model.Post;
import com.example.client.services.ExternalService;
import com.example.client.services.IExternalHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    IExternalHttpService externalHttpService;

//    @Autowired
//    ExternalService externalHttpService;

//    public ClientController(IExternalHttpService externalHttpService) {
//        this.externalHttpService = externalHttpService;
//    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        logger.info("External post service has been called");
        return externalHttpService.findAll();
    }

//    @GetMapping("/posts/{id}")
//    public Post findById(@PathVariable Integer id) {
//        return externalHttpService.findById(id);
//    }
}
