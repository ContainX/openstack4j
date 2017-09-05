package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortChainParameters;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.builder.PortChainBuilder;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */

/*
    {
      "port_chain": {
        "tenant_id": "fa30ea019b0d43d7a917b31f28a5efdb",
        "name": "PC1",
        "chain_parameters": {
          "symmetric": false,
          "correlation": "mpls"
        },
        "port_pair_groups": ["bf587d66-4427-4f27-a4f6-e6dcb362f7c5"],
        "flow_classifiers": ["3e70c929-eb89-4647-b48a-c6829632a08b"],
        "project_id": "fa30ea019b0d43d7a917b31f28a5efdb",
        "chain_id": 1,
        "id": "f56ba0f9-4a0e-4252-8bff-fecd5d418fea",
        "description": ""
      }
    }
*/

@JsonRootName("port_chain")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronPortChain implements PortChain {

    private static final long serialVersionUID = 1L;

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("project_id")
    private String projectId;

    private String name;

    private String description;

    @JsonProperty("chain_id")
    private Integer chainId;

    @JsonProperty("port_pair_groups")
    private List<String> portPairGroups;

    @JsonProperty("flow_classifiers")
    private List<String> flowClassifiers;


    @JsonProperty("chain_parameters")
    private NeutronPortChainParameters portChainParameters;













    @Override
    public PortChainBuilder toBuilder() {
        return new PortChainContreteBuilder() ;
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
                .add("chain_id", chainId)
                .add("port_pair_groups", portPairGroups)
                .add("flow_classifiers",flowClassifiers)
                .add("port_chain_parameters", portChainParameters)
                .add("projectId", projectId)
                .add("tenantId", tenantId)
                .toString();
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
    public PortChainParameters getPortChainParameters() {
        return portChainParameters;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public Integer getChainId() {
        return chainId;
    }

    @Override
    public List<String> getPortPairGroups() {
        return portPairGroups;
    }

    @Override
    public List<String> getFlowClassifiers() {
        return flowClassifiers;
    }

    /**
     *
     * @author Massimiliano Romano
     *
     */
    public static class PortChains extends ListResult<NeutronPortChain>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("port_chains")
        List<NeutronPortChain> portChainList;
        @Override
        public List<NeutronPortChain> value() {
            return portChainList;
        }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("portChains", portChainList).toString();
        }

    }
    public static class PortChainContreteBuilder implements PortChainBuilder {

        private NeutronPortChain m;
        public PortChainContreteBuilder(){
            this(new NeutronPortChain());
        }
        public PortChainContreteBuilder(NeutronPortChain m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChain build() {
            return m;
        }

        @Override
        public PortChainBuilder from(PortChain in) {
            m = (NeutronPortChain)in;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChainBuilder tenantId(String tenantId) {
            m.tenantId = tenantId;
            return this;
        }

        @Override
        public PortChainBuilder projectId(String projectId) {
            m.projectId = projectId;
            return this;
        }

        @Override
        public PortChainBuilder flowClassifiers(List<String> flowClassifiers) {
            m.flowClassifiers = flowClassifiers;
            return this;
        }

        @Override
        public PortChainBuilder portPairGroups(List<String> portPairGroups) {
            m.portPairGroups = portPairGroups;
            return this;
        }

        @Override
        public PortChainBuilder portChainParameters(PortChainParameters portChainParameters) {
            m.portChainParameters = (NeutronPortChainParameters) portChainParameters;
            return this;
        }


        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChainBuilder name(String name) {
            m.name = name;
            return this;
        }



        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChainBuilder description(String description) {
            m.description = description;
            return this;
        }









    }

    public static PortChainBuilder builder(){
        return new PortChainContreteBuilder();

    }
}
