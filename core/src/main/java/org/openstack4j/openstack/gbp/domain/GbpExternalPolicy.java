package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.ExternalPolicy;
import org.openstack4j.model.gbp.PolicyRuleSet;
import org.openstack4j.model.gbp.builder.ExternalPolicyBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

@JsonRootName("external_policy")
public class GbpExternalPolicy implements ExternalPolicy{
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    @JsonProperty("consumed_policy_rule_sets")
    private List<PolicyRuleSet> consumedPolicyRuleSets;
    @JsonProperty("provided_policy_rule_sets")
    private List<PolicyRuleSet> providedPolicyRuleSets;
    @JsonProperty("external_segments")
    private List<String> externalSegments;
    private boolean shared;
    
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

    public List<String> getExternalSegments() {
        return externalSegments;
    }

    public void setExternalSegments(List<String> externalSegments) {
        this.externalSegments = externalSegments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    @Override
    public ExternalPolicyBuilder toBuilder() {
        return new ExternalPolicyConcreteBuilder(this);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues().add("", "").toString();
    }
    
    public static class ExternalPolicys extends ListResult<GbpExternalPolicy> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("external_policies")
        private List<GbpExternalPolicy> ExternalPolicys;

        @Override
        public List<GbpExternalPolicy> value() {
            return ExternalPolicys;
        }
    }
    
    public static class ExternalPolicyConcreteBuilder implements ExternalPolicyBuilder{

        private GbpExternalPolicy extPolicy;
        
        public ExternalPolicyConcreteBuilder(){
            this(new GbpExternalPolicy());
        }
        
        public ExternalPolicyConcreteBuilder(GbpExternalPolicy gbpExternalPolicy) {
            this.extPolicy=gbpExternalPolicy;
        }

        @Override
        public ExternalPolicy build() {
            return extPolicy;
        }

        @Override
        public ExternalPolicyBuilder from(ExternalPolicy in) {
            extPolicy = (GbpExternalPolicy)in;
            return this;
        }
        
    }

}
