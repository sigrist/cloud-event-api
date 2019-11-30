package com.github.sigrist.cloudevent.codec.jackson;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.impl.AbstractDecoratorEvent;

public class JsonEvent<T> extends AbstractDecoratorEvent<T> {

    private final EventCodec codec;

    public JsonEvent(final Event<T> origin, final EventCodec codec) {
        super(origin);
        this.codec = codec;
    }
    
    
    @Override
    public String toString() {
        return new String(this.codec.encode(this));
    }

    
}
