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

	@Override
	public <T> Event<T> decode(final InputStream stream, final Class<T> clazz) {
		final Object object;

		try {
			object = this.toObject(stream.readAllBytes());
			// TODO IF
			if (object instanceof Event) {
				return (Event<T>) object;
			}
			throw new CloudEventException("Object is not instance of Event: " + object.getClass());
		} catch (IOException e) {
			throw new CloudEventException("Error decoding event object", e);
		}

	}

}
