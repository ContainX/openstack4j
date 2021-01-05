package org.openstack4j.openstack.networking.internal.qos;

import java.util.List;

import org.openstack4j.api.networking.qos.NetQosRuleTypeService;
import org.openstack4j.model.network.qos.NetQosRuleDrivers;
import org.openstack4j.model.network.qos.NetQosRuleType;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosRuleDrivers;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosRuleType.NeutronQosRuleTypes;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Neutron QoS Rule Type Service
 *
 * @author Guoshuai Li
 *
 */
public class NetQosRuleTypeServiceImpl extends BaseNetworkingServices implements NetQosRuleTypeService {
    @Override
    public List<? extends NetQosRuleType> listRuleTypes() {
        return get(NeutronQosRuleTypes.class, uri("qos/rule-types")).execute().getList();
    }

    @Override
    public NetQosRuleDrivers getRuleTypeDrivers(String qosRuleType) {
        return get(NeutronQosRuleDrivers.class, uri("qos/rule-types/%s", qosRuleType)).execute();
    }
}
