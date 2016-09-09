package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.ExternalPolicy;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.google.common.base.Preconditions;
/**
 * Test cases for External policies on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/external_policies")
public class ExternalPolicyServiceTest extends AbstractTest {

    private static final String EXTERNAL_POLICIES="/network/gbp/external_policies.json";
    private static final String EXTERNAL_POLICY="/network/gbp/external_policy.json";
    private static final String EXTERNAL_POLICY_UPDATE="/network/gbp/external_policy_update.json";
    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void testListExternalPolicy() throws Exception{
        respondWith(EXTERNAL_POLICIES);
        List<? extends ExternalPolicy> externalPolicyList = osv2().gbp().externalPolicy().list();
        assertEquals(2, externalPolicyList.size());
        Preconditions.checkNotNull(externalPolicyList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : External Policyfrom List : "+externalPolicyList.get(0));
        assertEquals(externalPolicyList.get(0).getId(), "6294837a-8f35-4071-b766-3a4ff3d8afbb");
    }
    
    @Test
    public void testGetExternalPolicy() throws Exception{
        respondWith(EXTERNAL_POLICY);
        String id = "8d14b663-c67d-4fa7-b23f-0732cb9aa292";
        ExternalPolicy externalPolicy = osv2().gbp().externalPolicy().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : External Policy by ID : "+externalPolicy);
        assertNotNull(externalPolicy);
        assertEquals(id, externalPolicy.getId());
    }
    
    @Test
    public void testCreateExternalPolicy() throws Exception{
        respondWith(EXTERNAL_POLICY);
        List<String> externalSegmentIds = Lists.newArrayList();
        externalSegmentIds.add("f062e3b9-a668-4265-95d0-239c2061ca12");
        List<String> consumedPolicyRuleSet= Lists.newArrayList();
        consumedPolicyRuleSet.add("1bbc10a8-aeb2-4e53-ab31-a1fed18763f4");
        consumedPolicyRuleSet.add("ec0b5a7d-f963-45de-b4e4-864d59dc8c0b");
        consumedPolicyRuleSet.add("c3207474-19ee-4241-81e5-9e20995f2065");
        consumedPolicyRuleSet.add("f19a90af-0103-429d-8118-3920bf2f27db");
        consumedPolicyRuleSet.add("590f005f-de21-4466-905f-457487cc9a60");
        consumedPolicyRuleSet.add("b8a116d4-d674-438c-9fc6-973f2d6f5019");
        consumedPolicyRuleSet.add("408a5153-efc5-4bea-a3ef-2c838e330254");
        consumedPolicyRuleSet.add("de829eb3-4867-442f-bac7-c59906c610b0");

        List<String> providedPolicyRuleSet= Lists.newArrayList();
        providedPolicyRuleSet.add("1bbc10a8-aeb2-4e53-ab31-a1fed18763f4");
        providedPolicyRuleSet.add("ec0b5a7d-f963-45de-b4e4-864d59dc8c0b");
        providedPolicyRuleSet.add("c3207474-19ee-4241-81e5-9e20995f2065");
        providedPolicyRuleSet.add("f19a90af-0103-429d-8118-3920bf2f27db");
        providedPolicyRuleSet.add("590f005f-de21-4466-905f-457487cc9a60");
        providedPolicyRuleSet.add("b8a116d4-d674-438c-9fc6-973f2d6f5019");
        providedPolicyRuleSet.add("408a5153-efc5-4bea-a3ef-2c838e330254");
        providedPolicyRuleSet.add("de829eb3-4867-442f-bac7-c59906c610b0");
        
        ExternalPolicy externalPolicy = osv2().gbp().externalPolicy().create(Builders.externalPolicy().consumedPolicyRuleSets(consumedPolicyRuleSet).providedPolicyRuleSets(providedPolicyRuleSet).externalSegments(externalSegmentIds ).name("test-ext-policy").description("test-ext-policy-desc").build());
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created External Policy : "+externalPolicy);
        assertEquals("test-ext-policy", externalPolicy.getName());
        assertEquals(8, externalPolicy.getConsumedPolicyRuleSets().size());
        assertEquals(8, externalPolicy.getProvidedPolicyRuleSets().size());
    }
    @Test
    public void testUpdateExternalPolicy() throws Exception{
        respondWith(EXTERNAL_POLICY_UPDATE);
        String id = "8d14b663-c67d-4fa7-b23f-0732cb9aa292";
        ExternalPolicy externalPolicy =osv2().gbp().externalPolicy().update(id, Builders.externalPolicy().name("test-ext-policy-update").description("test-ext-policy-desc-update").build());
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated External Policy : "+externalPolicy);
        assertEquals("test-ext-policy-desc-update", externalPolicy.getDescription());
    }
    @Test
    public void testDeleteExternalPolicy() {
        respondWith(200);
        String id = "8d14b663-c67d-4fa7-b23f-0732cb9aa292";
        ActionResponse result = osv2().gbp().externalPolicy().delete(id);
        assertTrue(result.isSuccess());
    }

}
