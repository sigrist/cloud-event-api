package com.github.sigrist.cloudevent.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;

public class SerializebleCodec implements Codec {

	@Override
	public String encode(final Object data) {
		return this.toString(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T decode(final String target, final Class<T> clazz) {
		final Object o = this.toObject(target);

		if (clazz.isInstance(o)) {
			return (T) o;
		}
		throw new CloudEventException("Error decoding object. Invalid instance tyoe : " + o.getClass());

	}

	@Override
	public String contentType() {
		return "text/plain";
	}

	private Object toObject(final String encoded) {
		byte[] data = Base64.getDecoder().decode(encoded);

		try (final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new CloudEventException("Error decoding object", e);
		}

	}

	private String toString(final Object source) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(source);
			oos.close();
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (IOException e) {
			throw new CloudEventException("Error encoding object", e);
		}

	}

}
