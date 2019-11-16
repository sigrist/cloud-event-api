package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;

public class EventDecoratorTest {

    private Event<Void> origin;
    private final String contentType = "application/json";
    private final URI schema = URI.create("/shcema1");
    private final String subject = "subject1";
    private final LocalDateTime time = LocalDateTime.parse("2019-11-15T14:45:03.650572");

    @BeforeEach
    public void beforeTest() {
        this.origin = new DefaultEventImpl<>(URI.create("/default"), "Default event");
    }

    @Test
    public void testDataContentTypeDecorator() {
        final Event<Void> event = new DataContentTypeEventImpl<>(this.origin, this.contentType);

        this.assertOrigin(event);
        assertEquals(event.dataContentType().get(), this.contentType);
    }

    @Test
    public void testDataSchemaDecorator() {
        final Event<Void> event = new DataSchemaEventImpl<>(this.origin, this.schema);

        this.assertOrigin(event);
        assertEquals(event.dataSchema().get(), this.schema);
    }

    @Test
    public void testSubjectDecorator() {
        final Event<Void> event = new SubjectEventImpl<>(this.origin, this.subject);

        this.assertOrigin(event);
        assertEquals(event.subject().get(), this.subject);
    }

    @Test
    public void testTimeDecorator() {
        final Event<Void> event = new LocalDataTimeEventImpl<>(origin, this.time);

        this.assertOrigin(event);
        assertEquals(event.time().get(), this.time);
    }

    @Test
    public void testDataDecorator() {
        final String data = "UnitTest";

        final Event<String> event = new DefaultEventImpl<>(URI.create("/default"), "Default event");
        final Event<String> dataEvent = new DataEventImpl<>(event, new UnitTestCodec(), data);

        this.assertOrigin(dataEvent);
        assertEquals(dataEvent.data().get(), data);

    }

    @Test
    public void testAllDecorator() {

        final Event<Void> event = new DataSchemaEventImpl<>(
                new SubjectEventImpl<>(new LocalDataTimeEventImpl<>(
                        new DataContentTypeEventImpl<>(this.origin, this.contentType), this.time), this.subject),
                this.schema);

        this.assertOrigin(event);
        assertEquals(event.dataContentType().get(), this.contentType);
        assertEquals(event.dataSchema().get(), this.schema);
        assertEquals(event.subject().get(), this.subject);
        assertEquals(event.time().get(), this.time);

    }

    private void assertOrigin(final Event<?> event) {
        assertEquals(URI.create("/default"), event.source());
        assertEquals("Default event", event.type());
    }

    class UnitTestCodec implements Codec {

        @Override
        public String encode(Object payload) {
            return payload.toString();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T decode(String object, Class<T> clazz) {
            return (T) object;
        }

        @Override
        public String contentType() {
            return "text/plain";
        }

    }
}
