package com.github.sigrist.cloudevent;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

public interface Event<T> extends Serializable {

	String eventId();

	URI source();

	String specVersion();

	String type();

	Optional<String> subject();

	Optional<LocalDateTime> time();

	Optional<String> dataContentType();

	Optional<URI> dataSchema();

	Optional<T> data();

	Extensions extensions();
}
