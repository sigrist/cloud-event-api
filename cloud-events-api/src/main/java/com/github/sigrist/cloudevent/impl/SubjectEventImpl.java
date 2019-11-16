package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class SubjectEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

    private final String theSubject;

    public SubjectEventImpl(final Event<T> origin, final String subject) {
        super(origin);
        this.theSubject = subject;
    }

    @Override
    public Optional<String> subject() {
        return Optional.of(this.theSubject);
    }
}
