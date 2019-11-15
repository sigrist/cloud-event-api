package com.github.sigrist.cloudevent;

import java.io.InputStream;

public interface EventCodec {

	byte[] encode(Event<?> event);
	
	<T> Event<T> decode(InputStream stream, Class<T> clazz);
}
