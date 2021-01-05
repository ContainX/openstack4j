package org.openstack4j.openstack.networking.internal.qos;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

import org.openstack4j.api.networking.qos.NetQosDscpMarkingRuleService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosDscpMarkingRule;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosDscpMarkingRule;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosDscpMarkingRule.NeutronQosDscpMarkingRules;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Neutron QoS DSCP Marking Rule Service
 *
 * @author Guoshuai Li
 *
 */
public class QosDscpMarkingRuleServiceImpl extends BaseNetworkingServices implements NetQosDscpMarkingRuleService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosDscpMarkingRule> list(String qosPolicyId) {
        return get(NeutronQosDscpMarkingRules.class, uri("/qos/policies/%s/dscp_marking_rules", qosPolicyId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosDscpMarkingRule get(String qosPolicyId, String ruleId) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return get(NeutronQosDscpMarkingRule.class, uri("/qos/policies/%s/dscp_marking_rules/%s", qosPolicyId, ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String qosPolicyId, String ruleId) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return deleteWithResponse(uri("/qos/policies/%s/dscp_marking_rules/%s", qosPolicyId, ruleId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosDscpMarkingRule create(String qosPolicyId, NetQosDscpMarkingRule qosDscpMarkingRule) {
        checkNotNull(qosPolicyId);
        checkNotNull(qosDscpMarkingRule);
        return post(NeutronQosDscpMarkingRule.class, uri("/qos/policies/%s/dscp_marking_rules", qosPolicyId)).entity(qosDscpMarkingRule).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosDscpMarkingRule update(String qosPolicyId, String ruleId, NetQosDscpMarkingRule qosDscpMarkingRule) {
        checkNotNull(qosPolicyId);
        checkNotNull(ruleId);
        return put(NeutronQosDscpMarkingRule.class, uri("/qos/policies/%s/dscp_marking_rules/%s", qosPolicyId, ruleId)).entity(qosDscpMarkingRule).execute();
    }
}
