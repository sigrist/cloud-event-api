package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataContentTypeEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String theDataContentType;

	public DataContentTypeEventImpl(final Event<T> origin, final String dataContentType) {
		super(origin);
		this.theDataContentType = dataContentType;
	}

	@Override
	public Optional<String> dataContentType() {
		return Optional.ofNullable(this.theDataContentType);
	}
}
