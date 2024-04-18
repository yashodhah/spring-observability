package com.example.client.controllers;

import com.example.client.model.Post;
import com.example.client.services.IExternalHttpService;
import com.example.client.services.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    IExternalHttpService externalHttpService;

    @Autowired
    ProcessingService processingService;

//    public ClientController(IExternalHttpService externalHttpService) {
//        this.externalHttpService = externalHttpService;
//    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        logger.info("External post service has been called");
        List<Post> postLists =  externalHttpService.findAll();
        return processingService.processWithObservation(postLists);
    }

//    @GetMapping("/posts/{id}")
//    public Post findById(@PathVariable Integer id) {
//        return externalHttpService.findById(id);
//    }
}
