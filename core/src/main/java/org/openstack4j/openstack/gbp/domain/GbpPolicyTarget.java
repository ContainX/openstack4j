package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.PolicyTarget;
import org.openstack4j.model.gbp.builder.PolicyTargetBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
/**
 * Model implementation for Policy Target
 *  
 * @author vinod borole
 */
@JsonRootName("policy_target")
public class GbpPolicyTarget implements PolicyTarget {

    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    @JsonProperty("cluster_id")
    private String clusterId;
    @JsonProperty("policy_target_group_id")
    private String policyTargetGroupId;
    @JsonProperty("port_id")
    private String portId;

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
    public String getDescription() {
        return description;
    }

    @Override
    public String getClusterId() {
        return clusterId;
    }

    @Override
    public String getPolicyTargetGroupId() {
        return policyTargetGroupId;
    }

    @Override
    public String getPortId() {
        return portId;
    }

    @Override
    public PolicyTargetBuilder toBuilder() {
        return new PolicyTargetConcreteBuilder(this);
    }

    public static class PolicyTargets extends ListResult<GbpPolicyTarget>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("policy_targets")
        private List<GbpPolicyTarget> policyTargets;
        @Override
        protected List<GbpPolicyTarget> value() {
            return policyTargets;
        }
        
    }
    
    public static class PolicyTargetConcreteBuilder implements PolicyTargetBuilder{

        private GbpPolicyTarget policyTarget;
        public PolicyTargetConcreteBuilder(GbpPolicyTarget gbpPolicyTarget) {
            this.policyTarget=gbpPolicyTarget;
        }

        @Override
        public PolicyTarget build() {
            return policyTarget;
        }

        @Override
        public PolicyTargetBuilder from(PolicyTarget in) {
            policyTarget=(GbpPolicyTarget) in;
            return this;
        }
        
    }
}
