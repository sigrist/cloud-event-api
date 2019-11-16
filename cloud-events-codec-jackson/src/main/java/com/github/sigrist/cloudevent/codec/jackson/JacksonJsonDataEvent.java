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

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Codecs;
import com.github.sigrist.cloudevent.impl.AbstractDecoratorEvent;

public class JacksonJsonDataEvent<T> extends AbstractDecoratorEvent<T> {

    private final Codecs codecs;
    private final Class<T> clazz;
    private final JsonNode rawJson;
    private final ObjectMapper mapper;

    public JacksonJsonDataEvent(final JacksonJsonEvent<T> origin, final JsonNode rawJson, final Codecs codecs,
            final Class<T> clazz) {
        super(origin);
        this.mapper = new ObjectMapper();
        this.codecs = codecs;
        this.clazz = clazz;
        this.rawJson = rawJson;
    }

    @Override
    public Optional<T> data() {

        Optional<JsonNode> data = this.rawData();

        return data.map(this::decodedData).orElse(Optional.empty());

    }

    private Codec codec() {
        return this.codecs.get(this.dataContentType().orElseThrow());
    }

    private Optional<T> decodedData(final JsonNode node) {
        String rawValue = value(node);

        return Optional.ofNullable(codec().decode(rawValue, this.clazz));
    }

    private String value(final JsonNode node) {
        try {
            return node.isContainerNode() ? mapper.writeValueAsString(node) : node.asText();
        } catch (JsonProcessingException e) {
            throw new CloudEventException("Error readind data field", e);
        }
    }

    private Optional<JsonNode> rawData() {
        return this.rawJson.has("data") && this.dataContentType().isPresent() ? Optional.of(this.rawJson.get("data"))
                : Optional.empty();
    }

}
