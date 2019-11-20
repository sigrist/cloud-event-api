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
package com.github.sigrist.cloudevent;

/**
 * Translate the event payload based on the {@link #contentType()}.
 * 
 * @author sigrist
 */
public interface Codec {

    /**
     * Encode the payload into the content type.
     * 
     * @param payload The payload to convert
     * @return The payload converted as String
     */
    String encode(Object payload);

    /**
     * Decode the object to the payload.
     * 
     * @param object The string to convert
     * @param clazz  The payload class
     * @return an instance of the payload
     */
    <T> T decode(String object, Class<T> clazz);

    /**
     * @return The codec content type.
     */
    String contentType();
}
