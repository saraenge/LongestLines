package com.saraenge.longestlines;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("trafiklab")
public record TrafiklabConfigProperties(String apiKey) {
}
