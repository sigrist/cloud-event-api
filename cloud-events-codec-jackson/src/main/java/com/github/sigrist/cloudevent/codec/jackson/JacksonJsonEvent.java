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
public final class JacksonJsonEvent<T> implements Event<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("source")
	private URI source;

	@JsonProperty("specversion")
	private String specVersion;

	@JsonProperty("type")
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
	public String id() {
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
