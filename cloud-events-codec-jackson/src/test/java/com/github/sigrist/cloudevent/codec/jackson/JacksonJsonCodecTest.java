package com.github.sigrist.cloudevent.codec.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Codec;

public class JacksonJsonCodecTest {

	@Test
	public void testEncode() {
		final MyPayload payload = new MyPayload(40, "Paulo");
		final Codec codec = new JacksonJsonCodec();
		
		String data = codec.encode(payload);
		String expected = "{\"age\":40,\"name\":\"Paulo\"}";
		
		assertEquals(expected, data);
	}
	
	@Test
	public void testDecode() {
		final MyPayload payload = new MyPayload(40, "Paulo");
		final Codec codec = new JacksonJsonCodec();
		final String json = "{\"age\":40,\"name\":\"Paulo\"}";
		
		
		MyPayload actual = codec.decode(json, MyPayload.class);
		
		
		assertEquals(payload, actual);
		
		
	}

}
