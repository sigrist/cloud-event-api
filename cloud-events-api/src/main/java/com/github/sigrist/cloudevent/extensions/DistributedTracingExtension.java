package com.github.sigrist.cloudevent.extensions;

import java.util.Optional;

import com.github.sigrist.cloudevent.Extension;

public class DistributedTracingExtension implements Extension {

	private final String parent;
	private final Optional<String> state;

	private DistributedTracingExtension(final String traceParent, final Optional<String> traceState) {
		this.parent = traceParent;
		this.state = traceState;
	}

	public DistributedTracingExtension(final String traceParent, final String traceState) {
		this(traceParent, Optional.of(traceState));
	}

	public DistributedTracingExtension(final String traceParent) {
		this(traceParent, Optional.empty());
	}

	public String traceParent() {
		return this.parent;
	}

	public Optional<String> traceState() {
		return this.state;
	}
}
