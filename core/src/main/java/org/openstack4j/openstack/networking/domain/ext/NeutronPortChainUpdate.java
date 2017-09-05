package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortChainParameters;
import org.openstack4j.model.network.ext.PortChainUpdate;
import org.openstack4j.model.network.ext.builder.PortChainBuilder;
import org.openstack4j.model.network.ext.builder.PortChainUpdateBuilder;
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
public class NeutronPortChainUpdate implements PortChainUpdate {

    private static final long serialVersionUID = 1L;



    private String name;

    private String description;



    @JsonProperty("port_pair_groups")
    private List<String> portPairGroups;

    @JsonProperty("flow_classifiers")
    private List<String> flowClassifiers;















    @Override
    public PortChainUpdateBuilder toBuilder() {
        return new PortChainUpdateContreteBuilder() ;
    }

    /**
     *
     * {@inheritDoc}
     */




    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("name", name)
                .add("description", description)
                .add("port_pair_groups", portPairGroups)
                .add("flow_classifiers",flowClassifiers)
                .toString();
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
    public List<String> getPortPairGroups() {
        return portPairGroups;
    }

    @Override
    public List<String> getFlowClassifiers() {
        return flowClassifiers;
    }

    public static class PortChainUpdateContreteBuilder implements PortChainUpdateBuilder {

        private NeutronPortChainUpdate m;
        public PortChainUpdateContreteBuilder(){
            this(new NeutronPortChainUpdate());
        }
        public PortChainUpdateContreteBuilder(NeutronPortChainUpdate m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChainUpdate build() {
            return m;
        }

        @Override
        public PortChainUpdateBuilder from(PortChainUpdate in) {
            m = (NeutronPortChainUpdate)in;
            return this;
        }




        @Override
        public PortChainUpdateBuilder flowClassifiers(List<String> flowClassifiers) {
            m.flowClassifiers = flowClassifiers;
            return this;
        }

        @Override
        public PortChainUpdateBuilder portPairGroups(List<String> portPairGroups) {
            m.portPairGroups = portPairGroups;
            return this;
        }



        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChainUpdateBuilder name(String name) {
            m.name = name;
            return this;
        }



        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortChainUpdateBuilder description(String description) {
            m.description = description;
            return this;
        }









    }

    public static PortChainUpdateBuilder builder(){
        return new PortChainUpdateContreteBuilder();

    }
}
