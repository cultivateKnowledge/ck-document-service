package com.cultivateknowledge;

import com.cultivateknowledge.service.db.mongo.MongoFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User: Ryan Chute
 * Date: 10/17/14
 */
public class DocumentServiceConfiguration extends Configuration  {

    public MongoFactory getMongoFactory() { return mongoFactory; }

    @Valid
    @NotNull
    @JsonProperty("mongoDB")
    private MongoFactory mongoFactory = new MongoFactory();

}
