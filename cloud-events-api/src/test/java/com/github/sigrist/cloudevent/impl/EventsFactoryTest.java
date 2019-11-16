package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Event;

public class EventsFactoryTest {

    private final MyEventFactory factory;
    private final URI expectedSource;

    public EventsFactoryTest() {
        this.factory = new MyEventFactory();
        this.expectedSource = URI.create("/TestEventFactory");
    }

    @Test
    public void testVoidEvent() {
        final Event<Void> eventVoid = factory.myVoidEvent();

        assertNotNull(eventVoid);
        assertNotNull(eventVoid.eventId());
        assertEquals("1.0", eventVoid.specVersion());
        assertEquals("voidEvent", eventVoid.type());
        assertEquals(expectedSource, eventVoid.source());
        assertTrue(eventVoid.subject().isEmpty());
        assertTrue(eventVoid.time().isEmpty());
        assertTrue(eventVoid.dataContentType().isEmpty());
        assertTrue(eventVoid.dataSchema().isEmpty());
        assertTrue(eventVoid.data().isEmpty());
        assertFalse(eventVoid.extensions().iterator().hasNext());

    }

    @Test
    public void testEventWithData() {
        final Event<String> event = factory.myPayloadEvent("MyData");

        assertNotNull(event);
        assertNotNull(event.eventId());
        assertEquals("1.0", event.specVersion());
        assertEquals("MyPayloadEvent", event.type());
        assertEquals(expectedSource, event.source());
        assertFalse(event.subject().isEmpty());
        assertFalse(event.time().isEmpty());
        assertEquals("text/plain", event.dataContentType().get());
        assertFalse(event.dataSchema().isEmpty());
        assertEquals("MyData", event.data().get());
        assertFalse(event.extensions().iterator().hasNext());

    }

}
