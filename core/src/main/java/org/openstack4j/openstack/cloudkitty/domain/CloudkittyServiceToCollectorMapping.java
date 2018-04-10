package org.openstack4j.openstack.cloudkitty.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.ServiceToCollectorMapping;
import org.openstack4j.model.cloudkitty.builder.ServiceToCollectorMappingBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * A service to collector mapping
 * @author mariusleu
 */
public class CloudkittyServiceToCollectorMapping implements ServiceToCollectorMapping {

    private String collector;
    private String service;

    @Override
    public String getCollector() {
        return collector;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public ServiceToCollectorMappingBuilder toBuilder() {
        return new CloudkittyServiceToCollectorMappingConcreteBuilder(this);
    }

    public static ServiceToCollectorMappingBuilder builder() {
        return new CloudkittyServiceToCollectorMappingConcreteBuilder();
    }

    public static class CloudkittyServiceToCollectorMappingConcreteBuilder implements ServiceToCollectorMappingBuilder {

        private CloudkittyServiceToCollectorMapping model;

        public CloudkittyServiceToCollectorMappingConcreteBuilder() {
            this(new CloudkittyServiceToCollectorMapping());
        }

        public CloudkittyServiceToCollectorMappingConcreteBuilder(CloudkittyServiceToCollectorMapping model) {
            this.model = model;
        }

        @Override
        public ServiceToCollectorMappingBuilder collector(String collector) {
            model.collector = collector;
            return this;
        }

        @Override
        public ServiceToCollectorMappingBuilder service(String service) {
            model.service = service;
            return this;
        }

        @Override
        public ServiceToCollectorMapping build() {
            return model;
        }

        @Override
        public ServiceToCollectorMappingBuilder from(ServiceToCollectorMapping in) {
            model = (CloudkittyServiceToCollectorMapping) in;
            return this;
        }
    }

    public static class Mappings extends ListResult<CloudkittyServiceToCollectorMapping> {

        @JsonProperty
        private List<CloudkittyServiceToCollectorMapping> mappings;

        @Override
        protected List<CloudkittyServiceToCollectorMapping> value() {
            return mappings;
        }
    }
}
