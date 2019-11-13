package com.github.sigrist.cloudevent.impl;

import java.net.URI;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;

public abstract class AbstractEventFactory implements EventFactory {

	private final URI source;

	public AbstractEventFactory(final URI source) {
		this.source = source;
	}

	@Override
	public URI source() {
		return this.source;
	}

	protected final <T> Event<T> create(final String type) {
		return new DefaultEventImpl<>(this.source(), type);
	}

	protected final <T> Event<T> create(final String type, T data) {
		return new DataEventImpl<>(new DefaultEventImpl<>(this.source(), type), data);
	}

}
