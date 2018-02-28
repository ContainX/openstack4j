package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.Summary;
import org.openstack4j.model.cloudkitty.builder.SummaryBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

public class CloudkittySummary implements Summary {

    private Date begin;
    private Date end;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("res_type")
    private String resourceType;
    private float rate;

    @Override
    public Date getBegin() {
        return begin;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getResourceType() {
        return resourceType;
    }

    @Override
    public float getRate() {
        return rate;
    }

    @Override
    public SummaryBuilder toBuilder() {
        return new CloudkittySummaryConcreteBuilder(this);
    }

    public static SummaryBuilder builder() {
        return new CloudkittySummaryConcreteBuilder();
    }

    public static class CloudkittySummaryConcreteBuilder implements SummaryBuilder {

        private CloudkittySummary model;

        public CloudkittySummaryConcreteBuilder(CloudkittySummary model) {
            this.model = model;
        }

        public CloudkittySummaryConcreteBuilder() {
            this(new CloudkittySummary());
        }

        @Override
        public SummaryBuilder begin(Date begin) {
            model.begin = begin;
            return this;
        }

        @Override
        public SummaryBuilder end(Date end) {
            model.end = end;
            return this;
        }

        @Override
        public SummaryBuilder tenantId(String tenantId) {
            model.tenantId = tenantId;
            return this;
        }

        @Override
        public SummaryBuilder resourceType(String resourceType) {
            model.resourceType = resourceType;
            return this;
        }

        @Override
        public SummaryBuilder rate(float rate) {
            model.rate = rate;
            return this;
        }

        @Override
        public Summary build() {
            return model;
        }

        @Override
        public SummaryBuilder from(Summary in) {
            model = (CloudkittySummary) in;
            return this;
        }
    }

    public static class Summaries extends ListResult<CloudkittySummary> {

        @JsonProperty
        List<CloudkittySummary> summary;

        @Override
        protected List<CloudkittySummary> value() {
            return summary;
        }
    }
}
