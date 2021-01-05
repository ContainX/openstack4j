package org.openstack4j.api.network.qos;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosMinimumBandwidthRule;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Guoshuai Li
 *
 */
@Test(suiteName = "QosPolicy")
public class NetQosMinimumBandwidthRuleTests extends AbstractTest {

    private static final String JSON_QOS_MINIMUM_BANDWIDTH_RULE = "/network/qos/minimum_bandwidth_rule.json";
    private static final String JSON_QOS_MINIMUM_BANDWIDTH_RULES = "/network/qos/minimum_bandwidth_rules.json";
    private static final String QOS_MINIMUM_BANDWIDTH_RULE_ID = "1eddf7af-0b4c-42c5-8ae1-390b32f1de08";
    private static final String QOS_POLICY_ID = "46ebaec0-0570-43ac-82f6-60d2b03168c4";
    private static final String QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION = "egress";
    private static final Integer QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS = 10000;

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void createQosMinimumBandwidthRule() throws Exception {
        respondWith(JSON_QOS_MINIMUM_BANDWIDTH_RULE);
        NetQosMinimumBandwidthRule qosMinimumBandwidthRule = osv3().networking().qos().qosMinimumBandwidthRule().create(
                QOS_POLICY_ID, Builders.netQosMinimumBandwidthRule().direction(QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION).minKbps(QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS).build());
        server.takeRequest();
        assertEquals(qosMinimumBandwidthRule.getId(), QOS_MINIMUM_BANDWIDTH_RULE_ID);
        assertEquals(qosMinimumBandwidthRule.getDirection(), QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION);
        assertEquals(qosMinimumBandwidthRule.getMinKbps(), QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS);
    }

    @Test
    public void deleteQosMinimumBandwidthRule() throws Exception {
        respondWith(200);
        ActionResponse result = osv3().networking().qos().qosMinimumBandwidthRule().delete(QOS_POLICY_ID, QOS_MINIMUM_BANDWIDTH_RULE_ID);
        server.takeRequest();
        assertTrue(result.isSuccess());
    }

    @Test
    public void updateQosMinimumBandwidthRule() throws Exception {
        respondWith(JSON_QOS_MINIMUM_BANDWIDTH_RULE);
        NetQosMinimumBandwidthRule qosMinimumBandwidthRule = osv3().networking().qos().qosMinimumBandwidthRule().update(
                QOS_POLICY_ID, QOS_MINIMUM_BANDWIDTH_RULE_ID, Builders.netQosMinimumBandwidthRule().direction(QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION).minKbps(QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS).build());
        server.takeRequest();
        assertEquals(qosMinimumBandwidthRule.getId(), QOS_MINIMUM_BANDWIDTH_RULE_ID);
        assertEquals(qosMinimumBandwidthRule.getDirection(), QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION);
        assertEquals(qosMinimumBandwidthRule.getMinKbps(), QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS);
    }

    @Test
    public void getQosMinimumBandwidthRule() throws Exception {
        respondWith(JSON_QOS_MINIMUM_BANDWIDTH_RULE);
        NetQosMinimumBandwidthRule qosMinimumBandwidthRule = osv3().networking().qos().qosMinimumBandwidthRule().get(QOS_POLICY_ID, QOS_MINIMUM_BANDWIDTH_RULE_ID);
        server.takeRequest();
        assertEquals(qosMinimumBandwidthRule.getId(), QOS_MINIMUM_BANDWIDTH_RULE_ID);
        assertEquals(qosMinimumBandwidthRule.getDirection(), QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION);
        assertEquals(qosMinimumBandwidthRule.getMinKbps(), QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS);
    }

    @Test
    public void listQosMinimumBandwidthRule() throws Exception {
        respondWith(JSON_QOS_MINIMUM_BANDWIDTH_RULES);
        List<? extends NetQosMinimumBandwidthRule> qosMinimumBandwidthRules = osv3().networking().qos().qosMinimumBandwidthRule().list(QOS_POLICY_ID);
        server.takeRequest();
        assertEquals(qosMinimumBandwidthRules.get(0).getId(), QOS_MINIMUM_BANDWIDTH_RULE_ID);
        assertEquals(qosMinimumBandwidthRules.get(0).getDirection(), QOS_MINIMUM_BANDWIDTH_RULE_DIRECTION);
        assertEquals(qosMinimumBandwidthRules.get(0).getMinKbps(), QOS_MINIMUM_BANDWIDTH_RULE_MINKBPS);
    }
}
