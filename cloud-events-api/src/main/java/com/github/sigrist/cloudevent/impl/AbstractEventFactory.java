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
package com.github.sigrist.cloudevent.impl;

import java.io.InputStream;
import java.net.URI;

import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.EventCodec;
import com.github.sigrist.cloudevent.EventFactory;

public abstract class AbstractEventFactory implements EventFactory {

    private final URI theSource;
    private final EventCodec theEventCodec;

    public AbstractEventFactory(final URI source, final EventCodec eventCodec) {
        this.theSource = source;
        this.theEventCodec = eventCodec;
    }

    @Override
    public URI source() {
        return this.theSource;
    }

    protected final <T> Event<T> create(final String type) {
        return new DefaultEventImpl<>(this.source(), type);
    }

    protected final <T> Event<T> create(final String type, final String contentType, final T data) {
        return new DataEventImpl<>(new DefaultEventImpl<>(this.source(), type), contentType, data);
    }

    public final <T> Event<T> fromStream(final InputStream inputStream, final Class<T> payloadClazz) {
        return this.theEventCodec.decode(inputStream, payloadClazz);
    }

}
