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

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.impl.AbstractDecoratorEvent;

@JsonPropertyOrder({ "specversion", "type", "source", "subject", "id", "time", "datacontenttype", "dataschema",
        "data" })
public class JacksonEventDecorator<T> extends AbstractDecoratorEvent<T> {

    public JacksonEventDecorator(Event<T> origin) {
        super(origin);
    }

    @JsonProperty("specversion")
    public String jacksonSpecVersion() {
        return super.specVersion();
    }

    @JsonProperty("type")
    public String jacksonType() {
        return super.type();
    }

    @JsonProperty("source")
    public URI jacksonSource() {
        return super.source();
    }

    @JsonProperty("subject")
    @Override
    public Optional<String> subject() {
        return super.subject();
    }

    @JsonProperty("id")
    public String jacksonId() {
        return super.eventId();
    }

    @JsonProperty("time")
    @Override
    public Optional<LocalDateTime> time() {
        return super.time();
    }

    @JsonProperty("datacontenttype")
    @Override
    public Optional<String> dataContentType() {
        return super.dataContentType();
    }

    @JsonProperty("dataschema")
    @Override
    public Optional<URI> dataSchema() {
        return super.dataSchema();
    }

    @JsonProperty("data")
    @Override
    public Optional<T> data() {
        return super.data();
    }


}
