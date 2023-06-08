package com.saraenge.longestlines.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.saraenge.longestlines.deserializer.StopPointDeserializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@JsonDeserialize(using = StopPointDeserializer.class)
public record StopPoint(@Id int id, int stopPointNumber, String stopPointName, @Version Integer version) {
    // ignorerar "StopAreaNumber", "LocationNorthingCoordinate", "LocationEastingCoordinate", "ZoneShortName", "StopAreaTypeCode", "LastModifiedUtcDateTime", "ExistsFromDate"
}
