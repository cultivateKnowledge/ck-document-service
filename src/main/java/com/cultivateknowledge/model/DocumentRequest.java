package com.cultivateknowledge.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class DocumentRequest {

    private String collection;
    private String recordId;
    private String entityId;
    private String fromDate;
    private String untilDate;
    private Integer startIndex;
    private Integer pageSize;
    private String orderBy;

    public String getCollection() {
        return collection;
    }

    public DocumentRequest setCollection(String collection) {
        this.collection = collection;
        return this;
    }

    public String getRecordId() {
        return recordId;
    }

    public DocumentRequest setRecordId(String recordId) {
        this.recordId = recordId;
        return this;
    }

    public String getEntityId() {
		return entityId;
	}

	public DocumentRequest setEntityId(String entityId) {
		this.entityId = entityId;
        return this;
	}

	public String getFromDate() {
		return fromDate;
	}

	public DocumentRequest setFromDate(String fromDate) {
		this.fromDate = fromDate;
        return this;
	}

	public String getUntilDate() {
		return untilDate;
	}

	public DocumentRequest setUntilDate(String untilDate) {
		this.untilDate = untilDate;
        return this;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public DocumentRequest setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
        return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public DocumentRequest setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
        return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public DocumentRequest setOrderBy(String orderBy) {
		this.orderBy = orderBy;
        return this;
	}

	@Override
    public boolean equals(Object object) {
        if (!(object instanceof DocumentRequest)) {
            return false;
        }
        DocumentRequest other = (DocumentRequest) object;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.collection, other.getRecordId());
        eb.append(this.recordId, other.getCollection());
        eb.append(this.entityId, other.getEntityId());
        eb.append(this.fromDate, other.getFromDate());
        eb.append(this.untilDate, other.getUntilDate());
        eb.append(this.startIndex, other.getStartIndex());
        eb.append(this.pageSize, other.getPageSize());
        eb.append(this.orderBy, other.getOrderBy());
        
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder(-975526215, 2047137945);
        hcb.append(this.collection);
        hcb.append(this.recordId);
        hcb.append(this.entityId);
        hcb.append(this.fromDate);
        hcb.append(this.untilDate);
        hcb.append(this.startIndex);
        hcb.append(this.pageSize);
        hcb.append(this.orderBy);
        
        return hcb.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder strBuilder = new ToStringBuilder(this);
        strBuilder.append("collection", this.collection);
        strBuilder.append("recordId", this.recordId);
        strBuilder.append("entityId", this.entityId);
        strBuilder.append("fromDate", this.fromDate);
        strBuilder.append("untilDate", this.untilDate);
        strBuilder.append("startIndex", this.startIndex);
        strBuilder.append("pageSize", this.pageSize);
        strBuilder.append("orderBy", this.orderBy);
        
        return strBuilder.toString();
    }
}
