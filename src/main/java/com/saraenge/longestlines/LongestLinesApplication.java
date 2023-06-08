package com.saraenge.longestlines;

import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.repository.JourneyPatternPointOnLineRepository;
import com.saraenge.longestlines.repository.StopPointRepository;
import com.saraenge.longestlines.repository.TodoRepository;
import com.saraenge.longestlines.model.StopPoint;
import com.saraenge.longestlines.model.Todo;
import com.saraenge.longestlines.service.JSONPlaceholderService;
import com.saraenge.longestlines.service.TrafiklabService;
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

    @Bean
    CommandLineRunner commandLineRunnerStopPoint(TrafiklabService trafiklabService, StopPointRepository stopPointRepository) {
        return args -> {
            List<StopPoint> stopPoints = trafiklabService.getStopPoints();

            stopPointRepository.saveAll(stopPoints);
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerJourneyAPI(TrafiklabService trafiklabService, JourneyPatternPointOnLineRepository journeyPatternPointOnLineRepository) {
        return args -> {
            List<JourneyPatternPointOnLine> pointsOnLines = trafiklabService.getPointsOnLine();

            journeyPatternPointOnLineRepository.saveAll(pointsOnLines);
        };
    }

}
