package com.github.sigrist.cloudevent.impl;

import java.io.InputStream;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;

class MyEventCodec implements EventCodec {

    @Override
    public byte[] encode(Event<?> event) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Event<T> decode(InputStream stream, Class<T> payloadClazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Codec get(String contentType) {
        return new Codec() {

            @Override
            public String encode(Object payload) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public <T> T decode(String object, Class<T> clazz) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String contentType() {
                // TODO Auto-generated method stub
                return null;
            }
            
        };
    }
    
}