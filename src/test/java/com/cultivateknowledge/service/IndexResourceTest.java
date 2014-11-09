package com.cultivateknowledge.service;

import org.junit.Test;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexResourceTest {

    @Test
    public void testHandle() throws Exception {
        IndexResource resource = new IndexResource();
        UriInfo uriInfo = mock(UriInfo.class);
        URI uri = new URI("http://test.bob.com:8081");
        when(uriInfo.getRequestUri()).thenReturn(uri);
        IndexResource.IndexView view = resource.handle(uriInfo);
        assertEquals(view.getHelloWorldMsg(), "Hello World!");
        assertTrue(view.getWorlds().contains("Earth"));
        assertTrue(view.getHostname().equals("test.bob.com"));
    }
}