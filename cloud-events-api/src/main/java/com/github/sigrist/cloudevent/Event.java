package com.github.sigrist.cloudevent;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * An "event" is a data record expressing an occurrence and its context.
 * 
 * 
 * @see https://github.com/cloudevents/spec/blob/v1.0/spec.md#event
 * 
 *
 * @param <T> The event payload.
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
