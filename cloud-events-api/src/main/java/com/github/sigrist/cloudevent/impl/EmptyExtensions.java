package com.github.sigrist.cloudevent.impl;

import java.util.Collections;
import java.util.Iterator;

import com.github.sigrist.cloudevent.Entry;
import com.github.sigrist.cloudevent.Extensions;

public class EmptyExtensions implements Extensions {

    @Override
    public Iterator<Entry<?>> iterator() {
        return Collections.emptyIterator();
    }

}
