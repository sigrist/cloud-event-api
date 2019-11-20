package com.github.sigrist.cloudevent.codec.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Entry;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;

public class JacksonJsonExtensionTest {
    private final EventCodec codec = new JacksonJsonEventCodec();
    private final MyPayload payload = new MyPayload(40, "Paulo");

    @Test
    public void test() {
        InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/expectedEventWithExtension.json");
        Event<MyPayload> event = codec.decode(stream, MyPayload.class);
        final URI payloadDataSchema = URI.create("/MyPayloadDataSchema");
        final URI expectedSource = URI.create("/MyEventFactory");
        final LocalDateTime expectedTime = LocalDateTime.parse("2019-11-15T14:45:03.650572");

        assertNotNull(event);
        assertEquals("ae2bc7a9-c52b-4246-9683-111f48029a54", event.eventId());
        assertEquals("1.0", event.specVersion());
        assertEquals("MyPayloadEvent", event.type());
        assertEquals(expectedSource, event.source());
        assertEquals("Subject", event.subject().get());
        assertEquals(expectedTime, event.time().get());
        assertEquals("application/json", event.dataContentType().get());
        assertEquals(payloadDataSchema, event.dataSchema().get());
        assertFalse(event.data().isEmpty());
        assertEquals(payload, event.data().get());
        
        final Iterator<Entry<?>> iterator = event.extensions().iterator();
        assertTrue(iterator.hasNext());
        Entry<?> entry = iterator.next();
        
        assertEquals("dataref", entry.name());
        assertEquals("/dataref", entry.value().get());

        assertTrue(iterator.hasNext());
        entry = iterator.next();
        
        assertEquals("numberextension", entry.name());
        assertEquals(27, entry.value().get());

        assertTrue(iterator.hasNext());
        entry = iterator.next();
        
        assertEquals("traceparent", entry.name());
        assertEquals("traceParentValue", entry.value().get());
        
        assertFalse(iterator.hasNext());

    }
}
