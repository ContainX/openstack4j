package org.openstack4j.openstack.networking.internal.qos;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

import org.openstack4j.api.networking.qos.NetQosBandwidthLimitRuleService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosBandwidthLimitRule;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosBandwidthLimitRule;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosBandwidthLimitRule.NeutronQosBandwidthLimitRules;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Neutron QoS Bandwidth Limit Rule Service
 *
 * @author Guoshuai Li
 *
 */
public class QosBandwidthLimitRuleServiceImpl extends BaseNetworkingServices implements NetQosBandwidthLimitRuleService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosBandwidthLimitRule> list(String qosPolicyId) {
        return get(NeutronQosBandwidthLimitRules.class, uri("/qos/policies/%s/bandwidth_limit_rules", qosPolicyId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosBandwidthLimitRule get(String qosPolicyId, String ruleId) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return get(NeutronQosBandwidthLimitRule.class, uri("/qos/policies/%s/bandwidth_limit_rules/%s", qosPolicyId, ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String qosPolicyId, String ruleId) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return deleteWithResponse(uri("/qos/policies/%s/bandwidth_limit_rules/%s", qosPolicyId, ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosBandwidthLimitRule create(String qosPolicyId, NetQosBandwidthLimitRule qosBandwidthLimitRule) {
        checkNotNull(qosPolicyId);
        checkNotNull(qosBandwidthLimitRule);
        return post(NeutronQosBandwidthLimitRule.class, uri("/qos/policies/%s/bandwidth_limit_rules", qosPolicyId)).entity(qosBandwidthLimitRule).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosBandwidthLimitRule update(String qosPolicyId, String ruleId, NetQosBandwidthLimitRule qosBandwidthLimitRule) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return put(NeutronQosBandwidthLimitRule.class, uri("/qos/policies/%s/bandwidth_limit_rules/%s", qosPolicyId, ruleId)).entity(qosBandwidthLimitRule).execute();
    }
}
