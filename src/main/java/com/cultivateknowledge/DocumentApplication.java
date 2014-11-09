package com.cultivateknowledge;

import com.codahale.metrics.JmxReporter;
import com.cultivateknowledge.client.DocumentAPI;
import com.cultivateknowledge.client.DocumentConsumerResource;
import com.cultivateknowledge.service.DocumentResource;
import com.cultivateknowledge.service.IndexResource;
import com.cultivateknowledge.service.db.mongo.MongoDocumentDAOImpl;
import com.cultivateknowledge.service.healthcheck.MongoDBHealthCheck;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;

import java.text.SimpleDateFormat;

public class DocumentApplication extends Application<DocumentServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new DocumentApplication().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public void initialize(Bootstrap<DocumentServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(DocumentServiceConfiguration cfg, Environment env) throws Exception {
        JmxReporter.forRegistry(env.metrics()).build().start(); // Manually add JMX reporting (Dropwizard regression)

        env.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

        // Consumer Resource - Calls Document API as client
        Feign.Builder feignBuilder = Feign.builder()
                .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
                .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
                .decoder(new JacksonDecoder());
        DocumentAPI documentAPI = feignBuilder.target(DocumentAPI.class, "http://localhost:8080");
        env.jersey().register(new DocumentConsumerResource(documentAPI));

        // MongoDB Resource Setup
        final MongoClient mongoClient = cfg.getMongoFactory().buildClient(env);
        final DB db = cfg.getMongoFactory().buildDB(env);

        // Register mongodb health checks
        env.healthChecks().register("mongo",new MongoDBHealthCheck(mongoClient));

        // Register Document Resources
        env.jersey().register(new DocumentResource(new MongoDocumentDAOImpl(db)));

        // Add index page
        env.jersey().register(new IndexResource());

        // Delegate swagger api documentation set-up
        SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
        swaggerDropwizard.onRun(cfg, env, "localhost", 8080);
    }

}