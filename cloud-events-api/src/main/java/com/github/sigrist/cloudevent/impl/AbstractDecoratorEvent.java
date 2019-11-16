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
package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;

public abstract class AbstractDecoratorEvent<T> implements Event<T> {

    private final Event<T> origin;

    public AbstractDecoratorEvent(final Event<T> origin) {
        this.origin = origin;
    }

    @Override
    public final String eventId() {
        return this.origin.eventId();
    }

    @Override
    public final URI source() {
        return this.origin.source();
    }

    @Override
    public final String specVersion() {
        return this.origin.specVersion();
    }

    @Override
    public final String type() {
        return this.origin.type();
    }

    @Override
    public Optional<String> subject() {
        return this.origin.subject();
    }

    @Override
    public Optional<LocalDateTime> time() {
        return this.origin.time();
    }

    @Override
    public Optional<String> dataContentType() {
        return this.origin.dataContentType();
    }

    @Override
    public Optional<URI> dataSchema() {
        return this.origin.dataSchema();
    }

    @Override
    public Optional<T> data() {
        return this.origin.data();
    }

    @Override
    public Extensions extensions() {
        return this.origin.extensions();
    }

}
