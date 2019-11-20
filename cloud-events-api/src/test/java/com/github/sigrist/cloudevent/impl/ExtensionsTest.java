package com.github.sigrist.cloudevent.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.sigrist.cloudevent.Entry;
import com.github.sigrist.cloudevent.Event;
import com.github.sigrist.cloudevent.extensions.DataRefExtension;
import com.github.sigrist.cloudevent.extensions.DistributedTracingExtension;

public class ExtensionsTest {

    private Event<Void> origin;
    private final URI dataRef = URI.create("/dataRef");

    @BeforeEach
    public void beforeTest() {
        this.origin = new DefaultEventImpl<>(URI.create("/default"), "Default event");
    }

    @Test
    public void testDataRefExtension() {

        Event<Void> event = new ExtensionsEventImpl<>(this.origin, new DataRefExtension(this.dataRef));
                
        assertTrue(event.extensions().iterator().hasNext());
        Entry<?> e = event.extensions().iterator().next();
        assertNotNull(e);
        assertEquals("dataref", e.name());
        assertEquals(dataRef, e.value().get());
    }
    
    @Test
    public void testDistributedTracingExtension() {

        Event<Void> event = new ExtensionsEventImpl<>(this.origin, new DistributedTracingExtension("parent", "state"));
        
        Iterator<Entry<?>> iterator = event.extensions().iterator();
        assertTrue(iterator.hasNext());
        
        
        Entry<?> e = iterator.next();
        assertEquals("traceparent", e.name());
        assertEquals("parent", e.value().get());
        
        e = iterator.next();
        assertEquals("tracestate", e.name());
        assertEquals("state", e.value().get());

    }

}
