package org.openstack4j.openstack.networking.internal.qos;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

import org.openstack4j.api.networking.qos.NetQosMinimumBandwidthRuleService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosMinimumBandwidthRule;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosMinimumBandwidthRule;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosMinimumBandwidthRule.NeutronQosMinimumBandwidthRules;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Neutron QoS Minimum Bandwidth Rule Service
 *
 * @author Guoshuai Li
 *
 */
public class QosMinimumBandwidthRuleServiceImpl extends BaseNetworkingServices implements NetQosMinimumBandwidthRuleService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosMinimumBandwidthRule> list(String qosPolicyId) {
        return get(NeutronQosMinimumBandwidthRules.class, uri("/qos/policies/%s/minimum_bandwidth_rules", qosPolicyId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosMinimumBandwidthRule get(String qosPolicyId, String ruleId) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return get(NeutronQosMinimumBandwidthRule.class, uri("/qos/policies/%s/minimum_bandwidth_rules/%s", qosPolicyId, ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String qosPolicyId, String ruleId) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return deleteWithResponse(uri("/qos/policies/%s/minimum_bandwidth_rules/%s", qosPolicyId, ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosMinimumBandwidthRule create(String qosPolicyId, NetQosMinimumBandwidthRule qosMinimumBandwidthRule) {
        checkNotNull(qosPolicyId);
        checkNotNull(qosMinimumBandwidthRule);
        return post(NeutronQosMinimumBandwidthRule.class, uri("/qos/policies/%s/minimum_bandwidth_rules", qosPolicyId)).entity(qosMinimumBandwidthRule).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosMinimumBandwidthRule update(String qosPolicyId, String ruleId, NetQosMinimumBandwidthRule qosMinimumBandwidthRule) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return put(NeutronQosMinimumBandwidthRule.class, uri("/qos/policies/%s/minimum_bandwidth_rules/%s", qosPolicyId, ruleId)).entity(qosMinimumBandwidthRule).execute();
    }
}
