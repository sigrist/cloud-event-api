package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;
import java.time.LocalDateTime;

import com.github.sigrist.cloudevent.Codecs;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;
import com.github.sigrist.cloudevent.impl.AbstractEventFactory;
import com.github.sigrist.cloudevent.impl.DataSchemaEventImpl;
import com.github.sigrist.cloudevent.impl.LocalDataTimeEventImpl;
import com.github.sigrist.cloudevent.impl.SubjectEventImpl;

public class MyEventFactory extends AbstractEventFactory implements EventFactory {

    public MyEventFactory() {
        // TODO Change abstract factory to get codecs drom EventCodecs
        super(URI.create("/MyEventFactory"),
                new JacksonJsonEventCodec(new Codecs(new JacksonJsonCodec(), new JacksonXmlCodec())),
                new JacksonJsonCodec(), new JacksonXmlCodec());
    }

    public Event<MyPayload> simpleEvent(final MyPayload payload) {
        return new DataSchemaEventImpl<>(new LocalDataTimeEventImpl<>(
                new SubjectEventImpl<>(this.create("MyPayloadEvent", "application/json", payload), "Subject"),
                LocalDateTime.now()), URI.create("/MyPayloadDataSchema"));
    }

}
