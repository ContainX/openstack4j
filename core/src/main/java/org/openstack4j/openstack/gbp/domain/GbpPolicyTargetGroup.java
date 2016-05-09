package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.PolicyTargetGroup;
import org.openstack4j.model.gbp.builder.PolicyTargetGroupBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * Model implementation for Policy target group
 * 
 * @author vinod borole
 */
@JsonRootName("policy_target_group")
public class GbpPolicyTargetGroup implements PolicyTargetGroup {

    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    @JsonProperty("consumed_policy_rule_sets")
    private List<String> consumedPolicyRuleSets;
    @JsonProperty("provided_policy_rule_sets")
    private List<String> providedPolicyRuleSets;
    @JsonProperty("l2_policy_id")
    private String l2PolicyId;
    @JsonProperty("network_service_policy_id")
    private String networkServicePolicyId;
    @JsonProperty("policy_targets")
    private List<String> policyTargets;
    @JsonProperty("service_management")
    private Boolean serviceManagement;
    private Boolean shared;
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
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public List<String> getConsumedPolicyRuleSets() {
        return consumedPolicyRuleSets;
    }

    @Override
    public List<String> getProvidedPolicyRuleSets() {
        return providedPolicyRuleSets;
    }

    @Override
    public String getL2PolicyId() {
        return l2PolicyId;
    }

    @Override
    public String getNetworkServicePolicyId() {
        return networkServicePolicyId;
    }

    @Override
    public List<String> getPolicyTargets() {
        return policyTargets;
    }

    @Override
    public boolean isServiceManagement() {
        return this.serviceManagement == null ? false : shared;
    }

    @Override
    public boolean isShared() {
        return this.shared == null ? false : shared;
    }

    @Override
    public List<String> getSubnets() {
        return subnets;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues().add("id", id).add("name", name).add("desription", description)
                .add("tenantId", tenantId).add("consumedPolicyRuleSets", consumedPolicyRuleSets).add("providedPolicyRuleSets", providedPolicyRuleSets)
                .add("l2PolicyId", l2PolicyId).add("networkServicePolicyId", networkServicePolicyId)
                .add("policyTargets", policyTargets).add("serviceManagement", serviceManagement).add("shared", shared).add("subnets", subnets).toString();
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

        public PolicyTargetConcreteGroupBuilder() {
            this(new GbpPolicyTargetGroup());
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

        @Override
        public PolicyTargetGroupBuilder name(String name) {
            policyTargetGroup.name=name;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder description(String description) {
            this.policyTargetGroup.description=description;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder isShared(boolean shared) {
            this.policyTargetGroup.shared=shared;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder consumedPolicyRuleSets(List<String> policyRuleSet) {
            this.policyTargetGroup.consumedPolicyRuleSets=policyRuleSet;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder providedPolicyRuleSets(List<String> policyRuleSet) {
            this.policyTargetGroup.providedPolicyRuleSets=policyRuleSet;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder policyTargets(List<String> policyTargets) {
            this.policyTargetGroup.policyTargets=policyTargets;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder networkServicePolicyId(String id) {
            this.policyTargetGroup.networkServicePolicyId=id;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder l2Policy(String id) {
            this.policyTargetGroup.l2PolicyId=id;
            return this;
        }

        @Override
        public PolicyTargetGroupBuilder serviceManagement(boolean serviceManagement) {
            this.policyTargetGroup.serviceManagement=serviceManagement;
            return this;
        }
        
    }

    public static PolicyTargetGroupBuilder builder() {
        return new PolicyTargetConcreteGroupBuilder();
    }
    
}
