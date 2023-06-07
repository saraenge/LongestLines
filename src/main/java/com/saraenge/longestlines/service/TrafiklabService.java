package com.saraenge.longestlines.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saraenge.longestlines.TrafiklabConfigProperties;
import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.StopPoint;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        final String fullUrl = getFullUrl("stop");
        ResponseEntity<String> exchange = restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                null,
                String.class
        );
        String body = exchange.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonNode = objectMapper.readTree(body);
            JsonNode responseDataNode = jsonNode.get("ResponseData");
            JsonNode resultNode = responseDataNode.get("Result");
            return objectMapper.convertValue(resultNode, new TypeReference<List<StopPoint>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private String getFullUrl(final String model) {
        return API_BASE_URL
                .replace("[model]", model)
                .replace("[key]", apikey)
                .concat("&" + onlyBus);
    }
}
