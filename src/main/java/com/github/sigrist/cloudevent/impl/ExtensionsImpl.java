package com.github.sigrist.cloudevent.impl;

import java.util.ArrayList;

import com.github.sigrist.cloudevent.Extension;
import com.github.sigrist.cloudevent.Extensions;

public class ExtensionsImpl extends ArrayList<Extension> implements Extensions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2544416779523013927L;

	public ExtensionsImpl() {
	}

	public ExtensionsImpl(Extensions extensions, Extension extension) {
		extensions.forEach(this::add);
		this.add(extension);
	}

}
