package com.github.sigrist.cloudevent.codec.jackson;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.Extensions;
import com.github.sigrist.cloudevent.impl.ExtensionsImpl;

@JsonIgnoreProperties(ignoreUnknown = true)
final class JacksonJsonEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id", required = true)
	private String id;

	@JsonProperty(value = "source", required = true)
	private URI source;

	@JsonProperty(value = "specversion", required = true)
	private String specVersion;

	@JsonProperty(value = "type", required = true)
	private String type;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("dataschema")
	private URI dataSchema;

	@JsonProperty("datacontenttype")
	private String dataContentType;

	@JsonProperty("time")
	private LocalDateTime time;

	@Override
	public String eventId() {
		return this.id;
	}

	@Override
	public URI source() {
		return this.source;
	}

	@Override
	public String specVersion() {
		return this.specVersion;
	}

	@Override
	public String type() {
		return this.type;
	}

	@Override
	public Optional<String> subject() {
		return Optional.of(this.subject);
	}

	@Override
	public Optional<LocalDateTime> time() {
		return Optional.of(this.time);
	}

	@Override
	public Optional<String> dataContentType() {
		return Optional.of(this.dataContentType);
	}

	@Override
	public Optional<URI> dataSchema() {
		return Optional.of(this.dataSchema);
	}

	@Override
	public Optional<T> data() {
		return Optional.empty();
	}

	@Override
	public Extensions extensions() {
		// TODO
		return new ExtensionsImpl();
	}

}
