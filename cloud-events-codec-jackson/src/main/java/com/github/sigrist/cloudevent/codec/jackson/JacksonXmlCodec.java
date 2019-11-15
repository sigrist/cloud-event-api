package com.github.sigrist.cloudevent.codec.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;

public class JacksonXmlCodec implements Codec {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final XmlMapper mapper = new XmlMapper();
	@Override
	public String encode(Object source) {
		
		try {
			return mapper.writeValueAsString(source);
		} catch (JsonProcessingException e) {
			throw new CloudEventException("Error encoding object to XML", e);
		}
	}

	@Override
	public <T> T decode(String object, Class<T> clazz) {
		try {
			return mapper.readValue(object, clazz);
		} catch (JsonProcessingException e) {
			throw new CloudEventException("Error decoding XML", e);
		}
	}

	@Override
	public String contentType() {
		return "text/xml";
	}

}
