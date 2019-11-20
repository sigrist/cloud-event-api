package com.github.sigrist.cloudevent;

import java.util.Optional;

public interface Entry<T> {

    String name();
    
    Optional<T> value();
}
