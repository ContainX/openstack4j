package org.openstack4j.api.network.qos;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosBandwidthLimitRule;
import org.openstack4j.model.network.qos.NetQosPolicy;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Guoshuai Li
 *
 */
@Test(suiteName = "QosPolicy")
public class NetQosPolicyTests extends AbstractTest {

    private static final String JSON_QOS_POLICY = "/network/qos/policy.json";
    private static final String JSON_QOS_POLICIES = "/network/qos/policies.json";
    private static final String QOS_POLICY_ID = "46ebaec0-0570-43ac-82f6-60d2b03168c4";
    private static final String QOS_POLICY_NAME = "10Mbit";

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void createQosPolicy() throws Exception {
        respondWith(JSON_QOS_POLICY);
        NetQosPolicy qosPolicy = osv3().networking().qos().qosPolicy().create(
                Builders.netQosPolicy().name(QOS_POLICY_NAME).isShared(false).isDefault(false).build());
        server.takeRequest();
        assertEquals(qosPolicy.getId(), QOS_POLICY_ID);
        assertEquals(qosPolicy.getName(), QOS_POLICY_NAME);
        assertEquals(qosPolicy.isDefault(), false);
        assertEquals(qosPolicy.isShared(), false);
    }

    @Test
    public void deleteQosPolicy() throws Exception {
        respondWith(200);
        ActionResponse result = osv3().networking().qos().qosPolicy().delete(QOS_POLICY_ID);
        server.takeRequest();
        assertTrue(result.isSuccess());
    }

    @Test
    public void updateQosPolicy() throws Exception {
        respondWith(JSON_QOS_POLICY);
        NetQosPolicy qosPolicy = osv3().networking().qos().qosPolicy().update(
                QOS_POLICY_ID, Builders.netQosPolicy().name(QOS_POLICY_NAME).isShared(false).isDefault(false).build());
        server.takeRequest();
        assertEquals(qosPolicy.getId(), QOS_POLICY_ID);
        assertEquals(qosPolicy.getName(), QOS_POLICY_NAME);
        assertEquals(qosPolicy.isDefault(), false);
        assertEquals(qosPolicy.isShared(), false);
    }

    @Test
    public void getQosPolicy() throws Exception {
        respondWith(JSON_QOS_POLICY);
        NetQosPolicy qosPolicy = osv3().networking().qos().qosPolicy().get(QOS_POLICY_ID);
        server.takeRequest();
        assertEquals(qosPolicy.getId(), QOS_POLICY_ID);
        assertEquals(qosPolicy.getName(), QOS_POLICY_NAME);
        assertEquals(qosPolicy.isDefault(), false);
        assertEquals(qosPolicy.isShared(), false);
        assertEquals(qosPolicy.getRules().size(), 2);
        NetQosBandwidthLimitRule qosBandwidthLimitRule = (NetQosBandwidthLimitRule) qosPolicy.getRules().get(0);
        assertEquals(qosBandwidthLimitRule.getQosPolicy(), QOS_POLICY_ID);
        assertEquals(qosBandwidthLimitRule.getMaxKbps(), Integer.valueOf(10000));
    }

    @Test
    public void listQosPolicy() throws Exception {
        respondWith(JSON_QOS_POLICIES);
        List<? extends NetQosPolicy> qosPolicys = osv3().networking().qos().qosPolicy().list();
        server.takeRequest();
        assertEquals(qosPolicys.get(0).getId(), QOS_POLICY_ID);
        assertEquals(qosPolicys.get(0).getName(), QOS_POLICY_NAME);
        assertEquals(qosPolicys.get(0).isDefault(), false);
        assertEquals(qosPolicys.get(0).isShared(), false);
    }
}
