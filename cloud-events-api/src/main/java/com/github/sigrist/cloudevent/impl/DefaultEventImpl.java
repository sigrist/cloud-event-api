package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;

public class DefaultEventImpl<T> implements Event<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		this.theExtensions = new ExtensionsImpl();
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
