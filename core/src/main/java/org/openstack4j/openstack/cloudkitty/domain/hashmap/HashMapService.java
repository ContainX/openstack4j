package org.openstack4j.openstack.cloudkitty.domain.hashmap;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.builder.hashmap.ServiceBuilder;
import org.openstack4j.model.cloudkitty.hashmap.Service;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class HashMapService implements Service {
    @JsonProperty("service_id")
    private String id;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ServiceBuilder toBuilder() {
        return new HashMapServiceConcreteBuilder(this);
    }

    public static ServiceBuilder builder() {
        return new HashMapServiceConcreteBuilder();
    }

    public static class HashMapServiceConcreteBuilder implements ServiceBuilder {

        HashMapService model;

        public HashMapServiceConcreteBuilder(HashMapService model) {
            this.model = model;
        }

        public HashMapServiceConcreteBuilder() {
            this(new HashMapService());
        }

        @Override
        public ServiceBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public ServiceBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public Service build() {
            return model;
        }

        @Override
        public ServiceBuilder from(Service in) {
            model = (HashMapService) in;
            return this;
        }
    }

    public static class Services extends ListResult<HashMapService> {

        @JsonProperty
        private List<HashMapService> services;

        @Override
        protected List<HashMapService> value() {
            return services;
        }
    }
}
