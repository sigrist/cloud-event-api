package com.github.sigrist.cloudevent.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class LocalDataTimeEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final LocalDateTime theTime;

    public LocalDataTimeEventImpl(final Event<T> origin, final LocalDateTime time) {
        super(origin);
        this.theTime = time;
    }

    @Override
    public Optional<LocalDateTime> time() {
        return Optional.of(this.theTime);
    }
}