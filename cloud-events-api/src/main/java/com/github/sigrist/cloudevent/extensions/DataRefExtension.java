package com.github.sigrist.cloudevent.extensions;

import java.net.URI;

import com.github.sigrist.cloudevent.Extension;

public class DataRefExtension implements Extension {

    private final URI dataRefURI;

    public DataRefExtension(final URI dataRef) {
        this.dataRefURI = dataRef;
    }

    URI dataRef() {
        return this.dataRefURI;
    }
}
