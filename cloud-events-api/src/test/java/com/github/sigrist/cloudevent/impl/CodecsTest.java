package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Codecs;

public class CodecsTest {

    @Test
    public void testEmptyCodecs() {
        final Codecs codecs = new Codecs();
        
        final Codec codec = codecs.get("application/json");
        
        assertNotNull(codec);
        assertThrows(UnsupportedOperationException.class, () -> codec.contentType());
        assertThrows(UnsupportedOperationException.class, () -> codec.encode("test"));
        assertThrows(UnsupportedOperationException.class, () -> codec.decode("test", String.class));
        
    }

    @Test
    public void testSimpleCodecs() {
        final Codecs codecs = new Codecs(new MyCodec());
        
        final Codec codec = codecs.get("text/plain");
        
        assertNotNull(codec);
        assertEquals("text/plain", codec.contentType());
        assertThrows(UnsupportedOperationException.class, () -> codec.encode("test"));
        assertThrows(UnsupportedOperationException.class, () -> codec.decode("test", String.class));
        
    }
    
    class MyCodec implements Codec {

        @Override
        public String encode(Object payload) {
            throw new UnsupportedOperationException("Encode: not a testable operation");
        }

        @Override
        public <T> T decode(String object, Class<T> clazz) {
            throw new UnsupportedOperationException("Decode: not a testable operation");
        }

        @Override
        public String contentType() {
            return "text/plain";
        }
        
    }

}
