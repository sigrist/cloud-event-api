package com.github.sigrist.cloudevent;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.impl.AbstractEventFactory;
import com.github.sigrist.cloudevent.impl.LocalDataTimeEventImpl;
import com.github.sigrist.cloudevent.impl.SubjectEventImpl;

public class TestEventFactory extends AbstractEventFactory implements EventFactory {

	public TestEventFactory() {
		super(URI.create("/TestEventFactory"));
	}

	public Event<MyPayload> myPayloadEvent(final MyPayload payload) {
		return new LocalDataTimeEventImpl<>(new SubjectEventImpl<>(this.create("MyPayload", payload), "Subject"),
				LocalDateTime.now());
	}

	public Event<Void> myVoidEvent() {
		return new SubjectEventImpl<>(this.create("voidEvent"), "void event");
	}
}
