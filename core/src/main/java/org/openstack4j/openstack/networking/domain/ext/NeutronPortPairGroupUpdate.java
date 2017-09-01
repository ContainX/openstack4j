package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortPairGroupUpdate;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.PortPairUpdate;
import org.openstack4j.model.network.ext.builder.PortPairGroupUpdateBuilder;
import org.openstack4j.model.network.ext.builder.PortPairUpdateBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */
@JsonRootName("port_pair_group")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronPortPairGroupUpdate implements PortPairGroupUpdate {

    private static final long serialVersionUID = 1L;



    private String name;

    private String description;

    private List<String> portPairs;



    @JsonProperty("port_pairs")
    private PortPairServiceFunctionParameters serviceFunctionParameters;


    @Override
    public List<String> getPortPairs() {
        return portPairs;
    }










    @Override
    public PortPairGroupUpdateBuilder toBuilder() {
        return new PortPairGroupUpdateContreteBuilder() ;
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
                .add("port_pairs", portPairs)
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




    public static class PortPairGroupUpdateContreteBuilder implements PortPairGroupUpdateBuilder {

        private NeutronPortPairGroupUpdate m;
        public PortPairGroupUpdateContreteBuilder(){
            this(new NeutronPortPairGroupUpdate());
        }
        public PortPairGroupUpdateContreteBuilder(NeutronPortPairGroupUpdate m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupUpdate build() {
            return m;
        }

        @Override
        public PortPairGroupUpdateBuilder from(PortPairGroupUpdate in) {
            m = (NeutronPortPairGroupUpdate)in;
            return this;
        }





        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupUpdateBuilder name(String name) {
            m.name = name;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupUpdateBuilder description(String description) {
            m.description = description;
            return this;
        }

        @Override
        public PortPairGroupUpdateBuilder portPairs(List<String> portPairs) {
            m.portPairs = portPairs;
            return this;
        }


    }

    public static PortPairGroupUpdateBuilder  builder(){
        return new PortPairGroupUpdateContreteBuilder();

    }
}
