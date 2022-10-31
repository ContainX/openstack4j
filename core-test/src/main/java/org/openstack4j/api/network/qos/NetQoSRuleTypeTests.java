package org.openstack4j.api.network.qos;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.qos.NetQosRuleDrivers;
import org.openstack4j.model.network.qos.NetQosRuleSupportedChoices;
import org.openstack4j.model.network.qos.NetQosRuleType;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 *
 * @author Guoshuai Li
 *
 */
public class NetQoSRuleTypeTests extends AbstractTest {
    private static final String JSON_QOS_RULE_TYPES = "/network/qos/rule_types.json";
    private static final String JSON_QOS_RULE_DRIVERS = "/network/qos/rule_drivers.json";
    private static final String TYPE_BANDWIDTH_LIMIT = "bandwidth_limit";
    private static final String TYPE_DSCP_MARKING = "dscp_marking";
    private static final String TYPE_MINIMUM_BANDWIDTH = "minimum_bandwidth";
    private static final String DRIVER_OPENVSWITCH = "openvswitch";

    @Override
    protected AbstractTest.Service service() {
        return AbstractTest.Service.NETWORK;
    }

    @Test
    public void getQosRuleTypes() throws Exception {
        respondWith(JSON_QOS_RULE_TYPES);
        List<? extends NetQosRuleType> qosRuleTypes = osv3().networking().qos().qosRuleType().listRuleTypes();
        server.takeRequest();
        assertEquals(qosRuleTypes.get(0).getType(), TYPE_BANDWIDTH_LIMIT);
        assertEquals(qosRuleTypes.get(1).getType(), TYPE_DSCP_MARKING);
        assertEquals(qosRuleTypes.get(2).getType(), TYPE_MINIMUM_BANDWIDTH);
    }

    @Test
    public void getQosRuleDrivers() throws Exception {
        respondWith(JSON_QOS_RULE_DRIVERS);
        NetQosRuleDrivers qosRuleDrivers = osv3().networking().qos().qosRuleType().getRuleTypeDrivers(TYPE_BANDWIDTH_LIMIT);
        server.takeRequest();
        assertEquals(qosRuleDrivers.getType(), TYPE_BANDWIDTH_LIMIT);
        assertEquals(qosRuleDrivers.getDrivers().get(0).getName(), DRIVER_OPENVSWITCH);

        NetQosRuleSupportedChoices choices = (NetQosRuleSupportedChoices) qosRuleDrivers.getDrivers().get(0).getSupportedParameters().get(1);
        assertEquals(choices.getParameterName(), "direction");
        assertEquals(choices.getParameterType(), "choices");
        assertEquals(choices.getParameterValues().get(0), "ingress");
    }
}
