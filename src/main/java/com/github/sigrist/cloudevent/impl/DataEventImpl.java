package com.github.sigrist.cloudevent.impl;

import java.util.Optional;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;

public class DataEventImpl<T> extends DataContentTypeEventImpl<T> implements Event<T> {
	private final String data;
	private final Codec codec;
	private final Class<T> clazz;

	public DataEventImpl(final Event<T> origin, final Codec codec, final T data) {
		super(origin, codec.contentType());
		this.codec = codec;
		this.clazz = (Class<T>) data.getClass();
		
		this.data = this.codec.encode(data);
	}

	@Override
	public Optional<T> data() {
		return Optional.of(this.codec.decode(this.data, this.clazz));
	}
	
	

}
