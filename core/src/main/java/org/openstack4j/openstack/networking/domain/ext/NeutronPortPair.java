package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.*;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;
import org.openstack4j.model.network.ext.builder.VipBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */
@JsonRootName("port_pair")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronPortPair implements PortPair {

    private static final long serialVersionUID = 1L;

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("project_id")
    private String projectId;

    private String name;

    private String description;

    @JsonProperty("ingress")
    private String ingressPortId;

    @JsonProperty("egress")
    private String egressPortId;

    @JsonProperty("service_function_parameters")
    private NeutronPortPairServiceFunctionParameters serviceFunctionParameters;













    @Override
    public PortPairBuilder toBuilder() {
        return new PortPairContreteBuilder() ;
    }

    /**
     *
     * {@inheritDoc}
     */




    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("ingress", ingressPortId)
                .add("egress", ingressPortId)
                .add("description", description)
                .add("service_function_parameters", serviceFunctionParameters)
                .add("projectId", projectId)
                .add("tenantId", tenantId)
                .toString();
    }

    @Override
    public String getEgressPortId() {
        return egressPortId;
    }

    @Override
    public String getIngressPortId() {
        return ingressPortId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PortPairServiceFunctionParameters getServiceFunctionParameters() {
        return serviceFunctionParameters;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }


    /**
     *
     * @author liujunpeng
     *
     */
    public static class PortPairs extends ListResult<NeutronPortPair>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("port_pairs")
        List<NeutronPortPair> portPairList;
        @Override
        public List<NeutronPortPair> value() {
            return portPairList;
        }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("portPairs", portPairList).toString();
        }

    }
    public static class PortPairContreteBuilder implements PortPairBuilder {

        private NeutronPortPair m;
        public PortPairContreteBuilder(){
            this(new NeutronPortPair());
        }
        public PortPairContreteBuilder(NeutronPortPair m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPair build() {
            return m;
        }

        @Override
        public PortPairBuilder from(PortPair in) {
            m = (NeutronPortPair)in;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairBuilder tenantId(String tenantId) {
            m.tenantId = tenantId;
            return this;
        }

        @Override
        public PortPairBuilder projectId(String projectId) {
            m.projectId = projectId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairBuilder name(String name) {
            m.name = name;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairBuilder ingressPortId(String ingressPortId) {
            m.ingressPortId = ingressPortId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairBuilder egressPortId(String egressPortId) {
            m.egressPortId=egressPortId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairBuilder description(String description) {
            m.description = description;
            return this;
        }









    }

    public static PortPairBuilder  builder(){
        return new PortPairContreteBuilder();

    }
}
