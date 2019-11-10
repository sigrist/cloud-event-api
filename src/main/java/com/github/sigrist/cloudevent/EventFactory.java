package com.github.sigrist.cloudevent;

import java.net.URI;

public interface EventFactory {

	<T> Event<T> create(String type);
	<T> Event<T> create(String type, T data);
	
	
	URI source();
	
}
