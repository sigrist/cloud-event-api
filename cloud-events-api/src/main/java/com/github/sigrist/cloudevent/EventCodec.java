package com.github.sigrist.cloudevent;

import java.io.InputStream;

/**
 * Translate the event object into JSON Format.
 * 
 * @author sigrist
 *
 */
public interface EventCodec {

    /**
     * Encode the event into JSON.
     * 
     * @param event The event to convert
     * @return a byte array with the Event in JSON format
     */
    byte[] encode(Event<?> event);

    /**
     * Decode the {@link InputStream} bytes to an {@link Event}.
     * 
     * @param stream       The InputStream with the bytes
     * @param payloadClazz The playload class.
     * @return an Event instance.
     */
    <T> Event<T> decode(InputStream stream, Class<T> payloadClazz);
}
