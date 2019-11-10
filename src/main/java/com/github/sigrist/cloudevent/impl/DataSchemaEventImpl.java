package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataSchemaEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	private final Optional<URI> dataSchema;

	public DataSchemaEventImpl(final Event<T> origin, final URI dataSchema) {
		super(origin);
		this.dataSchema = Optional.of(dataSchema);
	}

	@Override
	public Optional<URI> dataSchema() {
		return this.dataSchema;
	}

}
