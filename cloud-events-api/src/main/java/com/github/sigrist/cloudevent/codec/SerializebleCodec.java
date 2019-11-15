package com.github.sigrist.cloudevent.codec;

import java.util.Base64;

import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.impl.BaseSerializableCodec;

public class SerializebleCodec extends BaseSerializableCodec implements Codec {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String encode(final Object object) {
		return Base64.getEncoder().encodeToString(this.toBytes(object));
	}

	@Override
	public <T> T decode(final String target, final Class<T> clazz) {
		byte[] data = Base64.getDecoder().decode(target);
		final Object o = this.toObject(data);

		// TODO if
		if (clazz.isInstance(o)) {
			return (T) o;
		}
		throw new CloudEventException("Error decoding object. Invalid instance tyoe : " + o.getClass());

	}

	@Override
	public String contentType() {
		return "text/plain";
	}


}
