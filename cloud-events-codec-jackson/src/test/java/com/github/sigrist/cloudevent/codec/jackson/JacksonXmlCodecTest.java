package com.github.sigrist.cloudevent.codec.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;

public class JacksonXmlCodecTest {
    private final Codec codec = new JacksonXmlCodec();
    private final MyPayload payload = new MyPayload(40, "Paulo");
    private final String rawXml = "<MyPayload><age>40</age><name>Paulo</name></MyPayload>";

    @Test
    public void testEncode() {
        final String data = codec.encode(payload);
        assertEquals(this.rawXml, data);
    }

    @Test
    public void testInvalidEncode() {
        assertThrows(CloudEventException.class, () -> codec.encode(new MyErrorOject()));
    }

    @Test
    public void testDecode() {
        final MyPayload actual = codec.decode(this.rawXml, MyPayload.class);

        assertEquals(payload, actual);

    }

    @Test
    public void testInvalidDecode() {
        assertThrows(CloudEventException.class, () -> codec.decode("<invalid>" + this.rawXml, MyPayload.class));

    }

}
