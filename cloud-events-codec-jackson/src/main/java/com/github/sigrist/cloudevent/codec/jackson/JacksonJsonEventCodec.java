package com.github.sigrist.cloudevent.codec.jackson;

import java.io.IOException;
import java.io.InputStream;

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

	private final ObjectMapper mapper = new ObjectMapper();
	
	private final Codecs codecs;
	
	public JacksonJsonEventCodec(final Codecs codecs) {
		this.mapper.registerModule(new Jdk8Module());
		this.mapper.registerModule(new JavaTimeModule());
		this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        
		
        this.codecs = codecs;
	}
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
			JsonNode node = this.mapper.readTree(stream);
			
			Event<?> event = this.mapper.treeToValue(node, JacksonJsonEvent.class);
			
			if (node.has("data")) {
				Codec codec = codecs.get("application/json");
				JsonNode jsonData = node.get("data");
				
				String json = mapper.writeValueAsString(jsonData);
				T data = codec.decode(json, clazz);
				event = new DataEventImpl<>(event, codec, data);
			}
			
			return (Event<T>) event;
		} catch (IOException e) {
			throw new CloudEventException("Error decoding event from JSON", e);
		}
	}

}
