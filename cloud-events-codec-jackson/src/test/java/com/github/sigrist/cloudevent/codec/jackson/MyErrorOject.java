package com.github.sigrist.cloudevent.codec.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

class MyErrorOject {
    @JsonProperty("name")
    public String name() {
        throw new UnsupportedOperationException("Forcing exception");
    }
}