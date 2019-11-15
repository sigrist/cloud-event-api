package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataContentTypeEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String dataContentType;

	public DataContentTypeEventImpl(final Event<T> origin, final String dataContentType) {
		super(origin);
		this.dataContentType = dataContentType;
	}

	@Override
	public Optional<String> dataContentType() {
		return Optional.of(this.dataContentType);
	}
}
