package com.cultivateknowledge.client;

import com.cultivateknowledge.model.DocumentModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface DocumentAPI {
    @GET
    @Path("/document/{collection}/item/{recordId}")
    DocumentModel get(@PathParam("collection") String collection, @PathParam("recordId") String recordId);
}