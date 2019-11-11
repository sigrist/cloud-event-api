package com.github.sigrist.cloudevent.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class LocalDataTimeEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	private final Optional<LocalDateTime> time;

	public LocalDataTimeEventImpl(final Event<T> origin, final LocalDateTime time) {
		super(origin);
		this.time = Optional.of(time);
	}

	@Override
	public Optional<LocalDateTime> time() {
		return this.time;
	}
}