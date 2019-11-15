package com.github.sigrist.cloudevent.impl;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extension;
import com.github.sigrist.cloudevent.Extensions;

public class ExtensionsEventImpl<T> extends AbstractDecoratorEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Extensions extensions;

	public ExtensionsEventImpl(final Event<T> origin, final Extension extension) {
		super(origin);
		this.extensions = new ExtensionsImpl(origin.extensions(), extension);
	}

	@Override
	public Extensions extensions() {
		return this.extensions;
	}
}
