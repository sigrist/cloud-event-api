package com.github.sigrist.cloudevent;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Codecs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Map<String, Codec> codecsMap;

	public Codecs(final Codec... codecs) {
		this.codecsMap = Arrays.stream(codecs).collect(Collectors.toMap(Codec::contentType, Function.identity()));
	}

	public Codec get(final String contentType) {
		return this.codecsMap.getOrDefault(contentType, new NopCodec());
	}

	private class NopCodec implements Codec {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String encode(Object source) {
			throw new UnsupportedOperationException("Encode: invalid codec");
		}

		@Override
		public <T> T decode(String object, Class<T> clazz) {
			throw new UnsupportedOperationException("Decode: invalid codec");
		}

		@Override
		public String contentType() {
			throw new UnsupportedOperationException("ContentType: invalid codec");
		}

	}
}
