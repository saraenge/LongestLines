package com.saraenge.longestlines.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.saraenge.longestlines.deserializer.JourneyDeserializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Objects;

@JsonDeserialize(using = JourneyDeserializer.class)
public class JourneyPatternPointOnLine {
    // ignorerar "LastModifiedUtcDateTime", "ExistsFromDate"
    @Id
    private int id;
    private final int lineNumber;
    private final int directionCode;
    private final int stopPointNumber;
    @Version
    private final Integer version;

    public JourneyPatternPointOnLine(final int lineNumber, final int directionCode, final int stopPointNumber, final Integer version) {
        this.lineNumber = lineNumber;
        this.directionCode = directionCode;
        this.stopPointNumber = stopPointNumber;
        this.version = version;
        this.id = getHashedId();
    }

    public int getHashedId() {
        return Objects.hash(lineNumber, directionCode, stopPointNumber);
    }
}