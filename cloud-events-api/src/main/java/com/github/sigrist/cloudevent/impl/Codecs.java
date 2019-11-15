package com.github.sigrist.cloudevent.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.sigrist.cloudevent.Codec;

public class Codecs {

	private final Map<String, Codec> codecsMap;

	public Codecs(final Codec... codecs) {
		this.codecsMap = new HashMap<>();
		// Add the codecs
		for (int i = 0; i < codecs.length; i++) {
			final Codec c = codecs[i];
			this.codecsMap.put(c.contentType(), c);
		}
	}

	public Codec get(final String contentType) {
		return this.codecsMap.get(contentType);
	}
}
