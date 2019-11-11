package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {
	private final Optional<T> data;

	public DataEventImpl(final Event<T> origin, final T data) {
		super(origin);
		this.data = Optional.of(data);
	}

	@Override
	public Optional<T> data() {
		return this.data;
	}

}
