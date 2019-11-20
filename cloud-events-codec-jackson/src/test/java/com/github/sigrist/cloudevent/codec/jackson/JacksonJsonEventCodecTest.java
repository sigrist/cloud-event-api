package com.github.sigrist.cloudevent.codec.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.LocalDateTime;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.impl.SubjectEventImpl;

public class JacksonJsonEventCodecTest {

    private final EventCodec codec = new JacksonJsonEventCodec();
    private final MyPayload payload = new MyPayload(40, "Paulo");
    private final MyEventFactory factory = new MyEventFactory();

    @Test
    public void testEncode() throws IOException, JSONException {
        final Event<MyPayload> event = factory.simpleEvent(payload);

        final byte[] data = codec.encode(event);

        final String jsonActual = new String(data);

        // ID and time are not validated cause they are dynamically generated
        CustomComparator comparator = new CustomComparator(JSONCompareMode.STRICT_ORDER,
                new Customization("id", (o1, o2) -> true), new Customization("time", (o1, o2) -> true));

        JSONAssert.assertEquals(expected(), jsonActual, comparator);

    }

    @Test
    public void testDecode() {
        InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/expectedEvent.json");
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
        assertFalse(event.extensions().iterator().hasNext());
    }

    @Test
    public void testDecodeXml() throws JsonMappingException, JsonProcessingException {

        InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/expectedEventXml.json");
        Event<MyPayload> event = codec.decode(stream, MyPayload.class);
        final URI payloadDataSchema = URI.create("/MyPayloadDataSchemaXml");
        final URI expectedSource = URI.create("/MyEventFactory");
        final LocalDateTime expectedTime = LocalDateTime.parse("2019-11-15T14:45:03.650572");

        assertNotNull(event);
        assertEquals("ae2bc7a9-c52b-4246-9683-111f48029a54", event.eventId());
        assertEquals("1.0", event.specVersion());
        assertEquals("MyPayloadEvent", event.type());
        assertEquals(expectedSource, event.source());
        assertEquals("Subject", event.subject().get());
        assertEquals(expectedTime, event.time().get());
        assertEquals("text/xml", event.dataContentType().get());
        assertEquals(payloadDataSchema, event.dataSchema().get());
        assertFalse(event.data().isEmpty());
        assertEquals(payload, event.data().get());
        assertFalse(event.extensions().iterator().hasNext());

    }

    @Test
    public void testNoData() {
        InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/noDataEvent.json");
        Event<Void> event = codec.decode(stream, Void.class);
        final URI expectedSource = URI.create("/MyEventFactory");
        final LocalDateTime expectedTime = LocalDateTime.parse("2019-11-15T14:45:03.650572");

        assertNotNull(event);
        assertEquals("ae2bc7a9-c52b-4246-9683-111f48029a54", event.eventId());
        assertEquals("1.0", event.specVersion());
        assertEquals("VoidEvent", event.type());
        assertEquals(expectedSource, event.source());
        assertEquals("Subject", event.subject().get());
        assertEquals(expectedTime, event.time().get());
        assertTrue(event.data().isEmpty());
        assertFalse(event.extensions().iterator().hasNext());

    }

    @Test
    public void testDataWithNoContentType() {
        InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/noDataContentTypeEvent.json");

        assertThrows(CloudEventException.class, () -> codec.decode(stream, Void.class));

    }

    @Test
    public void testInvalidStream() {
        InputStream stream = new ByteArrayInputStream("invalid".getBytes());

        assertThrows(CloudEventException.class, () -> codec.decode(stream, Void.class));

    }

    @Test
    public void testInvalidEvent() {
        final Event<Void> event = new SubjectEventImpl<>(null, "Null origin");

        assertThrows(CloudEventException.class, () -> codec.encode(event));

    }
    
    @Test
    public void testJacksonJsonCodecEncode() {
        JacksonJsonCodec jsonCodec = new JacksonJsonCodec();
        
        assertThrows(CloudEventException.class, () -> jsonCodec.encode(new MyErrorOject()));
    }

    @Test
    public void testJacksonJsonCodecDecode() {
        JacksonJsonCodec jsonCodec = new JacksonJsonCodec();
        
        assertThrows(CloudEventException.class, () -> jsonCodec.decode("a", MyErrorOject.class));
    }

    private String expected() throws IOException {
        final InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/expectedEvent.json");
        return new String(stream.readAllBytes());
    }
}
