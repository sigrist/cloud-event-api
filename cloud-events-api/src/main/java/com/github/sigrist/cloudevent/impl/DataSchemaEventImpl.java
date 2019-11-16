package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.util.Optional;

import com.github.sigrist.cloudevent.Event;

public class DataSchemaEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

    private final URI theDataSchema;

    public DataSchemaEventImpl(final Event<T> origin, final URI dataSchema) {
        super(origin);
        this.theDataSchema = dataSchema;
    }

    @Override
    public Optional<URI> dataSchema() {
        return Optional.ofNullable(this.theDataSchema);
    }

}
