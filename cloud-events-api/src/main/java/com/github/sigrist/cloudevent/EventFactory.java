package com.github.sigrist.cloudevent;

import java.net.URI;

/**
 * Factory to create events.
 * 
 * @author sigrist
 *
 */
public interface EventFactory {

    /** Identifies the context in which an event happened. */
    URI source();

}
