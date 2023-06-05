package com.saraenge.longestlines;

import com.saraenge.longestlines.Repository.TodoRepository;
import com.saraenge.longestlines.model.Todo;
import com.saraenge.longestlines.service.JSONPlaceholderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(TrafiklabConfigProperties.class)
public class LongestLinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongestLinesApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner commandLineRunner(JSONPlaceholderService jsonPlaceholderService, TodoRepository todoRepository) {
        return args -> {
            List<Todo> todos = jsonPlaceholderService.getTodos();

            todoRepository.saveAll(todos);
        };
    }

}
