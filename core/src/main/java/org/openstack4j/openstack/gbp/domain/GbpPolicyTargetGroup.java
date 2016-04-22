package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.PolicyRuleSet;
import org.openstack4j.model.gbp.PolicyTarget;
import org.openstack4j.model.gbp.PolicyTargetGroup;
import org.openstack4j.model.gbp.builder.PolicyTargetGroupBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("policy_target_group")
public class GbpPolicyTargetGroup implements PolicyTargetGroup {

    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    @JsonProperty("consumed_policy_rule_sets")
    private List<PolicyRuleSet> consumedPolicyRuleSets;
    @JsonProperty("provided_policy_rule_sets")
    private List<PolicyRuleSet> providedPolicyRuleSets;
    @JsonProperty("l2_policy_id")
    private String l2PolicyId;
    @JsonProperty("network_service_policy_id")
    private String networkServicePolicyId;
    @JsonProperty("policy_targets")
    private List<PolicyTarget> policyTargets;
    @JsonProperty("service_management")
    private boolean serviceManagement;
    private boolean shared;
    @JsonProperty("subnets")
    private List<String> subnets;
    
    @Override
    public PolicyTargetGroupBuilder toBuilder() { 
        return new PolicyTargetConcreteGroupBuilder(this); 
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PolicyRuleSet> getConsumedPolicyRuleSets() {
        return consumedPolicyRuleSets;
    }

    public void setConsumedPolicyRuleSets(List<PolicyRuleSet> consumedPolicyRuleSets) {
        this.consumedPolicyRuleSets = consumedPolicyRuleSets;
    }

    public List<PolicyRuleSet> getProvidedPolicyRuleSets() {
        return providedPolicyRuleSets;
    }

    public void setProvidedPolicyRuleSets(List<PolicyRuleSet> providedPolicyRuleSets) {
        this.providedPolicyRuleSets = providedPolicyRuleSets;
    }

    public String getL2PolicyId() {
        return l2PolicyId;
    }

    public void setL2PolicyId(String l2PolicyId) {
        this.l2PolicyId = l2PolicyId;
    }

    public String getNetworkServicePolicyId() {
        return networkServicePolicyId;
    }

    public void setNetworkServicePolicyId(String networkServicePolicyId) {
        this.networkServicePolicyId = networkServicePolicyId;
    }

    public List<PolicyTarget> getPolicyTargets() {
        return policyTargets;
    }

    public void setPolicyTargets(List<PolicyTarget> policyTargets) {
        this.policyTargets = policyTargets;
    }

    public boolean isServiceManagement() {
        return serviceManagement;
    }

    public void setServiceManagement(boolean serviceManagement) {
        this.serviceManagement = serviceManagement;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public List<String> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<String> subnets) {
        this.subnets = subnets;
    }
    
    public static class PolicyTargetGroups extends ListResult<GbpPolicyTargetGroup>{
        private static final long serialVersionUID = 1L;
        
        @JsonProperty("policy_target_groups")
        private List<GbpPolicyTargetGroup> policyTargetGroups;

        @Override
        public List<GbpPolicyTargetGroup> value() {
            return policyTargetGroups;
        }
    }
    
    public static class PolicyTargetConcreteGroupBuilder implements PolicyTargetGroupBuilder{
        private GbpPolicyTargetGroup policyTargetGroup;
        
        public PolicyTargetConcreteGroupBuilder(GbpPolicyTargetGroup gbpPolicyTargetGroup) {
            this.policyTargetGroup=gbpPolicyTargetGroup;
        }

        @Override
        public PolicyTargetGroup build() {
            return policyTargetGroup;
        }

        @Override
        public PolicyTargetGroupBuilder from(PolicyTargetGroup in) {
            policyTargetGroup=(GbpPolicyTargetGroup) in;
            return this;
        }
        
    }
    
}
