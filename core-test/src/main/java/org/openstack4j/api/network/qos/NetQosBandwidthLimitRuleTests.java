package org.openstack4j.api.network.qos;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosBandwidthLimitRule;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Guoshuai Li
 *
 */
@Test(suiteName = "QosPolicy")
public class NetQosBandwidthLimitRuleTests extends AbstractTest {

    private static final String JSON_QOS_BANDWIDTH_LIMIT_RULE = "/network/qos/bandwidth_limit_rule.json";
    private static final String JSON_QOS_BANDWIDTH_LIMIT_RULES = "/network/qos/bandwidth_limit_rules.json";
    private static final String QOS_BANDWIDTH_LIMIT_RULE_ID = "5f126d84-551a-4dcf-bb01-0e9c0df0c793";
    private static final String QOS_POLICY_ID = "46ebaec0-0570-43ac-82f6-60d2b03168c4";
    private static final String QOS_BANDWIDTH_LIMIT_RULE_DIRECTION = "egress";
    private static final Integer QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS = 10000;

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void createQosBandwidthLimitRule() throws Exception {
        respondWith(JSON_QOS_BANDWIDTH_LIMIT_RULE);
        NetQosBandwidthLimitRule qosBandwidthLimitRule = osv3().networking().qos().qosBandwidthLimitRule().create(
                QOS_POLICY_ID, Builders.netQosBandwidthLimitRule().direction(QOS_BANDWIDTH_LIMIT_RULE_DIRECTION).maxKbps(QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS).build());
        server.takeRequest();
        assertEquals(qosBandwidthLimitRule.getId(), QOS_BANDWIDTH_LIMIT_RULE_ID);
        assertEquals(qosBandwidthLimitRule.getDirection(), QOS_BANDWIDTH_LIMIT_RULE_DIRECTION);
        assertEquals(qosBandwidthLimitRule.getMaxKbps(), QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS);
    }

    @Test
    public void deleteQosBandwidthLimitRule() throws Exception {
        respondWith(200);
        ActionResponse result = osv3().networking().qos().qosBandwidthLimitRule().delete(QOS_POLICY_ID, QOS_BANDWIDTH_LIMIT_RULE_ID);
        server.takeRequest();
        assertTrue(result.isSuccess());
    }

    @Test
    public void updateQosBandwidthLimitRule() throws Exception {
        respondWith(JSON_QOS_BANDWIDTH_LIMIT_RULE);
        NetQosBandwidthLimitRule qosBandwidthLimitRule = osv3().networking().qos().qosBandwidthLimitRule().update(
                QOS_POLICY_ID, QOS_BANDWIDTH_LIMIT_RULE_ID, Builders.netQosBandwidthLimitRule().direction(QOS_BANDWIDTH_LIMIT_RULE_DIRECTION).maxKbps(QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS).build());
        server.takeRequest();
        assertEquals(qosBandwidthLimitRule.getId(), QOS_BANDWIDTH_LIMIT_RULE_ID);
        assertEquals(qosBandwidthLimitRule.getDirection(), QOS_BANDWIDTH_LIMIT_RULE_DIRECTION);
        assertEquals(qosBandwidthLimitRule.getMaxKbps(), QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS);
    }

    @Test
    public void getQosBandwidthLimitRule() throws Exception {
        respondWith(JSON_QOS_BANDWIDTH_LIMIT_RULE);
        NetQosBandwidthLimitRule qosBandwidthLimitRule = osv3().networking().qos().qosBandwidthLimitRule().get(QOS_POLICY_ID, QOS_BANDWIDTH_LIMIT_RULE_ID);
        server.takeRequest();
        assertEquals(qosBandwidthLimitRule.getId(), QOS_BANDWIDTH_LIMIT_RULE_ID);
        assertEquals(qosBandwidthLimitRule.getDirection(), QOS_BANDWIDTH_LIMIT_RULE_DIRECTION);
        assertEquals(qosBandwidthLimitRule.getMaxKbps(), QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS);
    }

    @Test
    public void listQosBandwidthLimitRule() throws Exception {
        respondWith(JSON_QOS_BANDWIDTH_LIMIT_RULES);
        List<? extends NetQosBandwidthLimitRule> qosBandwidthLimitRules = osv3().networking().qos().qosBandwidthLimitRule().list(QOS_POLICY_ID);
        server.takeRequest();
        assertEquals(qosBandwidthLimitRules.get(0).getId(), QOS_BANDWIDTH_LIMIT_RULE_ID);
        assertEquals(qosBandwidthLimitRules.get(0).getDirection(), QOS_BANDWIDTH_LIMIT_RULE_DIRECTION);
        assertEquals(qosBandwidthLimitRules.get(0).getMaxKbps(), QOS_BANDWIDTH_LIMIT_RULE_MAXKBPS);
    }
}
