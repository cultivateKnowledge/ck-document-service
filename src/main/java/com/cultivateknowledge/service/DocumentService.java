package com.cultivateknowledge.service;

import com.cultivateknowledge.model.DocumentModel;

import java.util.List;

/**
 * User: Ryan Chute
 * Date: 11/6/14
 */
public interface DocumentService {

    public List<DocumentModel> find(String collection,
                                    String recordId,
                                    String entityId,
                                    String fromDate,
                                    String untilDate,
                                    Integer startIndex,
                                    Integer pageSize,
                                    String orderBy);

    /**
     * Get a single document using collection and recordId
     * @param recordId
     * @return
     */
    public DocumentModel get(String collection, String recordId);

    /**
     * Adds document
     * @param document
     */
    public void add(DocumentModel document);

    /**
     * Update document
     * @param document
     */
    public void update(DocumentModel document);

}
