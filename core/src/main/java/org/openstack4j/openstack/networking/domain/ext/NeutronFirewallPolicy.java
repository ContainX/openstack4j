package org.openstack4j.openstack.networking.domain.ext;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.model.network.ext.FirewallPolicy;
import org.openstack4j.model.network.ext.FirewallRule;
import org.openstack4j.model.network.ext.builder.FirewallPolicyBuilder;
import org.openstack4j.openstack.common.ListResult;
import org.testng.collections.Lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * A Neutron Firewall (FwaaS) : Firewall Policy Entity.
 * 
 * @author Vishvesh Deshmukh
 */
@JsonRootName("firewall_policy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronFirewallPolicy implements FirewallPolicy {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	private String description;
	
	private Boolean shared;
	
	private Boolean audited;
	
	@JsonProperty("firewall_rules")
	private List<String> firewallRules;
	
	private List<NeutronFirewallRule> neutronFirewallRules;
		
	/**
	 * Wrap this FirewallPolicy to a builder
	 * @return FirewallPolicyBuilder
	 */
	@Override
	public FirewallPolicyBuilder toBuilder() {
		return new FirewallPolicyConcreteBuilder(this);
	}
	
	/**
	 * @return FirewallPolicyBuilder
	 */
	public static FirewallPolicyBuilder builder() {
		return new FirewallPolicyConcreteBuilder();
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Boolean isShared() {
		return shared != null && shared;
	}
	
	@Override
	public Boolean isAudited() {
		return audited != null && audited;
	}

	@Override
	public List<String> getFirewallRuleIds() {
		return firewallRules;
	}

	@Override
	public List<? extends FirewallRule> getNeutronFirewallRules() {
        if (neutronFirewallRules == null && (firewallRules != null && firewallRules.size() > 0)) {
        	neutronFirewallRules = Lists.newArrayList();
            for (String ruleId : firewallRules) {
                NeutronFirewallRule rule = (NeutronFirewallRule) Apis.getNetworkingServices().
                		firewalls().firewallrule().get(ruleId);
                neutronFirewallRules.add(rule);
            }
        }
        return neutronFirewallRules;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				.add("id", id).add("name", name).add("shared", shared).add("audited", audited)
				.add("tenantId", tenantId).add("description", description)
				.add("firewallRuleIds", firewallRules).add("neutronFirewallRules", neutronFirewallRules)
				.toString();
	}
	
	public static class FirewallPolicies extends ListResult<NeutronFirewallPolicy> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("firewall_policies")
		List<NeutronFirewallPolicy> firewallPolicies;
		
		@Override
		public List<NeutronFirewallPolicy> value() {
			return firewallPolicies;
		}
		
		@Override
		public String toString() {
			return Objects.toStringHelper(this).omitNullValues()
					.add("firewallPolicies", firewallPolicies).toString();
		}
	}
	
	public static class FirewallPolicyConcreteBuilder implements FirewallPolicyBuilder {
		NeutronFirewallPolicy f;
		
		@Override
		public FirewallPolicy build() {
			return f;
		}
		
		public FirewallPolicyConcreteBuilder() {
			this(new NeutronFirewallPolicy());
		}
		
		public FirewallPolicyConcreteBuilder(NeutronFirewallPolicy f){
			this.f = f;
		}
		
		@Override
		public FirewallPolicyBuilder from(FirewallPolicy in) {
			this.f = (NeutronFirewallPolicy) in;
			return this;
		}

		@Override
		public FirewallPolicyBuilder tenantId(String tenantId) {
			f.tenantId = tenantId;
			return this;
		}

		@Override
		public FirewallPolicyBuilder name(String name) {
			f.name = name;
			return this;
		}

		@Override
		public FirewallPolicyBuilder description(String description) {
			f.description = description;
			return this;
		}

		@Override
		public FirewallPolicyBuilder shared(Boolean shared) {
			f.shared = shared;
			return this;
		}

		@Override
		public FirewallPolicyBuilder audited(Boolean audited) {
			f.audited = audited;
			return this;
		}

		@Override
		public FirewallPolicyBuilder firewallRules(List<String> ruleIdList) {
			f.firewallRules = ruleIdList;
			return this;
		}
	}
}
