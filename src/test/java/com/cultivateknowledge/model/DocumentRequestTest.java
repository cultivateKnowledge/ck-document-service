package com.cultivateknowledge.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DocumentRequestTest {
    private DocumentRequest buildModel(Date date) {
        DocumentRequest ar = new DocumentRequest();
        ar.setRecordId("2345");
        return ar;
    }

    @Test
    public void testDocumentRequest() {
        Date date = new Date();
        DocumentRequest ar = buildModel(date);
        assertEquals("2345", ar.getRecordId());
    }

    @Test
    public void testDocumentRequestEquals() {
        Date date = new Date();
        DocumentRequest ar = buildModel(date);
        DocumentRequest ar2 = buildModel(date);
        assertEquals(ar, ar2);
    }

    @Test
    public void testDocumentRequestHashCode() {
        Date date = new Date();
        DocumentRequest ar = buildModel(date);
        DocumentRequest ar2 = buildModel(date);
        assertEquals(ar.hashCode(), ar2.hashCode());
        assertTrue(ar.toString().contains("recordId"));
    }

    @Test
    public void testNotEquals() {
        DocumentRequest ar = new DocumentRequest();
        assertFalse(ar.equals(new String()));
    }
}