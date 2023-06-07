package com.saraenge.longestlines.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.StopPoint;

import java.io.IOException;

public class JourneyDeserializer extends StdDeserializer<JourneyPatternPointOnLine> {

    public JourneyDeserializer() {
        this(null);
    }

    public JourneyDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public JourneyPatternPointOnLine deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode journeyPatternNode = jp.getCodec().readTree(jp);
        final int lineNumber = journeyPatternNode.get("LineNumber").asInt();
        final int directionCode = journeyPatternNode.get("DirectionCode").asInt();
        final int stopPointNumber  = journeyPatternNode.get("JourneyPatternPointNumber").asInt();

        return new JourneyPatternPointOnLine(lineNumber, directionCode, stopPointNumber);
    }
}

