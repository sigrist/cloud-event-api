package com.github.sigrist.cloudevent.codec.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;

public class JacksonJsonCodec implements Codec {

    @Override
    public String encode(final Object source) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new CloudEventException("Error encoding object to JSON", e);
        }
    }

    public <T> T decode(final String object, final Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(object, clazz);
        } catch (JsonProcessingException e) {
            throw new CloudEventException("Error decoding JSON", e);
        }
    }

    @Override
    public String contentType() {
        return "application/json";
    }

}
