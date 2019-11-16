package com.github.sigrist.cloudevent;

public class CloudEventException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7776708168884307105L;

    public CloudEventException(final String message) {
        super(message);
    }

    public CloudEventException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
