package org.openstack4j.api.network.qos;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosDscpMarkingRule;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Guoshuai Li
 *
 */
@Test(suiteName = "QosPolicy")
public class NetQosDscpMarkingRuleTests extends AbstractTest {

    private static final String JSON_QOS_DSCP_MARKING_RULE = "/network/qos/dscp_marking_rule.json";
    private static final String JSON_QOS_DSCP_MARKING_RULES = "/network/qos/dscp_marking_rules.json";
    private static final String QOS_DSCP_MARKING_RULE_ID = "5f126d84-551a-4dcf-bb01-0e9c0df0c794";
    private static final String QOS_POLICY_ID = "46ebaec0-0570-43ac-82f6-60d2b03168c4";
    private static final Integer QOS_DSCP_MARKING_RULE_DSCP = 26;

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void createQosDscpMarkingRule() throws Exception {
        respondWith(JSON_QOS_DSCP_MARKING_RULE);
        NetQosDscpMarkingRule qosDscpMarkingRule = osv3().networking().qos().qosDscpMarkingRule().create(
                QOS_POLICY_ID, Builders.netQosDscpMarkingRule().dscpMark(QOS_DSCP_MARKING_RULE_DSCP).build());
        server.takeRequest();
        assertEquals(qosDscpMarkingRule.getId(), QOS_DSCP_MARKING_RULE_ID);
        assertEquals(qosDscpMarkingRule.getDscpMark(), QOS_DSCP_MARKING_RULE_DSCP);
    }

    @Test
    public void deleteQosDscpMarkingRule() throws Exception {
        respondWith(200);
        ActionResponse result = osv3().networking().qos().qosDscpMarkingRule().delete(QOS_POLICY_ID, QOS_DSCP_MARKING_RULE_ID);
        server.takeRequest();
        assertTrue(result.isSuccess());
    }

    @Test
    public void updateQosDscpMarkingRule() throws Exception {
        respondWith(JSON_QOS_DSCP_MARKING_RULE);
        NetQosDscpMarkingRule qosDscpMarkingRule = osv3().networking().qos().qosDscpMarkingRule().update(
                QOS_POLICY_ID, QOS_DSCP_MARKING_RULE_ID, Builders.netQosDscpMarkingRule().dscpMark(QOS_DSCP_MARKING_RULE_DSCP).build());
        server.takeRequest();
        assertEquals(qosDscpMarkingRule.getId(), QOS_DSCP_MARKING_RULE_ID);
        assertEquals(qosDscpMarkingRule.getDscpMark(), QOS_DSCP_MARKING_RULE_DSCP);
    }

    @Test
    public void getQosDscpMarkingRule() throws Exception {
        respondWith(JSON_QOS_DSCP_MARKING_RULE);
        NetQosDscpMarkingRule qosDscpMarkingRule = osv3().networking().qos().qosDscpMarkingRule().get(QOS_POLICY_ID, QOS_DSCP_MARKING_RULE_ID);
        server.takeRequest();
        assertEquals(qosDscpMarkingRule.getId(), QOS_DSCP_MARKING_RULE_ID);
        assertEquals(qosDscpMarkingRule.getDscpMark(), QOS_DSCP_MARKING_RULE_DSCP);
    }

    @Test
    public void listQosDscpMarkingRule() throws Exception {
        respondWith(JSON_QOS_DSCP_MARKING_RULES);
        List<? extends NetQosDscpMarkingRule> qosDscpMarkingRules = osv3().networking().qos().qosDscpMarkingRule().list(QOS_POLICY_ID);
        server.takeRequest();
        assertEquals(qosDscpMarkingRules.get(0).getId(), QOS_DSCP_MARKING_RULE_ID);
        assertEquals(qosDscpMarkingRules.get(0).getDscpMark(), QOS_DSCP_MARKING_RULE_DSCP);
    }
}
