package com.saraenge.longestlines.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.saraenge.longestlines.model.StopPoint;

import java.io.IOException;

public class StopPointDeserializer extends StdDeserializer<StopPoint> {

    public StopPointDeserializer() {
        this(null);
    }

    public StopPointDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public StopPoint deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode stopPointNode = jp.getCodec().readTree(jp);
        final int number = stopPointNode.get("StopPointNumber").asInt();
        final String name = stopPointNode.get("StopPointName").textValue();

        StopPoint stopPoint = new StopPoint(number, number, name, null);
        return stopPoint;
    }
}

