package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;
import com.github.sigrist.cloudevent.codec.SerializableEventCodec;
import com.github.sigrist.cloudevent.codec.SerializebleCodec;

public class TestEventFactory extends AbstractEventFactory implements EventFactory {

    public TestEventFactory() {
        super(URI.create("/TestEventFactory"), new SerializableEventCodec(), new SerializebleCodec());
    }

    public Event<Void> myVoidEvent() {
        return this.create("voidEvent");
    }

    public Event<MyPayload> myPayloadEvent(final MyPayload payload) {
        return new DataSchemaEventImpl<>(new LocalDataTimeEventImpl<>(
                new SubjectEventImpl<>(this.create("MyPayloadEvent", "text/plain", payload), "Subject"),
                LocalDateTime.now()), URI.create("/MyPayloadDataSchema"));
    }

    public Event<MyPayload> myPayloadWithExtension(final MyPayload payload) {
        return this.create("MyPayloadWithExtension", "text/plain", payload);
    }

}
