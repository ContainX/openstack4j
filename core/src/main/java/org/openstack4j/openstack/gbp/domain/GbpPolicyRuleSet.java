package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.PolicyRuleSet;
import org.openstack4j.model.gbp.builder.PolicyRuleSetBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("policy_rule_set")
public class GbpPolicyRuleSet implements PolicyRuleSet {
    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    private boolean shared;
    @JsonProperty("parent_id")
    private String parentId;
    @JsonProperty("child_policy_rule_sets")
    private List<String> childPolicyRuleSets;
    @JsonProperty("policy_rules")
    private List<GbpPolicyRule> policyRules;
    
    
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
    public boolean isShared() {
        return shared;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public List<String> getChildPolicyRuleSets() {
        return childPolicyRuleSets;
    }
    @Override
    public List<GbpPolicyRule> getPolicyRules() {
        return policyRules;
    }
   
    @Override
    public PolicyRuleSetBuilder toBuilder() {
        return new PolicyRuleSetConcreteBuilder(this);
    }
    
    public static class PolicyRuleSets extends ListResult<GbpPolicyRuleSet>{

        private static final long serialVersionUID = 1L;
        @JsonProperty("policy_rule_sets")
        private List<GbpPolicyRuleSet> policyRuleSets;
        @Override
        protected List<GbpPolicyRuleSet> value() {
            return policyRuleSets;
        }
        
    }
    public static class PolicyRuleSetConcreteBuilder implements PolicyRuleSetBuilder{

        private GbpPolicyRuleSet policyRuleSet;
        
        public PolicyRuleSetConcreteBuilder(GbpPolicyRuleSet gbpPolicyRuleSet) {
            this.policyRuleSet=gbpPolicyRuleSet;
        }

        @Override
        public PolicyRuleSet build() {
            return policyRuleSet;
        }

        @Override
        public PolicyRuleSetBuilder from(PolicyRuleSet in) {
            this.policyRuleSet=(GbpPolicyRuleSet) in;
            return this;
        }
        
    }

}
