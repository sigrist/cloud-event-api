package com.github.sigrist.cloudevent.codec.jackson;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.sigrist.cloudevent.Entry;
import com.github.sigrist.cloudevent.Extensions;
import com.github.sigrist.cloudevent.extensions.DefaultEntry;

public class JacksonJsonExtension implements Extensions {

    private final List<Entry<?>> entries;

    public JacksonJsonExtension(final Map<String, Object> extensions) {
        this.entries = extensions.entrySet().stream()
                .map(mapEntry -> new DefaultEntry<>(mapEntry.getKey(), mapEntry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Entry<?>> iterator() {
        return this.entries.iterator();
    }

}
