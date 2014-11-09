package com.cultivateknowledge.service;

import com.codahale.metrics.annotation.Timed;
import com.cultivateknowledge.model.DocumentModel;
import com.cultivateknowledge.model.DocumentRequest;
import com.cultivateknowledge.service.db.DocumentDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/document")
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource implements DocumentService {
    private final DocumentDAO dao;

    public DocumentResource(DocumentDAO dao) {
        this.dao = dao;
    }

    @Override
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{collection}/items")
    public List<DocumentModel> find(@PathParam("collection") String collection,
                                    @QueryParam("recordId") String recordId,
                                    @QueryParam("entityId") String entityId,
                                    @QueryParam("from") String fromDate,
                                    @QueryParam("until") String untilDate,
                                    @QueryParam("startIndex") Integer startIndex,
                                    @QueryParam("pageSize") Integer pageSize,
                                    @QueryParam("orderBy") String orderBy) {
        return dao.find(new DocumentRequest()
                .setCollection(collection)
                .setRecordId(recordId)
                .setEntityId(entityId)
                .setFromDate(fromDate)
                .setUntilDate(untilDate)
                .setStartIndex(startIndex)
                .setPageSize(pageSize)
                .setOrderBy(orderBy));
    }

    @Override
    @Timed
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/item")
    public void add(DocumentModel document) {
        dao.create(document);
    }

    @Override
    @Timed
    @GET
    @Path("/{collection}/item/{recordId}")
    public DocumentModel get(@PathParam("collection") String collection, @PathParam("recordId") String recordId) {
        return dao.findOne(new DocumentRequest().setCollection(collection).setRecordId(recordId));
    }

    @Override
    @Timed
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{collection}/item/{recordId}")
    public void update(DocumentModel document) {
        dao.update(document);
    }
}