package com.cultivateknowledge.client;

import com.cultivateknowledge.model.DocumentModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public interface DocumentAPI {
    @GET
    @Path("/document/{collectionId}/item/{recordId}")
    DocumentModel get(@PathParam("collectionId") String collectionId, @PathParam("recordId") String recordId);

    @POST
    @Path("/document/item")
    @Consumes(MediaType.APPLICATION_JSON)
    void add(DocumentModel document);

}