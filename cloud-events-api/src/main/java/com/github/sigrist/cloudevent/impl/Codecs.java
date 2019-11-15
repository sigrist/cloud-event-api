package com.github.sigrist.cloudevent.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.sigrist.cloudevent.Codec;

public class Codecs {

	private final Map<String, Codec> codecsMap;

	public Codecs(final Codec... codecs) {
		this.codecsMap = Arrays.stream(codecs).collect(Collectors.toMap(Codec::contentType, Function.identity()));
	}

	public Codec get(final String contentType) {
		return this.codecsMap.get(contentType);
	}
}
