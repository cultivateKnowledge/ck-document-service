package com.cultivateknowledge.service.db.mongo;

import com.cultivateknowledge.model.DocumentModel;
import com.cultivateknowledge.model.DocumentRequest;
import com.cultivateknowledge.service.db.DocumentDAO;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: Ryan Chute
 * Date: 11/6/14
 */
public class MongoDocumentDAOImpl implements DocumentDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDocumentDAOImpl.class);
    private final DB mongoDb;
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public MongoDocumentDAOImpl(final DB mongoDb) {
        this.mongoDb = mongoDb;
    }

    @Override
    public List<DocumentModel> find(DocumentRequest request) {
        return null;
    }

    @Override
    public DocumentModel findOne(DocumentRequest request) {
        JacksonDBCollection<DocumentModel, String> wrappedCollection = getWrappedCollection(request.getCollection());
        DocumentModel documentModel = wrappedCollection.findOneById(request.getRecordId());
        LOGGER.debug(String.format("Found %s!", (documentModel != null && documentModel.getRecordId() != null) ? documentModel.getRecordId() : "nothing"));
        return documentModel;
    }

    @Override
    public void create(DocumentModel document) {
        document.setRecordCreated(dateFormatter.format(new Date()));
        JacksonDBCollection<DocumentModel, String> wrappedCollection = getWrappedCollection(document.getCollection());
        WriteResult<DocumentModel, String> result = wrappedCollection.insert(document);
        LOGGER.debug(String.format("Created %s", result.getSavedId()));
    }

    private JacksonDBCollection<DocumentModel, String> getWrappedCollection(String collection) {
        DBCollection mongoCollection = mongoDb.getCollection(collection);
        return JacksonDBCollection.wrap(mongoCollection, DocumentModel.class, String.class);
    }

    @Override
    public void update(DocumentModel document) {
        document.setRecordModified(dateFormatter.format(new Date()));
        JacksonDBCollection<DocumentModel, String> wrappedCollection = getWrappedCollection(document.getCollection());
        wrappedCollection.updateById(document.getRecordId(), document);
    }
}
