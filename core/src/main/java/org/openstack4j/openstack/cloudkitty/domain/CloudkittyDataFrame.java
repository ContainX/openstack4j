package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import org.openstack4j.model.cloudkitty.DataFrame;
import org.openstack4j.model.cloudkitty.RatedResource;
import org.openstack4j.model.cloudkitty.builder.DataFrameBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

public class CloudkittyDataFrame implements DataFrame {

    private Date begin;
    private Date end;
    @JsonDeserialize(contentAs = CloudkittyRatedResource.class)
    @JsonSerialize(contentAs = CloudkittyRatedResource.class)
    private List<?extends RatedResource> resources;
    private String tenantId;

    @Override
    public Date getBegin() {
        return begin;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public List<? extends RatedResource> getResources() {
        return resources;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public DataFrameBuilder toBuilder() {
        return new CloudkittyDataFrameConcreteBuilder();
    }

    public static DataFrameBuilder builder() {
        return new CloudkittyDataFrameConcreteBuilder();
    }

    public static class CloudkittyDataFrameConcreteBuilder implements DataFrameBuilder {

        private CloudkittyDataFrame model;

        public CloudkittyDataFrameConcreteBuilder(CloudkittyDataFrame model) {
            this.model = model;
        }

        public CloudkittyDataFrameConcreteBuilder() {
            this(new CloudkittyDataFrame());
        }

        @Override
        public DataFrameBuilder begin(Date begin) {
            model.begin = begin;
            return this;
        }

        @Override
        public DataFrameBuilder end(Date end) {
            model.end = end;
            return this;
        }

        @Override
        public DataFrameBuilder resources(List<? extends RatedResource> resources) {
            model.resources = resources;
            return this;
        }

        @Override
        public DataFrameBuilder tenantId(String tenantId) {
            model.tenantId = tenantId;
            return this;
        }

        @Override
        public DataFrame build() {
            return model;
        }

        @Override
        public DataFrameBuilder from(DataFrame in) {
            model = (CloudkittyDataFrame) in;
            return this;
        }
    }

    public static class DataFrames extends ListResult<CloudkittyDataFrame> {

        @JsonProperty
        List<CloudkittyDataFrame> dataframes;

        @Override
        protected List<CloudkittyDataFrame> value() {
            return dataframes;
        }
    }
}
