package com.github.sigrist.cloudevent;

/**
 * API Exception.
 * 
 * @author sigrist
 *
 */
public class CloudEventException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7776708168884307105L;

    /**
     * Create an expcetion with a message.
     * 
     * @param message The exception message
     */
    public CloudEventException(final String message) {
        super(message);
    }

    /**
     * Create an exception with a message and cause.
     * 
     * @param message The exception message.
     * @param cause   The root cause.
     */
    public CloudEventException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
