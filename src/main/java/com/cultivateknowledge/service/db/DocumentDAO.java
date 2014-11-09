package com.cultivateknowledge.service.db;

import com.cultivateknowledge.model.DocumentModel;
import com.cultivateknowledge.model.DocumentRequest;

import java.util.List;

/**
 * User: Ryan Chute
 * Date: 11/6/14
 */
public interface DocumentDAO {

    List<DocumentModel> find(DocumentRequest request);

    DocumentModel findOne(DocumentRequest request);

    void create(DocumentModel document);

    void update(DocumentModel document);

}
