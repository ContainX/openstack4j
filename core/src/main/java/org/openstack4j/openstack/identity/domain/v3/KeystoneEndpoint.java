package org.openstack4j.openstack.identity.domain.v3;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.types.Facing;
import org.openstack4j.model.identity.builder.v3.EndpointBuilder;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * Keystone V3 endpoint model class
 * 
 */
@JsonRootName("endpoint")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneEndpoint implements Endpoint {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String id;
    private String description;
    @JsonProperty("interface")
    private Facing iface;
    @JsonIgnore
    private String serviceId;
    private String name;
    @JsonProperty
    private String region;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty
    private URL url;
    private Map<String, String> links;

    /**
     * @return the endpoint builder
     */
    public static EndpointBuilder builder() {
        return new EndpointConcreteBuilder();
    }

    @Override
    public EndpointBuilder toBuilder() {
        return new EndpointConcreteBuilder();
    }

    /**
     * @return the id of the endpoint
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @return the description of the endpoint
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return the iface (interface) of the endpoint
     */
    @Override
    public Facing getIface() {
        return iface;
    }

    /**
     * @return the serviceId of the endpoint
     */
    @Override
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @return the name of the endpoint
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the region of the endpoint
     */
    @Override
    public String getRegion() {
        return region;
    }

    /**
     * @return the region identifier of the endpoint
     */
    @Override
    public String getRegionId() {
        return regionId;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("interface", iface)
                .add("serviceId", serviceId)
                .add("region", region)
                .add("url", url)
                .add("links", links)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, description, iface, serviceId, region, url, links);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeystoneEndpoint that = KeystoneEndpoint.class.cast(obj);
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.name, that.name)
                && Objects.equal(this.description, that.description)
                && Objects.equal(this.iface, that.iface)
                && Objects.equal(this.serviceId, that.serviceId)
                && Objects.equal(this.region, that.region)
                && Objects.equal(this.url, that.url)
                && Objects.equal(this.links, that.links);
    }

    public static class Endpoints extends ListResult<KeystoneEndpoint> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("endpoints")
        private List<KeystoneEndpoint> list;

        @Override
        protected List<KeystoneEndpoint> value() {
            return list;
        }

        public List<KeystoneEndpoint> all() {
            return list;
        }
    }

    public static class EndpointConcreteBuilder implements EndpointBuilder {

        KeystoneEndpoint model;

        EndpointConcreteBuilder() {
            this(new KeystoneEndpoint());
        }

        EndpointConcreteBuilder(KeystoneEndpoint model) {
            this.model = model;
        }

        /**
         * @see KeystoneEndpoint#getId()
         */
        public EndpointBuilder id(String id) {
            model.id = id;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getDescription()
         */
        public EndpointBuilder description(String description) {
            model.description = description;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getIface()
         */
        public EndpointBuilder iface(Facing iface) {
            model.iface = iface;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getServiceId()
         */
        public EndpointBuilder serviceId(String serviceId) {
            model.serviceId = serviceId;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getName()
         */
        public EndpointBuilder name(String name) {
            model.name = name;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getRegion()
         */
        public EndpointBuilder region(String region) {
            model.region = region;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getUrl()
         */
        public EndpointBuilder url(URL url) {
            model.url = url;
            return this;
        }

        /**
         * @see KeystoneEndpoint#getLinks()
         */
        public EndpointBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Endpoint build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EndpointBuilder from(Endpoint in) {
            this.model = (KeystoneEndpoint) in;
            return this;
        }

    }

}
