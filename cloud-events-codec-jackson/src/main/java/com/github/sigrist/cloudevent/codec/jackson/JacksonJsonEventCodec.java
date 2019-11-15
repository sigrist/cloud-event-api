package com.github.sigrist.cloudevent.codec.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.impl.Codecs;
import com.github.sigrist.cloudevent.impl.DataEventImpl;

public class JacksonJsonEventCodec implements EventCodec {

	private final ObjectMapper mapper;

	private final Codecs codecs;

	public JacksonJsonEventCodec(final Codecs codecs) {
		this.mapper = new ObjectMapper();
		this.mapper.registerModule(new Jdk8Module());
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		this.codecs = codecs;
	}

	/**
	 * Converts the `event` into JSON.
	 * 
	 * This is done adding on top of it an instance of
	 * {@link JacksonEventDecorator}, where contains the {@link JsonProperty}
	 * annotations.
	 * 
	 * @param event The event to encode.
	 * 
	 * @return the event encoded as JSON.
	 * 
	 * @throws CloudEventException when there is a JSON processing error.
	 */
	@Override
	public byte[] encode(final Event<?> event) {
		try {
			return mapper.writeValueAsBytes(new JacksonEventDecorator<>(event));
		} catch (JsonProcessingException e) {
			throw new CloudEventException("Error encoding event to JSON", e);
		}
	}

	@Override
	public <T> Event<T> decode(final InputStream stream, final Class<T> clazz) {
		try {
			JsonNode jsonNode = this.mapper.readTree(stream);
			
			JacksonJsonEvent<T> event = this.mapper.convertValue(jsonNode, JacksonJsonEvent.class);

			
			/*
			 * Optional<T> data = decodeData(jsonNode, clazz);
			r = event;
			
			if (jsonNode.has("data") && jsonNode.has("datacontenttype")) {
				JsonNode node = jsonNode.get("data");
				String contentType = jsonNode.get("datacontenttype").asText();
				Codec codec = codecs.get(contentType);

				String json = mapper.writeValueAsString(node);
				T data = codec.decode(json, clazz);
				r = new DataEventImpl<>(event, codec, data);
			}

			return (Event<T>) r;
			*/
			Event<T> dataEvent = new JacksonJsonDataEvent<>(event, jsonNode, codecs, clazz);
			
			return dataEvent;
			
		} catch (IOException e) {
			throw new CloudEventException("Error decoding event from JSON", e);
		}
	}
	
	private <T> Optional<T> decodeData(final JsonNode jsonNode, Class<T> clazz) throws JsonProcessingException {
		final Optional<T> optional;
		if (jsonNode.has("data") && jsonNode.has("datacontenttype")) {
			JsonNode node = jsonNode.get("data");
			String contentType = jsonNode.get("datacontenttype").asText();
			Codec codec = codecs.get(contentType);

			String json = mapper.writeValueAsString(node);
			T data = codec.decode(json, clazz);
			optional = Optional.of(data);
		} else {
			optional = Optional.empty();
		}
		
		return optional;
	}

}
