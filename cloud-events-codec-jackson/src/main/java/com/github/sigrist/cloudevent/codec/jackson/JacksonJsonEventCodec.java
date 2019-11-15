package com.github.sigrist.cloudevent.codec.jackson;

import java.io.InputStream;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;

public class JacksonJsonEventCodec implements EventCodec {

	@Override
	public byte[] encode(Event<?> event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Event<T> decode(InputStream stream, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
