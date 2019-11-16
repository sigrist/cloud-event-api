package com.github.sigrist.cloudevent.impl;

import java.util.ArrayList;

import com.github.sigrist.cloudevent.Extension;
import com.github.sigrist.cloudevent.Extensions;

public class ExtensionsImpl extends ArrayList<Extension> implements Extensions {

    public ExtensionsImpl() {
    }

    public ExtensionsImpl(final Extensions extensions, final Extension extension) {
        extensions.forEach(this::add);
        this.add(extension);
    }

}
