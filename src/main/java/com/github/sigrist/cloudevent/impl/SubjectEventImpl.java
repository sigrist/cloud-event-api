package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class SubjectEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	private final Optional<String> subject;

	public SubjectEventImpl(final Event<T> origin, final String subject) {
		super(origin);
		this.subject = Optional.of(subject);
	}

	@Override
	public Optional<String> subject() {
		return this.subject;
	}
}
