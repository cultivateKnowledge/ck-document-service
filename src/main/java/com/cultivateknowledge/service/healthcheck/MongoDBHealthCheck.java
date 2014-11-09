package com.cultivateknowledge.service.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;

/**
 * This HealthCheck checks if the MongoDB specified in the configuration file
 * is reachable.
 */
public class MongoDBHealthCheck extends HealthCheck {

    private final MongoClient mongoClient;

    public MongoDBHealthCheck(MongoClient mongoClient) {
        super();
        this.mongoClient = mongoClient;
    }

    /**
     * Checks if the system database, which exists in all MongoDB instances can be reached.
     * @return A Result object
     * @throws Exception
     */
    @Override
    protected Result check() throws Exception {

        try {
            mongoClient.getDB("system").getStats();
        }catch(MongoClientException ex) {
            return Result.unhealthy(ex.getMessage());
        }


        return Result.healthy();
    }

}