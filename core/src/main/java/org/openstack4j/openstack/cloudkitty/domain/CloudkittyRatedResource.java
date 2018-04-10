package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.RatedResource;
import org.openstack4j.model.cloudkitty.builder.RatedResourceBuilder;

import java.util.Map;

public class CloudkittyRatedResource implements RatedResource {

    @JsonProperty("desc")
    private Map<String, String> description;
    private String service;
    private float volume;
    private float rating;

    @Override
    public Map<String, String> getDescription() {
        return description;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public float getRating() {
        return rating;
    }

    @Override
    public RatedResourceBuilder toBuilder() {
        return new CloudkittyRatedResourceConcreteBuilder(this);
    }

    public static CloudkittyRatedResourceConcreteBuilder builder() {
        return new CloudkittyRatedResourceConcreteBuilder();
    }

    public static class CloudkittyRatedResourceConcreteBuilder implements RatedResourceBuilder {

        private CloudkittyRatedResource model;

        public CloudkittyRatedResourceConcreteBuilder() {
            this(new CloudkittyRatedResource());
        }

        public CloudkittyRatedResourceConcreteBuilder(CloudkittyRatedResource model) {
            this.model = model;
        }

        @Override
        public RatedResourceBuilder description(Map<String, String> description) {
            model.description = description;
            return this;
        }

        @Override
        public RatedResourceBuilder service(String service) {
            model.service = service;
            return this;
        }

        @Override
        public RatedResourceBuilder volume(float volume) {
            model.volume = volume;
            return this;
        }

        @Override
        public RatedResourceBuilder rating(float rating) {
            model.rating = rating;
            return this;
        }

        @Override
        public RatedResource build() {
            return model;
        }

        @Override
        public RatedResourceBuilder from(RatedResource in) {
            model = (CloudkittyRatedResource) in;
            return this;
        }
    }
}
