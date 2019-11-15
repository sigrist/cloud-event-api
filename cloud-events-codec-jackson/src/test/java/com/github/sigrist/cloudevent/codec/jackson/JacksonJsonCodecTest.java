package com.github.sigrist.cloudevent.codec.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Codec;

public class JacksonJsonCodecTest {

	private final Codec codec = new JacksonJsonCodec();
	private final MyPayload payload = new MyPayload(40, "Paulo");
	private final String rawJson = "{\"age\":40,\"name\":\"Paulo\"}";

	@Test
	public void testEncode() {

		final String data = codec.encode(payload);

		assertEquals(rawJson, data);
	}

	@Test
	public void testDecode() {

		final MyPayload actual = codec.decode(rawJson, MyPayload.class);

		assertEquals(payload, actual);

	}

}
