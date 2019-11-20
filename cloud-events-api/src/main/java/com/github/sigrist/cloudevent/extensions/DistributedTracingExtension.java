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
package com.github.sigrist.cloudevent.extensions;

import com.github.sigrist.cloudevent.Extension;

public class DistributedTracingExtension extends AbstractExtension implements Extension {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DistributedTracingExtension(final String traceParent, final String traceState) {
        super(new DefaultEntry<>("traceparent", traceParent), new DefaultEntry<>("tracestate", traceState));
    }

    public DistributedTracingExtension(final String traceParent) {
        super(new DefaultEntry<>("traceparent", traceParent));
    }
}
