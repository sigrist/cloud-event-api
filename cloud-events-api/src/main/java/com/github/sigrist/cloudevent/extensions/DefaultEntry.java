package com.github.sigrist.cloudevent.extensions;

import java.util.Optional;

import com.github.sigrist.cloudevent.Entry;

public class DefaultEntry<T> implements Entry<T> {

    private final String name;
    private final T value;

    public DefaultEntry(final String name, final T value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public final String name() {
        return this.name;
    }

    @Override
    public final Optional<T> value() {
        return Optional.ofNullable(this.value);
    }

}
