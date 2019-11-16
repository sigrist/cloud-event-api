package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Event;

public class EventsFactoryTest {

    private final TestEventFactory factory;
    private final URI expectedSource;

    public EventsFactoryTest() {
        this.factory = new TestEventFactory();
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

    public void testMyPayloadEvent() throws IOException {
        final MyPayload payload = new MyPayload(40, "Paulo Sigrist");
        final Event<MyPayload> event = factory.myPayloadEvent(payload);
        final URI payloadDataSchema = URI.create("/MyPayloadDataSchema");

        assertNotNull(event);
        assertNotNull(event.eventId());
        assertEquals("1.0", event.specVersion());
        assertEquals("MyPayloadEvent", event.type());
        assertEquals(expectedSource, event.source());
        assertEquals("Subject", event.subject().get());
        assertFalse(event.time().isEmpty());
        assertEquals("text/plain", event.dataContentType().get());
        assertEquals(payloadDataSchema, event.dataSchema().get());
        assertFalse(event.data().isEmpty());
        assertEquals(payload, event.data().get());
        assertFalse(event.extensions().iterator().hasNext());

    }

    @Test
    public void testMyPayloadEventWithExtension() {
        final MyPayload payload = new MyPayload(40, "Paulo Sigrist");
        final Event<MyPayload> event = factory.myPayloadWithExtension(payload);

        assertNotNull(event);
        assertNotNull(event.eventId());
        assertEquals("1.0", event.specVersion());
        assertEquals("MyPayloadWithExtension", event.type());
        assertEquals(expectedSource, event.source());

    }

}
