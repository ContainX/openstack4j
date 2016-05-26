package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.openstack4j.model.network.MeteringLabel;
import org.openstack4j.model.network.builder.MeteringLabelBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * An OpenStack (Neutron) metering label
 * 
 * @author Caio Bergamasco
 */
@JsonRootName("metering_label")
public class NeutronMeteringLabel implements MeteringLabel {

    private static final long serialVersionUID = 1L;

    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;

    public static MeteringLabelBuilder builder() {
        return new MeteringLabelConcreteBuilder();
    }

    /**
     * Wraps this Metering Label into a Builder
     * @return the meteringLabel builder
     */
    public MeteringLabelBuilder toBuilder() {
        return new MeteringLabelConcreteBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public String getTenantId() {
        return tenantId;
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
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("name", name)
                .add("tenantId", tenantId)
                .add("id", id)
                .add("description", description)
                .toString();
    }
    
    public static class MeteringLabels extends ListResult<NeutronMeteringLabel> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("meteringLabels")
        private List<NeutronMeteringLabel> meteringLabels;

        public List<NeutronMeteringLabel> value() {
            return meteringLabels;
        }
    }

    public static class MeteringLabelConcreteBuilder implements MeteringLabelBuilder {

        private NeutronMeteringLabel m;

        public MeteringLabelConcreteBuilder() {
            this(new NeutronMeteringLabel());
        }

        public MeteringLabelConcreteBuilder(NeutronMeteringLabel m) {
            this.m = m;
        }

        @Override
        public MeteringLabelBuilder name(String name) {
            m.name = name;
            return this;
        }

        @Override
        public MeteringLabelBuilder tenantId(String tenantId) {
            m.tenantId = tenantId;
            return this;
        }
        

        @Override
        public MeteringLabelBuilder description(String description) {
            m.description = description;
            return this;
        }

        @Override
        public MeteringLabel build() {
            return m;
        }

        @Override
        public MeteringLabelBuilder from(MeteringLabel in) {
            m = (NeutronMeteringLabel) in;
            return this;
        }
    }
}
