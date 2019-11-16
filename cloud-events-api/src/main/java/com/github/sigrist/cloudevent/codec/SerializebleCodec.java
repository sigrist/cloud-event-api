/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.sigrist.cloudevent.codec;

import java.util.Base64;

import com.github.sigrist.cloudevent.CloudEventException;
import com.github.sigrist.cloudevent.Codec;

public class SerializebleCodec extends BaseSerializableCodec implements Codec {

    @Override
    public String encode(final Object object) {
        return Base64.getEncoder().encodeToString(this.toBytes(object));
    }

    @Override
    public <T> T decode(final String target, final Class<T> clazz) {
        final byte[] data = Base64.getDecoder().decode(target);
        final Object o = this.toObject(data);

        // TODO if
        if (clazz.isInstance(o)) {
            return (T) o;
        }
        throw new CloudEventException("Error decoding object. Invalid instance tyoe : " + o.getClass());

    }

    @Override
    public String contentType() {
        return "text/plain";
    }

}
