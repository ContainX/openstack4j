package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.PolicyTargetGroup;
import org.openstack4j.model.gbp.PolicyTargetGroupCreate;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
/**
 * Test cases for policy target group on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/policy_target_groups")
public class GroupServiceTest extends AbstractTest {

    private static final String POLICY_TARGET_GROUPS="/network/gbp/policy_target_groups.json";
    private static final String POLICY_TARGET_GROUP="/network/gbp/policy_target_group.json";
    private static final String POLICY_TARGET_GROUP_UPDATE="/network/gbp/policy_target_group_update.json";
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListPolicyTargetGroup() throws Exception{
        respondWith(POLICY_TARGET_GROUPS);
        List<? extends PolicyTargetGroup> policytargetGroupList = osv2().gbp().group().list();
        assertEquals(8, policytargetGroupList.size());
        Preconditions.checkNotNull(policytargetGroupList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy target group from List : "+policytargetGroupList.get(0));
        assertEquals(policytargetGroupList.get(0).getId(), "1fb00129-06cf-48e5-8282-d15dbf4be60b");
    }
    @Test
    public void testGetPolicyTargetGroup() throws Exception{
        respondWith(POLICY_TARGET_GROUP);
        String id = "61073812-3994-40c2-96f0-6bff03804e47";
        PolicyTargetGroup policyTargetGroup = osv2().gbp().group().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy target Group by ID : "+policyTargetGroup);
        assertNotNull(policyTargetGroup);
        assertEquals(id, policyTargetGroup.getId());
    }
    @Test
    public void testCreatePolicyTargetGroup() throws Exception{
        respondWith(POLICY_TARGET_GROUP);
        PolicyTargetGroupCreate policyTargetGroupCreate= Builders.policyTargetGroup().name("test-policy-target-group").description("test-policy-target-group-desc").build();
        PolicyTargetGroup policyTargetGroup = osv2().gbp().group().create(policyTargetGroupCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Policy Target group : "+policyTargetGroup);
        assertEquals("test-policy-target-group", policyTargetGroup.getName());
        assertEquals(8, policyTargetGroup.getConsumedPolicyRuleSets().size());
        assertEquals(8, policyTargetGroup.getProvidedPolicyRuleSets().size());
    }
    @Test
    public void testUpdatePolicyTargetGroup() throws Exception{
        respondWith(POLICY_TARGET_GROUP_UPDATE);
        String id = "61073812-3994-40c2-96f0-6bff03804e47";
        PolicyTargetGroupCreate policyTargetGroupCreate= Builders.policyTargetGroup().name("test-policy-target-group-update").description("test-policy-target-group-desc-update").build();
        PolicyTargetGroup policyTargetGroup =osv2().gbp().group().update(id, policyTargetGroupCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Policy Target group : "+policyTargetGroup);
        assertEquals("test-policy-target-group-desc-update", policyTargetGroup.getDescription());

    }
    @Test
    public void testDeletePolicyTargetGroup() {
        respondWith(200);
        String id = "61073812-3994-40c2-96f0-6bff03804e47";
        ActionResponse result = osv2().gbp().group().delete(id);
        assertTrue(result.isSuccess());
    }


}
