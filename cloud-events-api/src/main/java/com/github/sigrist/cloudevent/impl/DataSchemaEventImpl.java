package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataSchemaEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final URI dataSchemaURI;

	public DataSchemaEventImpl(final Event<T> origin, final URI dataSchema) {
		super(origin);
		this.dataSchemaURI = dataSchema;
	}

	@Override
	public Optional<URI> dataSchema() {
		return Optional.of(this.dataSchemaURI);
	}

}
