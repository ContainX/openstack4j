package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.PolicyAction;
import org.openstack4j.model.gbp.PolicyRule;
import org.openstack4j.model.gbp.builder.PolicyRuleBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("policy_rule")
public class GbpPolicyRule implements PolicyRule {

    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    @JsonProperty("policy_classifier_id")
    private String policyClassifierId;
    @JsonProperty("policy_actions")
    private List<PolicyAction> policyActions;
    private boolean shared;
    private boolean enabled;
    
    
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

    public String getPolicyClassifierId() {
        return policyClassifierId;
    }

    public void setPolicyClassifierId(String policyClassifierId) {
        this.policyClassifierId = policyClassifierId;
    }

    public List<PolicyAction> getPolicyActions() {
        return policyActions;
    }

    public void setPolicyActions(List<PolicyAction> policyActions) {
        this.policyActions = policyActions;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public PolicyRuleBuilder toBuilder() {
        return new PolicyRuleConcreteBuilder(this);
    }
    
    public static class PolicyRules extends ListResult<GbpPolicyRule>{

        @JsonProperty("policy_rules")
        private List<GbpPolicyRule> policyRules;
        @Override
        protected List<GbpPolicyRule> value() {
            return policyRules;
        }
        
    }
    
    public static class PolicyRuleConcreteBuilder implements PolicyRuleBuilder{

        private GbpPolicyRule policyRule;
        
        public PolicyRuleConcreteBuilder(GbpPolicyRule gbpPolicyRule) {
            this.policyRule=gbpPolicyRule;
        }

        @Override
        public PolicyRule build() {
            return policyRule;
        }

        @Override
        public PolicyRuleBuilder from(PolicyRule in) {
            this.policyRule=(GbpPolicyRule) in;
            return this;
        }
        
        
    }

}
