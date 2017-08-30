package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.PortPairUpdate;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;
import org.openstack4j.model.network.ext.builder.PortPairUpdateBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */
@JsonRootName("port_pair")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronPortPairUpdate implements PortPairUpdate {

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
    private PortPairServiceFunctionParameters serviceFunctionParameters;













    @Override
    public PortPairUpdateBuilder toBuilder() {
        return new PortPairUpdateContreteBuilder() ;
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
    public String getDescription() {
        return description;
    }


    @Override
    public String getName() {
        return name;
    }



    /**
     *
     * @author liujunpeng
     *
     */
    public static class PortPairs extends ListResult<NeutronPortPairUpdate>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("port_pairs")
        List<NeutronPortPairUpdate> portPairList;
        @Override
        public List<NeutronPortPairUpdate> value() {
            return portPairList;
        }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("portPairs", portPairList).toString();
        }

    }
    public static class PortPairUpdateContreteBuilder implements PortPairUpdateBuilder {

        private NeutronPortPairUpdate m;
        public PortPairUpdateContreteBuilder(){
            this(new NeutronPortPairUpdate());
        }
        public PortPairUpdateContreteBuilder(NeutronPortPairUpdate m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairUpdate build() {
            return m;
        }

        @Override
        public PortPairUpdateBuilder from(PortPairUpdate in) {
            m = (NeutronPortPairUpdate)in;
            return this;
        }





        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairUpdateBuilder name(String name) {
            m.name = name;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairUpdateBuilder description(String description) {
            m.description = description;
            return this;
        }



    }

    public static PortPairUpdateBuilder  builder(){
        return new PortPairUpdateContreteBuilder();

    }
}
