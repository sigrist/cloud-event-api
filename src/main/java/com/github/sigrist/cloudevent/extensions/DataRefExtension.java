package com.github.sigrist.cloudevent.extensions;

import java.net.URI;

import com.github.sigrist.cloudevent.Extension;

public class DataRefExtension implements Extension {

	private final URI dataRef;

	public DataRefExtension(final URI dataRef) {
		this.dataRef = dataRef;
	}

	URI dataRef() {
		return this.dataRef;
	}
}
