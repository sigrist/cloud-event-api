package com.github.sigrist.cloudevent;

public interface Codec {

	String encode(Object source);
	
	<T> T decode(String object, Class<T> clazz);
	
	String contentType();
}
