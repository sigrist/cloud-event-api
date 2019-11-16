/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.sigrist.cloudevent.codec.jackson;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Codecs;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;

public class JacksonJsonEventCodec implements EventCodec {

    private final ObjectMapper mapper;

    private final Codecs codecs;

    public JacksonJsonEventCodec(final Codecs codecs) {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new Jdk8Module());
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.codecs = codecs;
    }

    /**
     * Converts the `event` into JSON. This is done adding on top of it an instance
     * of {@link JacksonEventDecorator}, where contains the {@link JsonProperty}
     * annotations.
     * 
     * @param event The event to encode.
     * @return the event encoded as JSON.
     * @throws CloudEventException when there is a JSON processing error.
     */
    @Override
    public byte[] encode(final Event<?> event) {
        try {
            return mapper.writeValueAsBytes(new JacksonEventDecorator<>(event));
        } catch (JsonProcessingException e) {
            throw new CloudEventException("Error encoding event to JSON", e);
        }
    }

    @Override
    public <T> Event<T> decode(final InputStream stream, final Class<T> clazz) {
        final JsonNode jsonNode = readJsonNode(stream);

        return new JacksonJsonDataEvent<>(convertEvent(jsonNode, clazz), jsonNode, codecs, clazz);

    }

    private JsonNode readJsonNode(final InputStream stream) {
        try {
            return this.mapper.readTree(stream);
        } catch (IOException e) {
            throw new CloudEventException("Error decoding event from JSON", e);
        }
    }

    private <T> JacksonJsonEvent<T> convertEvent(final JsonNode jsonNode, final Class<T> clazz) {
        final JavaType type = this.mapper.getTypeFactory().constructParametricType(JacksonJsonEvent.class, clazz);

        return this.mapper.convertValue(jsonNode, type);

    }

    public Codec get(final String contentType) {
        return this.codecs.get(contentType);
    };

}
