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
import java.util.UUID;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;

public class DefaultEventImpl<T> implements Event<T> {
    private final String theId;
    private final URI theSource;
    private final String theSpecVersion;
    private final String theType;
    private final Extensions theExtensions;

    public DefaultEventImpl(final URI source, final String type) {
        this.theId = UUID.randomUUID().toString();
        this.theSource = source;
        this.theSpecVersion = "1.0";
        this.theType = type;
        this.theExtensions = new ExtensionsImpl(); // TODO Create empry ExtensionImpl
    }

    @Override
    public String eventId() {
        return this.theId;
    }

    @Override
    public URI source() {
        return this.theSource;
    }

    @Override
    public String specVersion() {
        return this.theSpecVersion;
    }

    @Override
    public String type() {
        return this.theType;
    }

    @Override
    public Optional<String> subject() {
        return Optional.empty();
    }

    @Override
    public Optional<LocalDateTime> time() {
        return Optional.empty();
    }

    @Override
    public Optional<String> dataContentType() {
        return Optional.empty();
    }

    @Override
    public Optional<URI> dataSchema() {
        return Optional.empty();
    }

    @Override
    public Optional<T> data() {
        return Optional.empty();
    }

    @Override
    public Extensions extensions() {
        return this.theExtensions;
    }

}
