package com.github.sigrist.cloudevent.impl;

import java.io.InputStream;
import java.net.URI;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;

class MyEventCodec implements EventCodec {

    @Override
    public byte[] encode(final Event<?> event) {
        throw new UnsupportedOperationException("Unit tests. Should not be called");
    }

    @Override
    public <T> Event<T> decode(final InputStream stream, final Class<T> payloadClazz) {
        return new DefaultEventImpl<>(URI.create("/MyEventCodec"), "java.lang.String");
    }

    @Override
    public Codec get(final String contentType) {
        return new Codec() {

            @Override
            public String encode(final Object payload) {
                return payload.toString();
            }

            @SuppressWarnings("unchecked")
            @Override
            public <T> T decode(final String object, final Class<T> clazz) {
                return (T) object;
            }

            @Override
            public String contentType() {
                return "text/plain";
            }
            
        };
    }
    
}