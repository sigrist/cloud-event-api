package com.github.sigrist.cloudevent;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.extensions.DataRefExtension;
import com.github.sigrist.cloudevent.extensions.DistributedTracingExtension;
import com.github.sigrist.cloudevent.impl.AbstractEventFactory;
import com.github.sigrist.cloudevent.impl.ExtensionsEventImpl;
import com.github.sigrist.cloudevent.impl.LocalDataTimeEventImpl;
import com.github.sigrist.cloudevent.impl.SubjectEventImpl;

public class TestEventFactory extends AbstractEventFactory implements EventFactory {

	public TestEventFactory() {
		super(URI.create("/TestEventFactory"));
	}

	public Event<MyPayload> myPayloadEvent(final MyPayload payload) {
		Extension dataRef = new DataRefExtension(URI.create("/xpto"));
		Extension trace = new DistributedTracingExtension("traceParent", "traceState");

		return new ExtensionsEventImpl<>(new ExtensionsEventImpl<>(new LocalDataTimeEventImpl<>(
				new SubjectEventImpl<>(this.create("MyPayload", payload), "Subject"), LocalDateTime.now()), dataRef),
				trace);
	}

	public Event<Void> myVoidEvent() {
		return new SubjectEventImpl<>(this.create("voidEvent"), "void event");
	}
}
