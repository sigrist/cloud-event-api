package com.github.sigrist.cloudevent.impl;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.EventFactory;

public abstract class AbstractEventFactory implements EventFactory {

	private final URI source;
	private final Map<String, Codec> codecs;
	private final EventCodec eventCodec;

	public AbstractEventFactory(final URI source, final EventCodec eventCodec, final Codec... codecs) {
		this.source = source;
		this.eventCodec = eventCodec;
		this.codecs = new HashMap<>();

		// Add the codecs
		for (int i = 0; i < codecs.length; i++) {
			final Codec c = codecs[i];
			this.codecs.put(c.contentType(), c);
		}
	}

	@Override
	public URI source() {
		return this.source;
	}

	protected final <T> Event<T> create(final String type) {
		return new DefaultEventImpl<>(this.source(), type);
	}

	protected final <T> Event<T> create(final String type, final String contentType, final T data) {
		final Codec codec = this.codecs.get(contentType);

		// TODO Check if codec is not null
		return new DataEventImpl<>(new DefaultEventImpl<>(this.source(), type), codec, data);
	}
	
	public final <T> Event<T> fromStream(final InputStream inputStream, final Class<T> clazz) {
		return this.eventCodec.decode(inputStream, clazz);
	}

}
