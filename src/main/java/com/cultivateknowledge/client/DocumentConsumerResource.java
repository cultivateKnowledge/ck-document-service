package com.cultivateknowledge.client;

import com.codahale.metrics.annotation.Timed;
import com.cultivateknowledge.model.DocumentModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/consumer")
@Produces(MediaType.TEXT_PLAIN)
public class DocumentConsumerResource {
    private final DocumentAPI documentAPI;

    public DocumentConsumerResource(DocumentAPI documentAPI) {
        this.documentAPI = documentAPI;
    }

    @Timed
    @GET
    public String consume(@QueryParam("collectionId") String collectionId, @QueryParam("recordId") String recordId) {
        DocumentModel document = documentAPI.get(collectionId, recordId);
        return String.format("The service is saying %s:%s",  document.getCollectionId(), document.getRecordId());
    }
}
