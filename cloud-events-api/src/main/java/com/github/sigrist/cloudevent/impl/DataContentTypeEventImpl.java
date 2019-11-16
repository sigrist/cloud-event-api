package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataContentTypeEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

    private final String theContentType;

    public DataContentTypeEventImpl(final Event<T> origin, final String dataContentType) {
        super(origin);
        this.theContentType = dataContentType;
    }

    @Override
    public Optional<String> dataContentType() {
        return Optional.ofNullable(this.theContentType);
    }
}
