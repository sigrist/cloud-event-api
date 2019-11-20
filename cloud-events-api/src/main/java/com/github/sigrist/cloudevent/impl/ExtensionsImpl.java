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

import java.util.ArrayList;

import com.github.sigrist.cloudevent.Entry;
import com.github.sigrist.cloudevent.Extension;
import com.github.sigrist.cloudevent.Extensions;

public class ExtensionsImpl extends ArrayList<Entry<?>> implements Extensions {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // TODO REMOVER
    public ExtensionsImpl() {
    }

    public ExtensionsImpl(final Extensions extensions, final Extension extension) {
        extensions.forEach(this::add);
        extension.forEach(this::add);
    }
}
