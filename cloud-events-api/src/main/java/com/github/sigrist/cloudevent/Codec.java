package com.github.sigrist.cloudevent;

import java.io.Serializable;

/**
 * Translate the event payload based on the {@link #contentType()}.
 * 
 * @author sigrist
 *
 */
public interface Codec extends Serializable {

    /**
     * Encode the payload into the content type.
     * 
     * @param payload The payload to convert
     * @return The payload converted as String
     */
    String encode(Object payload);

    /**
     * Decode the object to the payload.
     * 
     * @param object The string to convert
     * @param clazz  The payload class
     * @return an instance of the payload
     */
    <T> T decode(String object, Class<T> clazz);

    /**
     * @return The codec content type.
     */
    String contentType();
}
