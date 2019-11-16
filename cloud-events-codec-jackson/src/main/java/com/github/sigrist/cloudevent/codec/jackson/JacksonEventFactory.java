package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;

import com.github.sigrist.cloudevent.Codecs;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.impl.AbstractEventFactory;

public class JacksonEventFactory extends AbstractEventFactory {

    public JacksonEventFactory(final URI source) {
        super(source, new JacksonJsonEventCodec(new Codecs(new JacksonJsonCodec(), new JacksonXmlCodec())));
    }
    
    public <T> Event<T> event(final String type) {
        return this.create(type);
    }

    protected final <T> Event<T> event(final String type, final String contentType, final T data) {
        return super.create(type, contentType, data);
    }

}
