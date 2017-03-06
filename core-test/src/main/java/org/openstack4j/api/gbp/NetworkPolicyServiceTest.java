package org.openstack4j.api.gbp;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.NetworkServicePolicy;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for Network service on GBP
 *
 * @author sumit gandhi
 */
@Test(suiteName="grouppolicy/network_service_policies")
public class NetworkPolicyServiceTest extends AbstractTest {

    private static final String NETWORK_SERVICE_POLICIES="/network/gbp/network_service_policies.json";
    private static final String NETWORK_SERVICE_POLICY="/network/gbp/network_service_policy.json";
    private static final String NETWORK_SERVICE_POLICY_UPDATE="/network/gbp/network_service_policy_update.json";

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void testListNetworkServicePolicy() throws Exception{
        respondWith(NETWORK_SERVICE_POLICIES);
        List<? extends NetworkServicePolicy> gbpServicePolicies = osv2().gbp().networkPolicyService().list();
        assertEquals(2, gbpServicePolicies.size());
        Preconditions.checkNotNull(gbpServicePolicies.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Nat Pool from List : "+gbpServicePolicies.get(0));
        assertEquals(gbpServicePolicies.get(0).getId(), "d98e3cd5-3eb4-41ba-9069-6f5867ceb162");
        assertEquals(gbpServicePolicies.get(1).getId(), "e252a688-2b09-4aab-ae4a-ad57fd9154f2");
    }

    @Test
    public void testGetNetworkServicePolicy() throws Exception{
        respondWith(NETWORK_SERVICE_POLICY);
        String id = "d98e3cd5-3eb4-41ba-9069-6f5867ceb162";
        NetworkServicePolicy gbpServicePolicy = osv2().gbp().networkPolicyService().get(id);
        Preconditions.checkNotNull(gbpServicePolicy);
        assertEquals(gbpServicePolicy.getId(), "d98e3cd5-3eb4-41ba-9069-6f5867ceb162");
    }

    @Test
    public void testCreateNetworkServicePolicy() throws Exception{
        respondWith(NETWORK_SERVICE_POLICY);
        NetworkServicePolicy gbpServicePolicy= Builders.networkServicePolicy().name("Test2").build();
        NetworkServicePolicy gbpServicePolicyOut = osv2().gbp().networkPolicyService().create(gbpServicePolicy);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Network Service Policy : "+gbpServicePolicyOut);
        assertEquals("Test2", gbpServicePolicyOut.getName());
    }

    @Test
    public void testDeleteNetworkServicePolicy() {
        respondWith(200);
        String id = "d98e3cd5-3eb4-41ba-9069-6f5867ceb162";
        ActionResponse result = osv2().gbp().networkPolicyService().delete(id);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testUpdateNatPool() throws Exception{
        respondWith(NETWORK_SERVICE_POLICY_UPDATE);
        String id = "d98e3cd5-3eb4-41ba-9069-6f5867ceb162";
        NetworkServicePolicy gbpServicePolicyUpdate = Builders.networkServicePolicy().name("Test2").build();
        NetworkServicePolicy gbpNetworkServicePolicy =osv2().gbp().networkPolicyService().update(id, gbpServicePolicyUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Network Service Policy : "+gbpNetworkServicePolicy);
        assertEquals("TestUpdate", gbpNetworkServicePolicy.getName());
    }

}
