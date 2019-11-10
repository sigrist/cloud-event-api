package com.github.sigrist.cloudevent;

import java.net.URI;

import com.github.sigrist.cloudevent.impl.AbstractEventFactory;

public class TestEventFactory extends AbstractEventFactory implements EventFactory {

	public TestEventFactory() {
		super(URI.create("/TestEventFactory"));
	}

	@Override
	public <T> Event create(final String type, T data) {
		// TODO Auto-generated method stub
		return null;
	}

}
