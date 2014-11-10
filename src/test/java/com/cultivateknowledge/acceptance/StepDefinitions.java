package com.cultivateknowledge.acceptance;

import com.cultivateknowledge.DocumentApplication;
import com.cultivateknowledge.DocumentServiceConfiguration;
import com.cultivateknowledge.client.DocumentAPI;
import com.cultivateknowledge.model.DocumentModel;
import com.google.common.io.Resources;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Global step definitions for conditions laid out in feature files
 */
public class StepDefinitions {
    private static DropwizardTestSupport<DocumentServiceConfiguration> service;
    private static DocumentAPI documentAPI;

    @Before
    public void setUp() throws Exception {
        if(service==null){
            service = new DropwizardTestSupport(DocumentApplication.class, Resources.getResource("appconfig.yml").getPath());
            service.startIfRequired();
            //Hack until @BeforeAll is properly supported by Cucumber-JVM
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    service.stop();
                }
            });
            Feign.Builder feignBuilder = Feign.builder()
                    .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
                    .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
                    .decoder(new JacksonDecoder());
            documentAPI = feignBuilder.target(DocumentAPI.class, "http://localhost:8080");
        }
    }

    DocumentModel documentModel;
    @Given("^new document with collectionId = '(.*)'$")
    public void new_document_with_json_x(String collectionId) throws Throwable {
        DocumentModel documentModel = new DocumentModel();
        documentModel.setCollectionId(collectionId);
        documentModel.setRecordId(UUID.randomUUID().toString());
        this.documentModel = documentModel;
    }

    @When("^the create endpoint is called$")
    public void the_create_endpoint() throws Throwable {
        documentAPI.add(documentModel);
    }

    @Then("^a new record containing recordId is created$")
    public void a_new_record_containing_recordId_is_created() throws Throwable {
        DocumentModel createdDoc = documentAPI.get(documentModel.getCollectionId(), documentModel.getRecordId());
        assertThat(createdDoc.getRecordId(), is(documentModel.getRecordId()));
    }
}