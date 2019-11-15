package com.github.sigrist.cloudevent.codec;

import java.io.IOException;
import java.io.InputStream;

import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.impl.BaseSerializableCodec;

public class SerializableEventCodec extends BaseSerializableCodec implements EventCodec {

	@Override
	public byte[] encode(final Event<?> event) {
		return this.toBytes(event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Event<T> decode(final InputStream stream, final Class<T> clazz) {
		final Object object;

		try {
			object = this.toObject(stream.readAllBytes());
			if (object instanceof Event) {
				return (Event<T>) object;
			}
			return null;
		} catch (IOException e) {
			throw new CloudEventException("Error decoding event object", e);
		}

	}

}
