package org.openstack4j.openstack.identity.domain.v3;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.identity.builder.v3.DomainBuilder;
import org.openstack4j.model.identity.v3.Domain;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@JsonRootName("domain")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeystoneDomain implements Domain {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    private String description;
    private Map<String, String> links;
    private boolean enabled;

    /**
     * @return the domain builder
     */
    public static DomainBuilder builder() {
        return new DomainConcreteBuilder();
    }

    @Override
    public DomainBuilder toBuilder() {
        return new DomainConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("description", description)
                .add("name", name)
                .add("links", links)
                .add("enabled", enabled)
                .toString();
    }

    public int hashCode() {
        return Objects.hashCode(id, description, enabled, name, links);
    }

    public static class DomainConcreteBuilder implements DomainBuilder {

        KeystoneDomain model;

        public DomainConcreteBuilder() {
            this(new KeystoneDomain());
        }

        DomainConcreteBuilder(KeystoneDomain model) {
            this.model = model;
        }

        @Override
        public Domain build() {
            return model;
        }

        @Override
        public DomainBuilder from(Domain in) {
            if (in != null)
                this.model = (KeystoneDomain) in;
            return this;
        }

        public DomainBuilder id(String id) {
            model.id = id;
            return this;
        }

        public DomainBuilder description(String description) {
            model.description = description;
            return this;
        }

        public DomainBuilder name(String name) {
            model.name = name;
            return this;
        }

        public DomainBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        public DomainBuilder enabled(boolean enabled) {
            model.enabled = enabled;
            return this;
        }

    }

    public static class Domains extends ListResult<KeystoneDomain> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("domains")
        protected List<KeystoneDomain> list;

        protected List<KeystoneDomain> value() {
            return list;
        }

    }

}
