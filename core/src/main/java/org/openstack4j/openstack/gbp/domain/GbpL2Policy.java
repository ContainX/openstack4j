package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.L2Policy;
import org.openstack4j.model.gbp.PolicyTargetGroup;
import org.openstack4j.model.gbp.builder.L2PolicyBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("l2_policy")
public class GbpL2Policy implements L2Policy {

    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    @JsonProperty("network_id")
    private String networkId;
    @JsonProperty("l3_policy_id")
    private String l3PolicyId;
    private boolean shared;
    @JsonProperty("policy_target_groups")
    private List<PolicyTargetGroup> policyTargetGroups;
    
    
    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId=tenantId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public L2PolicyBuilder toBuilder() {
        return new L2PolicyConcreteBuilder(this) ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getL3PolicyId() {
        return l3PolicyId;
    }

    public void setL3PolicyId(String l3PolicyId) {
        this.l3PolicyId = l3PolicyId;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public List<PolicyTargetGroup> getPolicyTargetGroups() {
        return policyTargetGroups;
    }

    public void setPolicyTargetGroups(List<PolicyTargetGroup> policyTargetGroups) {
        this.policyTargetGroups = policyTargetGroups;
    }
    
    public static class L2Policies extends ListResult<GbpL2Policy>{
        private static final long serialVersionUID = 1L;
        @JsonProperty("l2_policies")
        private List<GbpL2Policy> l2Policies;
        
        @Override
        protected List<GbpL2Policy> value() {
            return l2Policies;
        }
        
    }
    
    public static class L2PolicyConcreteBuilder implements L2PolicyBuilder{

        private GbpL2Policy l2Policy;
        
        public L2PolicyConcreteBuilder(GbpL2Policy gbpL2Policy) {
            this.l2Policy=gbpL2Policy;
        }

        @Override
        public L2Policy build() {
            return l2Policy;
        }

        @Override
        public L2PolicyBuilder from(L2Policy in) {
            l2Policy= (GbpL2Policy) in;
            return this;
        }

        
        
    }

    
    
}
