package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;

public class DataEventImpl<T> extends DataContentTypeEventImpl<T> implements Event<T> {
    private final String theData;
    private final Codec theCodec;
    private final Class<T> theClazz;

    @SuppressWarnings("unchecked")
    public DataEventImpl(final Event<T> origin, final Codec codec, final Object data) {
        super(origin, codec.contentType());
        this.theCodec = codec;
        this.theClazz = (Class<T>) data.getClass();

        this.theData = this.theCodec.encode(data);
    }

    @Override
    public Optional<T> data() {
        return Optional.ofNullable(this.theCodec.decode(this.theData, this.theClazz));
    }

}
