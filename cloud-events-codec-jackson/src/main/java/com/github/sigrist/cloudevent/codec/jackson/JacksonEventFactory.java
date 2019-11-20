package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;

import com.github.sigrist.cloudevent.Codecs;
import com.github.sigrist.cloudevent.impl.AbstractEventFactory;

public class JacksonEventFactory extends AbstractEventFactory {

    public JacksonEventFactory(final URI source) {
        super(source, new JacksonJsonEventCodec(new Codecs(new JacksonJsonCodec(), new JacksonXmlCodec())));
    }

}
