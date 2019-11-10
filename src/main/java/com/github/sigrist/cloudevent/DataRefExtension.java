package com.github.sigrist.cloudevent;

import java.net.URI;

public class DataRefExtension implements Extension {

	private final URI dataRef;

	public DataRefExtension(final URI dataRef) {
		this.dataRef = dataRef;
	}
	
	URI dataRef() {
		return this.dataRef;
	}
}
