package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;

public abstract class AbstractDecoratorEvent<T> implements Event<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
