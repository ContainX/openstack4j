package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.PolicyRule;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
/**
 * Test cases for policy rule on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/policy_rules")
public class PolicyRuleServiceTest extends AbstractTest {

    private static final String POLICY_RULES="/network/gbp/policy_rules.json";
    private static final String POLICY_RULE="/network/gbp/policy_rule.json";
    private static final String POLICY_RULE_UPDATE="/network/gbp/policy_rule_update.json";
    
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListPolicyRule() throws Exception{
        respondWith(POLICY_RULES);
        List<? extends PolicyRule> policyRuleList = osv2().gbp().policyRule().list();
        assertEquals(10, policyRuleList.size()); 
        Preconditions.checkNotNull(policyRuleList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Rule from List : "+policyRuleList.get(0));
        assertEquals(policyRuleList.get(0).getId(), "059909d1-7f20-40cf-a78a-27c340a5aaac");
    }
    @Test
    public void testGetPolicyRule() throws Exception{
        respondWith(POLICY_RULE);
        String id = "059909d1-7f20-40cf-a78a-27c340a5aaac";
        PolicyRule policyrule = osv2().gbp().policyRule().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Rule by ID : "+policyrule);
        assertNotNull(policyrule);
        assertEquals(id, policyrule.getId());
    }
    @Test
    public void testCreatePolicyRule() throws Exception{
        respondWith(POLICY_RULE);
        PolicyRule policyRuleCreate= Builders.policyRule().name("icmp-rule").build();
        PolicyRule policyRule = osv2().gbp().policyRule().create(policyRuleCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Create Policy Rule : "+policyRule);
        assertEquals("36e41adb-0b9b-4a11-abd5-66e5386139d4", policyRule.getPolicyClassifierId());
        assertEquals("icmp-rule", policyRule.getName());
    }
    @Test
    public void testUpdatePolicyRule() throws Exception{
        respondWith(POLICY_RULE_UPDATE);
        String id = "059909d1-7f20-40cf-a78a-27c340a5aaac";
        PolicyRule policyRuleUpdate= Builders.policyRule().name("icmp-rule-update").description("icmp-rule-desc-update").build();
        PolicyRule policyRule =osv2().gbp().policyRule().update(id, policyRuleUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Upate Policy Rule : "+policyRule);
        assertEquals("icmp-rule-desc-update", policyRule.getDescription());

    }
    @Test
    public void testDeletePolicyRule() {
        respondWith(200);
        String id = "059909d1-7f20-40cf-a78a-27c340a5aaac";
        ActionResponse result = osv2().gbp().policyRule().delete(id);
        assertTrue(result.isSuccess());
    }


}
