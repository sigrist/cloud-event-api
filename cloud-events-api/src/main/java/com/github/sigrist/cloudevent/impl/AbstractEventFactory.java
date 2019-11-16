package com.github.sigrist.cloudevent.impl;

import java.io.InputStream;
import java.net.URI;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Codecs;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.EventFactory;

public abstract class AbstractEventFactory implements EventFactory {

	private final URI theSource;
	private final Codecs theCodecs;
	private final EventCodec theEventCodec;

	public AbstractEventFactory(final URI source, final EventCodec eventCodec, final Codec... codecs) {
		this.theSource = source;
		this.theEventCodec = eventCodec;
		this.theCodecs = new Codecs(codecs);
	}

	@Override
	public URI source() {
		return this.theSource;
	}

	protected final <T> Event<T> create(final String type) {
		return new DefaultEventImpl<>(this.source(), type);
	}

	protected final <T> Event<T> create(final String type, final String contentType, final T data) {
		final Codec codec = this.theCodecs.get(contentType);

		return new DataEventImpl<>(new DefaultEventImpl<>(this.source(), type), codec, data);
	}

	public final <T> Event<T> fromStream(final InputStream inputStream, final Class<T> clazz) {
		return this.theEventCodec.decode(inputStream, clazz);
	}

}
