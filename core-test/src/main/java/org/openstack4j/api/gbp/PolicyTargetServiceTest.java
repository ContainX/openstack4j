package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.PolicyTarget;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
/**
 * Test cases for policy target on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/policy_targets")
public class PolicyTargetServiceTest extends AbstractTest {

    private static final String POLICY_TARGETS="/network/gbp/policy_targets.json";
    private static final String POLICY_TARGET="/network/gbp/policy_target.json";
    private static final String POLICY_TARGET_UPDATE="/network/gbp/policy_target_update.json";
    
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListPolicyTarget() throws Exception{
        respondWith(POLICY_TARGETS);
        List<? extends PolicyTarget> policytargetList = osv2().gbp().policyTarget().list();
        assertEquals(10, policytargetList.size());
        Preconditions.checkNotNull(policytargetList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy target from List : "+policytargetList.get(0));
        assertEquals(policytargetList.get(0).getId(), "0d65eebe-4efe-456e-aec3-7856e4e839b4");
    }
    @Test
    public void testGetPolicyTarget() throws Exception{
        respondWith(POLICY_TARGET);
        String id = "0d65eebe-4efe-456e-aec3-7856e4e839b4";
        PolicyTarget policyTarget = osv2().gbp().policyTarget().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy target by ID : "+policyTarget);
        assertNotNull(policyTarget);
        assertEquals(id, policyTarget.getId());
    }
    @Test
    public void testCreatePolicyTarget() throws Exception{
        respondWith(POLICY_TARGET);
        PolicyTarget policyTargetCreate= Builders.policyTarget().name("test-policytarget").description("test-policytarget-desc").build();
        PolicyTarget policyTarget = osv2().gbp().policyTarget().create(policyTargetCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Policy Target : "+policyTarget);
        assertEquals("test-policytarget", policyTarget.getName());
        assertEquals("36af8850-3514-4343-8293-9f9faae980d6", policyTarget.getPortId());
        assertEquals("1fb00129-06cf-48e5-8282-d15dbf4be60b", policyTarget.getPolicyTargetGroupId());
    }
    @Test
    public void testUpdatePolicyTarget() throws Exception{
        respondWith(POLICY_TARGET_UPDATE);
        String id = "0d65eebe-4efe-456e-aec3-7856e4e839b4";
        PolicyTarget policyTargetCreate= Builders.policyTarget().name("test-policytarget-update").description("test-policytarget-desc-update").build();
        PolicyTarget policyTarget =osv2().gbp().policyTarget().update(id, policyTargetCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Policy Target : "+policyTarget);
        assertEquals("test-policytarget-desc-update", policyTarget.getDescription());

    }
    @Test
    public void testDeletePolicyTarget() {
        respondWith(200);
        String id = "0d65eebe-4efe-456e-aec3-7856e4e839b4";
        ActionResponse result = osv2().gbp().policyTarget().delete(id);
        assertTrue(result.isSuccess());
    }


}
