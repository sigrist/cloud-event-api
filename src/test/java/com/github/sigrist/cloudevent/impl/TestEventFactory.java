package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;

public class TestEventFactory extends AbstractEventFactory implements EventFactory {

	public TestEventFactory() {
		super(URI.create("/TestEventFactory"));
	}

	public Event<Void> myVoidEvent() {
		return this.create("voidEvent");
	}
	
	public Event<MyPayload> myPayloadEvent(final MyPayload payload) {
		return new DataContentTypeEventImpl<>(new DataSchemaEventImpl<>(
				new LocalDataTimeEventImpl<>(new SubjectEventImpl<>(this.create("MyPayloadEvent", payload), "Subject"),
						LocalDateTime.now()),
				URI.create("/MyPayloadDataSchema")), "application/json");
	}

	
	public Event<MyPayload> myPayloadWithExtension(final MyPayload payload) {
		return this.create("MyPayloadWithExtension", payload);
	}
	
}
