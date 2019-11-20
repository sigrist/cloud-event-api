package com.github.sigrist.cloudevent.extensions;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.sigrist.cloudevent.Entry;
import com.github.sigrist.cloudevent.Extension;

public abstract class AbstractExtension extends ArrayList<Entry<?>> implements Extension {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AbstractExtension(final Entry<?>... entries) {
        Arrays.stream(entries).filter(entry -> entry.value().isPresent()).forEach(this::add);
    }

}
