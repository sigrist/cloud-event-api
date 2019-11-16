package com.github.sigrist.cloudevent;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A {@link Codec} list.
 * 
 * The {@link Codecs} cannot have two {@link Codec} with the same Content Type.
 * The latest one added will be keep it.
 * 
 * @author sigrist
 *
 */
public class Codecs {

    /** A map of codecs. The {@link Codec#contentType()} is the key. */
    private final Map<String, Codec> codecsMap;

    /**
     * Constructor.
     * 
     * @param codecs The codecs available in this list.
     */
    public Codecs(final Codec... codecs) {
        this.codecsMap = Arrays.stream(codecs).collect(Collectors.toMap(Codec::contentType, Function.identity()));
    }

    /**
     * Return a {@link Codec} based on the content type. If no codec is available, a
     * {@link NopCodec} will be returned.
     * 
     * @param contentType The content type to find the codec.
     * @return An instance of {@link Codec}.
     */
    public Codec get(final String contentType) {
        return this.codecsMap.getOrDefault(contentType, new NopCodec(contentType));
    }

    /**
     * A non-operational implementation of {@link Codec}.
     * 
     * Returned when the content type is not available.
     * 
     * @author sigrist
     *
     */
    private class NopCodec implements Codec {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        /** The expected content type. */
        private final String theContentType;

        /**
         * Constructor.
         * 
         * @param contentType The expected content type.
         */
        public NopCodec(final String contentType) {
            this.theContentType = contentType;
        }

        @Override
        public String encode(final Object source) {
            throw new UnsupportedOperationException("Encode: invalid codec '" + this.theContentType + "'");
        }

        @Override
        public <T> T decode(final String object, final Class<T> clazz) {
            throw new UnsupportedOperationException("Decode: invalid codec '" + this.theContentType + "'");
        }

        @Override
        public String contentType() {
            throw new UnsupportedOperationException("ContentType: invalid codec '" + this.theContentType + "'");
        }

    }
}
