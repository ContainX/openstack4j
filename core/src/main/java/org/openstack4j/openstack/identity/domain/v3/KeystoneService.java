package org.openstack4j.openstack.identity.domain.v3;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.identity.builder.v3.ServiceBuilder;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.v3.Service;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * V3 OpenStack service
 *
 */
@JsonRootName("service")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneService implements Service {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String type;
    @JsonProperty
    private List<? extends Endpoint> endpoints;
    private String description;

    private Map<String, String> links;

    @Override
    public ServiceBuilder toBuilder() {
        return new ServiceConcreteBuilder(this);
    }

    public static ServiceBuilder builder() {
        return new ServiceConcreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Endpoint> getEndpoints() {
        return endpoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("type", type)
                .add("endpoints", endpoints)
                .add("links", links)
                .toString();
    }

    public static class Services extends ListResult<KeystoneService> {

        private static final long serialVersionUID = 1L;
        // @JsonProperty("services")
        private List<KeystoneService> list;

        @Override
        protected List<KeystoneService> value() {
            return list;
        }
    }

    public static class ServiceConcreteBuilder implements ServiceBuilder {

        private KeystoneService model;

        ServiceConcreteBuilder() {
            this(new KeystoneService());
        }

        ServiceConcreteBuilder(KeystoneService model) {
            this.model = model;
        }

        @Override
        public Service build() {
            return model;
        }

        @Override
        public ServiceBuilder from(Service in) {
            model = (KeystoneService) in;
            return this;
        }

        @Override
        public ServiceBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public ServiceBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public ServiceBuilder type(String type) {
            model.type = type;
            return this;
        }

        @Override
        public ServiceBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public ServiceBuilder endpoints(List<? extends Endpoint> endpoints) {
            model.endpoints = endpoints;
            return this;
        }

        @Override
        public ServiceBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }
    }
}
