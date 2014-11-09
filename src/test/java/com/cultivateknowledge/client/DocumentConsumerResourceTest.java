package com.cultivateknowledge.client;

import com.cultivateknowledge.model.DocumentModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentConsumerResourceTest {

    private DocumentConsumerResource resource;
    private DocumentAPI apiClient;

    @Before
    public void setUp() throws Exception {
        apiClient = mock(DocumentAPI.class);
        resource = new DocumentConsumerResource(apiClient);
    }

    @Test
    public void testConsume() throws Exception {
        DocumentModel doc = new DocumentModel();
        doc.setCollection("test");
        doc.setRecordId("1234");
        when(apiClient.get("test", "1234")).thenReturn(doc);
        assertEquals("The service is saying: ?", resource.consume("test", "1234"));
    }
}