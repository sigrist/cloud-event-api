package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Event;

public class EventsFactoryTest {

	private final TestEventFactory factory;

	public EventsFactoryTest() {
		this.factory = new TestEventFactory();
	}

	@Test
	public void testVoidEvent() {
		URI expectedSource = URI.create("/TestEventFactory");

		Event<Void> eventVoid = factory.myVoidEvent();

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

	public void doo() {

		final MyPayload payload = new MyPayload(41, "Paulo Sigrist");

		Event<MyPayload> event = factory.myPayloadEvent(payload);

		System.out.println(event.data().get());
		System.out.println(event.subject().get());
		System.out.println(event.extensions());

	}
}
