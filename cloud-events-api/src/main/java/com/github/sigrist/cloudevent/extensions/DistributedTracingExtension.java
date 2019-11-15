package com.github.sigrist.cloudevent.extensions;

import java.util.Optional;

import com.github.sigrist.cloudevent.Extension;

public class DistributedTracingExtension implements Extension {

	private final String traceParent;
	private final Optional<String> traceState;
	
	private DistributedTracingExtension(final String traceParent, final Optional<String> traceState) {
		this.traceParent = traceParent;
		this.traceState = traceState;
	}

	public DistributedTracingExtension(final String traceParent, final String traceState) {
		this(traceParent, Optional.of(traceState));
	}

	public DistributedTracingExtension(final String traceParent) {
		this(traceParent, Optional.empty());
	}
	

	public String traceParent() {
		return traceParent;
	}
	
	public Optional<String> traceState() {
		return traceState;
	}
}
