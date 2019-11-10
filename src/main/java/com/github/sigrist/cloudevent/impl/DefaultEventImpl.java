package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;

public class DefaultEventImpl<T> implements Event<T> {
	private final String id;
	private final URI source;
	private final String specVersion;
	private final String type;
	private final Extensions extensions;

	public DefaultEventImpl(final URI source, final String type) {
		this.id = UUID.randomUUID().toString();
		this.source = source;
		this.specVersion = "1.0";
		this.type = type;
		this.extensions = new ExtensionsImpl();
	}

	@Override
	public String id() {
		return this.id;
	}

	@Override
	public URI source() {
		return this.source;
	}

	@Override
	public String specVersion() {
		return this.specVersion;
	}

	@Override
	public String type() {
		return this.type;
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
		return this.extensions;
	}

}
