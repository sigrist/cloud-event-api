package com.github.sigrist.cloudevent;

import java.io.Serializable;

public interface Codec extends Serializable {

	String encode(Object source);
	
	<T> T decode(String object, Class<T> clazz);
	
	String contentType();
}
