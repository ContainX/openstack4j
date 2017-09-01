package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.FlowClassifierUpdate;
import org.openstack4j.model.network.ext.builder.FlowClassifierBuilder;
import org.openstack4j.model.network.ext.builder.FlowClassifierUpdateBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */
@JsonRootName("flow_classifier")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronFlowClassifierUpdate implements FlowClassifierUpdate {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;


    @Override
    public FlowClassifierUpdateBuilder toBuilder() {
        return new FlowClassifierUpdateContreteBuilder() ;
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




    public static class FlowClassifierUpdateContreteBuilder implements FlowClassifierUpdateBuilder {

        private NeutronFlowClassifierUpdate m;
        public FlowClassifierUpdateContreteBuilder(){
            this(new NeutronFlowClassifierUpdate());
        }
        public FlowClassifierUpdateContreteBuilder(NeutronFlowClassifierUpdate m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierUpdate build() {
            return m;
        }

        @Override
        public FlowClassifierUpdateBuilder from(FlowClassifierUpdate in) {
            m = (NeutronFlowClassifierUpdate)in;
            return this;
        }


        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierUpdateBuilder name(String name) {
            m.name = name;
            return this;
        }




        /**
         *
         * {@inheritDoc}
         */
        @Override
        public FlowClassifierUpdateBuilder description(String description) {
            m.description = description;
            return this;
        }




    }

    public static FlowClassifierUpdateBuilder  builder(){
        return new FlowClassifierUpdateContreteBuilder();

    }
}
