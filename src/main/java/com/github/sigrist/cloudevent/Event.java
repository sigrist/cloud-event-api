package com.github.sigrist.cloudevent;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

public interface Event<T> {

	String id();

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
