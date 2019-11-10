package com.github.sigrist.cloudevent.impl;

import java.net.URI;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventFactory;

public abstract class AbstractEventFactory implements EventFactory {

	private final URI source;
	
	public AbstractEventFactory(final URI source) {
		this.source = source;
	}
	
	@Override
	public URI source() {
		return this.source;
	}
	
	@Override
	public <T> Event<T> create(final String type) {
		
		Event<T> event = new DefaultEventImpl<>(this.source, type);
		
		event = new SubjectEventImpl<>(event, "The subject");
		return event;
	}
}
