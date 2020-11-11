package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.NetQosPolicy;
import org.openstack4j.openstack.networking.domain.NeutronNetQosPolicy;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for (Neutron) qos policy extension based Services
 *
 * @author bboyHan
 */
@Test(suiteName = "Network/qosPolicy")
public class NetQosPolicyTests extends AbstractTest {
    private static final String QOS_POLICY_JSON = "/network/qos_policy.json";
    private static final String QOS_POLICIES_JSON = "/network/qos_policies.json";


    public void list() throws IOException {
        respondWith(QOS_POLICIES_JSON);
        List<? extends NetQosPolicy> netQosPolicies = osv3().networking().netQosPolicy().list();
        assertEquals(1, netQosPolicies.size());
        assertEquals("10Mbit", netQosPolicies.get(0).getName());
        assertFalse(netQosPolicies.get(0).isDefault());
    }

    public void get() throws IOException{
        respondWith(QOS_POLICY_JSON);
        NetQosPolicy netQosPolicy = osv3().networking().netQosPolicy().get("networkId");
        assertEquals("8d4c70a21fed4aeba121a1a429ba0d04", netQosPolicy.getTenantId());
        assertEquals("10Mbit", netQosPolicy.getName());
    }

    public void delete() {
        respondWith(204);
        ActionResponse response = osv3().networking().netQosPolicy().delete("policyId");
        assertTrue(response.isSuccess());
    }

    public void create() throws IOException {
        respondWith(QOS_POLICY_JSON);
        NetQosPolicy qosPolicy = osv3().networking().netQosPolicy()
                .create(NeutronNetQosPolicy.builder().name("name").build());
        assertEquals("8d4c70a21fed4aeba121a1a429ba0d04", qosPolicy.getTenantId());
        assertFalse(qosPolicy.isShared());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
