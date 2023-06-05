package com.saraenge.longestlines.service;

import com.saraenge.longestlines.model.Todo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JSONPlaceholderService {
    private final String API_URL = "https://jsonplaceholder.typicode.com/todos";
    private final RestTemplate restTemplate;

    public JSONPlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Todo> getTodos() {
        ResponseEntity<List<Todo>> exchange = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Todo>>() {}
        );
        return exchange.getBody();
    }
}
