package org.openstack4j.openstack.networking.internal.qos;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.qos.*;

/**
 * Neutron QoS Policy Service
 *
 * @author Guoshuai Li
 *
 */
public class QosServiceImpl implements NetQosService {

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosRuleTypeService qosRuleType() {
        return Apis.get(NetQosRuleTypeService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicyService qosPolicy() {
        return Apis.get(NetQosPolicyService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosBandwidthLimitRuleService qosBandwidthLimitRule() {
        return Apis.get(NetQosBandwidthLimitRuleService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosDscpMarkingRuleService qosDscpMarkingRule() {
        return Apis.get(NetQosDscpMarkingRuleService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosMinimumBandwidthRuleService qosMinimumBandwidthRule() {
        return Apis.get(NetQosMinimumBandwidthRuleService.class);
    }
}
