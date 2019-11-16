package com.github.sigrist.cloudevent.impl;

import java.io.InputStream;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;

class MyEventCodec implements EventCodec {

    @Override
    public byte[] encode(Event<?> event) {
        throw new UnsupportedOperationException("Unit tests. Should not be called");
    }

    @Override
    public <T> Event<T> decode(InputStream stream, Class<T> payloadClazz) {
        throw new UnsupportedOperationException("Unit tests. Should not be called");
    }

    @Override
    public Codec get(String contentType) {
        return new Codec() {

            @Override
            public String encode(Object payload) {
                return payload.toString();
            }

            @SuppressWarnings("unchecked")
            @Override
            public <T> T decode(String object, Class<T> clazz) {
                return (T) object;
            }

            @Override
            public String contentType() {
                return "text/plain";
            }
            
        };
    }
    
}