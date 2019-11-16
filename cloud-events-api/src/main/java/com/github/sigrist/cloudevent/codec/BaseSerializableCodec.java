package com.github.sigrist.cloudevent.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.github.sigrist.cloudevent.CloudEventException;

public abstract class BaseSerializableCodec {
    protected final Object toObject(final byte[] data) {

        try (final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CloudEventException("Error decoding object", e);
        }

    }

    protected final byte[] toBytes(final Object source) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(source);
            oos.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new CloudEventException("Error encoding object", e);
        }

    }

}
