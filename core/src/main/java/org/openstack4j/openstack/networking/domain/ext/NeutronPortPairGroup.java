package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.model.network.ext.PortPairGroupParameters;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;
import org.openstack4j.model.network.ext.builder.PortPairGroupBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * @author Massimiliano Romano
 *
 */
@JsonRootName("port_pair_group")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NeutronPortPairGroup implements PortPairGroup {

    private static final long serialVersionUID = 1L;

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("project_id")
    private String projectId;

    private String name;

    private String description;

    @JsonProperty("port_pair_group_parameters")
    private NeutronPortPairGroupParameters portPairGroupParameters;

    @JsonProperty("port_pairs")
    private List<String> portPairs;










    @Override
    public PortPairGroupBuilder toBuilder() {
        return new PortPairGroupContreteBuilder() ;
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
                .add("groupId", groupId)
                .add("description", description)
                .add("portPairs",portPairs)
                .add("port_pair_group_parameters", portPairGroupParameters)
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
    public String getGroupId() {
        return groupId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PortPairGroupParameters getPortPairGroupParameters() {
        return portPairGroupParameters;
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
    public List<String> getPortPairs() {
        return portPairs;
    }


    /**
     *
     * @author Massimiliano Romano
     *
     */
    public static class PortPairGroups extends ListResult<NeutronPortPairGroup>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("port_pair_groups")
        List<NeutronPortPairGroup> portPairGroupList;
        @Override
        public List<NeutronPortPairGroup> value() {
            return portPairGroupList;
        }
        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("portPairGroups", portPairGroupList).toString();
        }

    }
    public static class PortPairGroupContreteBuilder implements PortPairGroupBuilder {

        private NeutronPortPairGroup m;
        public PortPairGroupContreteBuilder(){
            this(new NeutronPortPairGroup());
        }
        public PortPairGroupContreteBuilder(NeutronPortPairGroup m){
            this.m = m;

        }
        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroup build() {
            return m;
        }

        @Override
        public PortPairGroupBuilder from(PortPairGroup in) {
            m = (NeutronPortPairGroup)in;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupBuilder tenantId(String tenantId) {
            m.tenantId = tenantId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupBuilder projectId(String projectId) {
            m.projectId = projectId;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupBuilder portPairs(List<String> portPairs) {
            m.portPairs=portPairs;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupBuilder portPairGroupParameters(PortPairGroupParameters portPairGroupParameters) {
            m.portPairGroupParameters = (NeutronPortPairGroupParameters)portPairGroupParameters;
            return this;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupBuilder name(String name) {
            m.name = name;
            return this;
        }



        /**
         *
         * {@inheritDoc}
         */
        @Override
        public PortPairGroupBuilder description(String description) {
            m.description = description;
            return this;
        }









    }

    public static PortPairGroupBuilder  builder(){
        return new PortPairGroupContreteBuilder();

    }
}
