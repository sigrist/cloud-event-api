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
package com.github.sigrist.cloudevent;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * An "event" is a data record expressing an occurrence and its context.
 * 
 * @see https://github.com/cloudevents/spec/blob/v1.0/spec.md#event
 * 
 * @param <T> The event payload.
 * 
 * @since 1.0.0
 * 
 */
public interface Event<T> {

    /** Identifies the event. */
    String eventId();

    /** Identifies the context in which an event happened. */
    URI source();

    /** The version of the CloudEvents specification which the event uses. */
    String specVersion();

    /**
     * The value describing the type of event related to the originating occurrence.
     */
    String type();

    /**
     * This describes the subject of the event in the context of the event producer
     * (identified by {@link #source()}).
     */
    Optional<String> subject();

    /** Timestamp of when the occurrence happened. */
    Optional<LocalDateTime> time();

    /** The Content type of data value. */
    Optional<String> dataContentType();

    /** Identifies the schema that {@link #data()} adheres to. */
    Optional<URI> dataSchema();

    /** The event payload. */
    Optional<T> data();

    /** The event extensions. */
    Extensions extensions();
}
