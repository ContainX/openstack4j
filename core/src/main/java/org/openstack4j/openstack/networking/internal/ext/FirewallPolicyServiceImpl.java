package org.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.networking.ext.FirewallPolicyService;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.propagation.PropagateOnStatus;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.ext.FirewallPolicy;
import org.openstack4j.model.network.ext.FirewallPolicyUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicy;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicy.FirewallPolicies;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) FwaaS FirewallPolicy Extension API
 * 
 * @author Vishvesh Deshmukh
 */
public class FirewallPolicyServiceImpl extends BaseNetworkingServices implements FirewallPolicyService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FirewallPolicy> list() {
		return get(FirewallPolicies.class, uri("/fw/firewall_policies")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FirewallPolicy> list(Map<String, String> filteringParams) {
		Invocation<FirewallPolicies> req = get(FirewallPolicies.class, uri("/fw/firewall_policies"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy get(String firewallPolicyId) {
		checkNotNull(firewallPolicyId);
		return get(NeutronFirewallPolicy.class, uri("/fw/firewall_policies/%s", firewallPolicyId)).execute();	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String firewallPolicyId) {
		checkNotNull(firewallPolicyId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri("/fw/firewall_policies/%s", firewallPolicyId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy create(FirewallPolicy firewallPolicy) {
		return post(NeutronFirewallPolicy.class, uri("/fw/firewall_policies")).entity(firewallPolicy)
				.execute(ExecutionOptions.<NeutronFirewallPolicy>create(PropagateOnStatus.on(404)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FirewallPolicy update(String firewallPolicyId, FirewallPolicyUpdate firewallPolicyUpdate) {
		checkNotNull(firewallPolicyId);
        checkNotNull(firewallPolicyUpdate);
        return put(NeutronFirewallPolicy.class, uri("/fw/firewall_policies/%s", firewallPolicyId)).entity(firewallPolicyUpdate).execute();
	}

}
