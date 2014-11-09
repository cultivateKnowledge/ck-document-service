package com.cultivateknowledge.model;

import com.cultivateknowledge.schema.RecordType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentModel extends RecordType {

    //private String id;

    @JsonProperty("_id")
    public String getId() {
        return this.recordId;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.recordId = recordId;
    }
}
