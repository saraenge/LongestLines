package com.saraenge.longestlines.model;

public record StopPoint(int stopPointNumber, int stopPointName) {
    // ignorerar "StopAreaNumber", "LocationNorthingCoordinate", "LocationEastingCoordinate", "ZoneShortName", "StopAreaTypeCode", "LastModifiedUtcDateTime", "ExistsFromDate"
}
