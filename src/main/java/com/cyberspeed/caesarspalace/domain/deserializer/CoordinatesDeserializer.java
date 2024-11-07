package com.cyberspeed.caesarspalace.domain.deserializer;

import com.cyberspeed.caesarspalace.domain.Coordinate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoordinatesDeserializer extends JsonDeserializer<List<List<Coordinate>>> {

    @Override
    public List<List<Coordinate>> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = p.getCodec().readTree(p);
        List<List<Coordinate>> coordinates = new ArrayList<>();

        if (node instanceof ArrayNode) {
            for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
                List<Coordinate> nestedCoordinates = new ArrayList<>();
                JsonNode coordNode = it.next();
                for (Iterator<JsonNode> nested = coordNode.elements(); nested.hasNext(); ) {
                    JsonNode nestedNode = nested.next();
                    String[] parts = nestedNode.asText().split(":");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    nestedCoordinates.add(new Coordinate(x, y));
                }
                coordinates.add(nestedCoordinates);
            }
        }

        return coordinates;
    }
}
