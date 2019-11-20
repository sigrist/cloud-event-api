package com.github.sigrist.cloudevent.impl;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;

public class MyEventFactory extends AbstractEventFactory implements EventFactory {

    public MyEventFactory() {
        super(URI.create("/TestEventFactory"), new MyEventCodec());
    }

    public Event<Void> myVoidEvent() {
        return this.create("voidEvent");
    }

    public Event<String> myPayloadEvent(final String payload) {
        return new SubjectEventImpl<>(new LocalDataTimeEventImpl<>(
                new DataSchemaEventImpl<>(this.create("MyPayloadEvent", "text/plain", payload),
                        URI.create("/MyPayloadDataSchema")),
                LocalDateTime.now()), "Subject");
    }

}
