package org.openstack4j.openstack.gbp.domain;

import java.util.List;

import org.openstack4j.model.gbp.PolicyAction;
import org.openstack4j.model.gbp.builder.PolicyActionBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("policy_action")
public class GbpPolicyAction implements PolicyAction {
    private static final long serialVersionUID = 1L;
    private String name;
    @JsonProperty("tenant_id")
    private String tenantId;
    private String id;
    private String description;
    private boolean shared;
    @JsonProperty("action_type")
    private String actionType;
    @JsonProperty("action_value")
    private String actionValue;
    
    
    @Override
    public PolicyActionBuilder toBuilder() {
        return new PolicyActionConcreteBuilder(this);
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

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }
    
    public static class PolicyActions extends ListResult<GbpPolicyAction>{
        private static final long serialVersionUID = 1L;
        @JsonProperty("policy_actions")
        private List<GbpPolicyAction> policyActions;
        
        @Override
        protected List<GbpPolicyAction> value() {
            return policyActions;
        }
        
    }
    
    public static class PolicyActionConcreteBuilder implements PolicyActionBuilder{

        private GbpPolicyAction policyAction;
        
        public PolicyActionConcreteBuilder(GbpPolicyAction gbpPolicyAction) {
            this.policyAction=gbpPolicyAction;
        }

        @Override
        public PolicyAction build() {
            return policyAction;
        }

        @Override
        public PolicyActionBuilder from(PolicyAction in) {
            this.policyAction=(GbpPolicyAction) in;
            return this;
        }
        
    }
    

}
