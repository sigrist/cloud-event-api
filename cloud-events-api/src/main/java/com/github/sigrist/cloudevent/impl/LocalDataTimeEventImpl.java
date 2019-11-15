package com.github.sigrist.cloudevent.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class LocalDataTimeEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final LocalDateTime time;

	public LocalDataTimeEventImpl(final Event<T> origin, final LocalDateTime time) {
		super(origin);
		this.time = time;
	}

	@Override
	public Optional<LocalDateTime> time() {
		return Optional.of(this.time);
	}
}