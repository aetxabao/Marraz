package com.masanz.marraz.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masanz.marraz.enums.EForm;
import com.masanz.marraz.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Serializer {

    public static AForm fromJSON(String json, EForm forma) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (forma) {
            case LINE:
                return objectMapper.readValue(json, Line.class);
            case CIRCLE:
                return objectMapper.readValue(json, Circle.class);
            case RECTANGLE:
                return objectMapper.readValue(json, Rectangle.class);
            case TRIANGLE:
                return objectMapper.readValue(json, Triangle.class);
            case HOUSE:
                return objectMapper.readValue(json, House.class);
        }
        return null;
    }

    public static String toJSON(AForm forma) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
//        return om.writerWithDefaultPrettyPrinter().writeValueAsString(forma);
        return om.writeValueAsString(forma);
    }

}
