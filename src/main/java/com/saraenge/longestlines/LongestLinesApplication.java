package com.saraenge.longestlines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TrafiklabConfigProperties.class)
public class LongestLinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongestLinesApplication.class, args);
    }

}
