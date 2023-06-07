package com.saraenge.longestlines.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.saraenge.longestlines.deserializer.JourneyDeserializer;

@JsonDeserialize(using = JourneyDeserializer.class)
public record JourneyPatternPointOnLine(int lineNumber, int directionCode, int stopPointNumber) {
    // ignorerar "LastModifiedUtcDateTime", "ExistsFromDate"
}