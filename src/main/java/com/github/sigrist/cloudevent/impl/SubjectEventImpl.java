package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class SubjectEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String subject;

	public SubjectEventImpl(final Event<T> origin, final String subject) {
		super(origin);
		this.subject = subject;
	}

	@Override
	public Optional<String> subject() {
		return Optional.of(this.subject);
	}
}
