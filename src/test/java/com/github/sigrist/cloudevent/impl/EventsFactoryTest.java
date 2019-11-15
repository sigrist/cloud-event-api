package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.codec.SerializableEventCodec;

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
		assertNotNull(eventVoid.id());
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
	public void testMyPayloadEvent() throws IOException {
		final MyPayload payload = new MyPayload(40, "Paulo Sigrist");
		final Event<MyPayload> event = factory.myPayloadEvent(payload);
		final URI payloadDataSchema = URI.create("/MyPayloadDataSchema");

//		{
//			byte[] data = new SerializableEventCodec().encode(event);
//			FileOutputStream out = new FileOutputStream("event.data");
//			out.write(data);
//			out.close();
//		}
		
		
		assertNotNull(event); 
		assertNotNull(event.id());
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
		assertNotNull(event.id());
		assertEquals("1.0", event.specVersion());
		assertEquals("MyPayloadWithExtension", event.type());
		assertEquals(expectedSource, event.source());

	}
	
	
	@Test
	public void testFromStream() {
		final MyPayload payload = new MyPayload(40, "Paulo Sigrist");
		final URI payloadDataSchema = URI.create("/MyPayloadDataSchema");
		
		
		final InputStream inputStream = EventsFactoryTest.class.getResourceAsStream("/event.data");
		
		final Event<MyPayload> event = this.factory.fromStream(inputStream, MyPayload.class);
		
		assertNotNull(event); 
		assertNotNull(event.id());
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
}
