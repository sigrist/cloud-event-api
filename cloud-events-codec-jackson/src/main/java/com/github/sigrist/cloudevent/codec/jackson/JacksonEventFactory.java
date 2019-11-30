package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.impl.AbstractEventFactory;
import com.github.sigrist.cloudevent.impl.DataEventImpl;
import com.github.sigrist.cloudevent.impl.DefaultEventImpl;

public class JacksonEventFactory extends AbstractEventFactory {

    public JacksonEventFactory(final URI source) {
        super(source, new JacksonJsonEventCodec());
    }
    
    protected final <T> Event<T> jsonEvent(final String type, final String contentType, final T data) {
        return new JsonEvent<>(super.create(type, contentType, data), new JacksonJsonEventCodec());
    }


}
