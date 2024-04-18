package com.example.client.services;

import com.example.client.model.Post;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessingService {
    private final ObservationRegistry registry;

    ProcessingService(ObservationRegistry registry) {
        this.registry = registry;
    }

    public List<Post> processWithObservation(List<Post> postList) {
        return Observation.createNotStarted("process posts", registry)
                .lowCardinalityKeyValue("lowTag", "lowTagValue")
                .highCardinalityKeyValue("highTag", "highTagValue")
                .observe(() -> process(postList));
    }

    public List<Post> process(List<Post> postList) {
       // update the postList title to uppercase
        return postList.stream()
                .map(post -> new Post(post.id(), post.userId(), post.title().toUpperCase(), post.body()))
                .collect(Collectors.toList());
    }
}
