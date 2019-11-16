package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;
import com.github.sigrist.cloudevent.impl.AbstractDecoratorEvent;

@JsonPropertyOrder({ "specversion", "type", "source", "subject", "id", "time", "datacontenttype", "dataschema",
		"data" })
public class JacksonEventDecorator<T> extends AbstractDecoratorEvent<T> {

	public JacksonEventDecorator(Event<T> origin) {
		super(origin);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("specversion")
	public String jacksonSpecVersion() {
		return super.specVersion();
	}

	@JsonProperty("type")
	public String jacksonType() {
		return super.type();
	}

	@JsonProperty("source")
	public URI jacksonSource() {
		return super.source();
	}

	@JsonProperty("subject")
	@Override
	public Optional<String> subject() {
		return super.subject();
	}

	@JsonProperty("id")
	public String jacksonId() {
		return super.eventId();
	}

	@JsonProperty("time")
	@Override
	public Optional<LocalDateTime> time() {
		return super.time();
	}

	@JsonProperty("datacontenttype")
	@Override
	public Optional<String> dataContentType() {
		return super.dataContentType();
	}

	@JsonProperty("dataschema")
	@Override
	public Optional<URI> dataSchema() {
		return super.dataSchema();
	}

	@JsonProperty("data")
	@Override
	public Optional<T> data() {
		return super.data();
	}

	@Override
	public Extensions extensions() {
		return super.extensions();
	}

}
