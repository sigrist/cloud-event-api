package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;
import com.github.sigrist.cloudevent.impl.DataSchemaEventImpl;
import com.github.sigrist.cloudevent.impl.LocalDataTimeEventImpl;
import com.github.sigrist.cloudevent.impl.SubjectEventImpl;

public class MyEventFactory extends JacksonEventFactory implements EventFactory {

    public MyEventFactory() {
        super(URI.create("/MyEventFactory"));
    }

    public Event<MyPayload> simpleEvent(final MyPayload payload) {
        return new DataSchemaEventImpl<>(new LocalDataTimeEventImpl<>(
                new SubjectEventImpl<>(this.create("MyPayloadEvent", "application/json", payload), "Subject"),
                LocalDateTime.now()), URI.create("/MyPayloadDataSchema"));
    }

}
