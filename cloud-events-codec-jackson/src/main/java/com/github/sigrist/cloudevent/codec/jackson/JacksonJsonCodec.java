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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;

public class JacksonJsonCodec implements Codec {

    private final ObjectMapper mapper;

    public JacksonJsonCodec() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public String encode(final Object source) {

        try {
            return this.mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new CloudEventException("Error encoding object to JSON", e);
        }
    }

    public <T> T decode(final String object, final Class<T> clazz) {
        try {
            return this.mapper.readValue(object, clazz);
        } catch (JsonProcessingException e) {
            throw new CloudEventException("Error decoding JSON", e);
        }
    }

    @Override
    public String contentType() {
        return "application/json";
    }

}
