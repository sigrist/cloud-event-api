package com.github.sigrist.cloudevent.codec.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.impl.Codecs;

public class JacksonJsonEventCodecTest {

	private final EventCodec codec = new JacksonJsonEventCodec(new Codecs(new JacksonJsonCodec()));
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

		assertNotNull(event);
		assertNotNull(event.id());
		assertEquals("1.0", event.specVersion());
		assertEquals("MyPayloadEvent", event.type());
		assertEquals(expectedSource, event.source());
		assertEquals("Subject", event.subject().get());
		assertFalse(event.time().isEmpty());
		assertEquals("application/json", event.dataContentType().get());
		assertEquals(payloadDataSchema, event.dataSchema().get());
		assertFalse(event.data().isEmpty());
		assertEquals(payload, event.data().get());
		assertFalse(event.extensions().iterator().hasNext());

	}

	private String expected() throws IOException {
		final InputStream stream = JacksonJsonEventCodecTest.class.getResourceAsStream("/expectedEvent.json");
		return new String(stream.readAllBytes());
	}

}
