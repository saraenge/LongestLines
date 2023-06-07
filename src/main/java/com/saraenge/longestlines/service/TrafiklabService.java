package com.saraenge.longestlines.service;

import com.saraenge.longestlines.TrafiklabConfigProperties;
import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.StopPoint;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TrafiklabService {
    private final RestTemplate restTemplate;
    private final String API_BASE_URL = "https://api.sl.se/api2/LineData.json?model=[model]&key=[key]";
    private final String onlyBus = "DefaultTransportModeCode=BUS";
    private final String apikey;

    public TrafiklabService(final RestTemplate restTemplate, final TrafiklabConfigProperties trafiklabConfigProperties) {
        this.restTemplate = restTemplate;
        apikey = trafiklabConfigProperties.apiKey();
    }


    public List<JourneyPatternPointOnLine> getPointsOnLine() {
        ResponseEntity<List<JourneyPatternPointOnLine>> exchange = restTemplate.exchange(
                getFullUrl("jour"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JourneyPatternPointOnLine>>() {}
        );
        return exchange.getBody();
    }

    public List<StopPoint> getStopPoints() {
        ResponseEntity<List<StopPoint>> exchange = restTemplate.exchange(
                getFullUrl("stop"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StopPoint>>() {}
        );
        return exchange.getBody();
    }

    private String getFullUrl(final String model) {
        return API_BASE_URL
                .replace("[model]", model)
                .replace("[key]", apikey)
                .concat("&" + onlyBus);
    }
}
