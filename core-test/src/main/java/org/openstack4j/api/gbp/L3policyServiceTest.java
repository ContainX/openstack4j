package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.L3Policy;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
/**
 * Test cases for l3 policies on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/l3_policies")
public class L3policyServiceTest extends AbstractTest {

    private static final String L3_POLICIES="/network/gbp/l3_policies.json";
    private static final String L3_POLICY="/network/gbp/l3_policy.json";
    private static final String L3_POLICY_UPDATE="/network/gbp/l3_policy_update.json";
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListl3Policy() throws Exception{
        respondWith(L3_POLICIES);
        List<? extends L3Policy> l3policyList = osv2().gbp().l3Policy().list();
        assertEquals(2, l3policyList.size()); 
        Preconditions.checkNotNull(l3policyList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : L3 Policy from List : "+l3policyList.get(0));
        assertEquals(l3policyList.get(0).getId(), "8a45f76f-888b-4f3b-ad00-88c3b0ba9e6d");
    }
    @Test
    public void testGetl3Policy() throws Exception{
        respondWith(L3_POLICY);
        String id = "8a45f76f-888b-4f3b-ad00-88c3b0ba9e6d";
        L3Policy l3policy = osv2().gbp().l3Policy().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : L3 Policy by ID : "+l3policy);
        assertNotNull(l3policy);
        assertEquals(id, l3policy.getId());
    }
    @Test
    public void testCreatel3Policy() throws Exception{
        respondWith(L3_POLICY);
        L3Policy l3PolicyCreate= Builders.l3Policy().name("default").description("Implicitly created L3 policy").build();
        L3Policy l3Policy = osv2().gbp().l3Policy().create(l3PolicyCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created L3 Policy : "+l3Policy);
        assertEquals("default", l3Policy.getName());
        assertEquals("24", l3Policy.getSubnetPrefixLength());
    }
    @Test
    public void testUpdatel3Policy() throws Exception{
        respondWith(L3_POLICY_UPDATE);
        String id = "8a45f76f-888b-4f3b-ad00-88c3b0ba9e6d";
        L3Policy l3PolicyUpdate= Builders.l3Policy().name("default-update").description("Implicitly created L3 policy-update").build();
        L3Policy l3Policy =osv2().gbp().l3Policy().update(id, l3PolicyUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated L3 Policy : "+l3Policy);
        assertEquals("Implicitly created L3 policy-update", l3Policy.getDescription());

    }
    @Test
    public void testDeletel3Policy() {
        respondWith(200);
        String id = "8a45f76f-888b-4f3b-ad00-88c3b0ba9e6d";
        ActionResponse result = osv2().gbp().l3Policy().delete(id);
        assertTrue(result.isSuccess());
    }


}
