package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.Resource;
import org.openstack4j.model.cloudkitty.builder.ResourceBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;
import java.util.Map;

public class CloudkittyResource implements Resource {

    @JsonProperty("desc")
    private Map<String, String> description;
    private String service;
    private float volume;

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
    public ResourceBuilder toBuilder() {
        return new CloudkittyResourceConcreteBuilder(this);
    }

    public static ResourceBuilder builder() {
        return new CloudkittyResourceConcreteBuilder();
    }

    public static class CloudkittyResourceConcreteBuilder implements ResourceBuilder {

        private CloudkittyResource model;

        public CloudkittyResourceConcreteBuilder(CloudkittyResource model) {
            this.model = model;
        }

        public CloudkittyResourceConcreteBuilder() {
            this(new CloudkittyResource());
        }

        @Override
        public ResourceBuilder description(Map<String, String> description) {
            model.description = description;
            return this;
        }

        @Override
        public ResourceBuilder service(String service) {
            model.service = service;
            return this;
        }

        @Override
        public ResourceBuilder volume(float volume) {
            model.volume = volume;
            return this;
        }

        @Override
        public Resource build() {
            return model;
        }

        @Override
        public ResourceBuilder from(Resource in) {
            model = (CloudkittyResource) in;
            return this;
        }
    }

    public static class Resources extends ListResult<CloudkittyResource> {

        @JsonProperty
        List<CloudkittyResource> resources;

        public Resources(List<CloudkittyResource> resources) {
            this.resources = resources;
        }

        public Resources() {
        }

        @Override
        protected List<CloudkittyResource> value() {
            return resources;
        }
    }
}