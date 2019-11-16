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

import java.util.Optional;

import com.github.sigrist.cloudevent.Codec;
import com.github.sigrist.cloudevent.Event;

public class DataEventImpl<T> extends DataContentTypeEventImpl<T> implements Event<T> {
    private final String theData;
    private final Codec theCodec;
    private final Class<T> theClazz;

    @SuppressWarnings("unchecked")
    public DataEventImpl(final Event<T> origin, final Codec codec, final Object data) {
        super(origin, codec.contentType());
        this.theCodec = codec;
        this.theClazz = (Class<T>) data.getClass();

        this.theData = this.theCodec.encode(data);
    }

    @Override
    public Optional<T> data() {
        return Optional.ofNullable(this.theCodec.decode(this.theData, this.theClazz));
    }

}
