/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyRuleSet;
/**
 * Test cases for policy rule set on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/policy_rule_set")
public class PolicyRuleSetServiceTest extends AbstractTest {

    private static final String POLICY_RULE_SETS="/network/gbp/policy_rule_sets.json";
    private static final String POLICY_RULE_SET="/network/gbp/policy_rule_set.json";
    private static final String POLICY_RULE_SET_UPDATE="/network/gbp/policy_rule_set_update.json";
    
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListPolicyRuleSet() throws Exception{
        respondWith(POLICY_RULE_SETS);
        List<? extends PolicyRuleSet> policyRuleSetList = osv2().gbp().policyRuleSet().list();
        assertEquals(10, policyRuleSetList.size()); 
        Preconditions.checkNotNull(policyRuleSetList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Rule Set from List : "+policyRuleSetList.get(0));
        assertEquals(policyRuleSetList.get(0).getId(), "1bbc10a8-aeb2-4e53-ab31-a1fed18763f4");
    }
    @Test
    public void testGetPolicyRuleSet() throws Exception{
        respondWith(POLICY_RULE_SET);
        String id = "1bbc10a8-aeb2-4e53-ab31-a1fed18763f4";
        PolicyRuleSet policyruleSet = osv2().gbp().policyRuleSet().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Rule Set by ID : "+policyruleSet);
        assertNotNull(policyruleSet);
        assertEquals(id, policyruleSet.getId());
    }
    @Test
    public void testCreatePolicyRuleSet() throws Exception{
        respondWith(POLICY_RULE_SET);
        PolicyRuleSet policyRuleSetCreate= Builders.policyRuleSet().name("test-rule-set").description("test-rule-set-desc").build();
        PolicyRuleSet policyRuleSet = osv2().gbp().policyRuleSet().create(policyRuleSetCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Create Policy Rule Set : "+policyRuleSet);
        assertEquals(0, policyRuleSet.getChildPolicyRuleSets().size());
        assertEquals("test-rule-set", policyRuleSet.getName());
   }
    @Test
    public void testUpdatePolicyRuleSet() throws Exception{
        respondWith(POLICY_RULE_SET_UPDATE);
        String id = "1bbc10a8-aeb2-4e53-ab31-a1fed18763f4";
        PolicyRuleSet policyRuleSetUpdate= Builders.policyRuleSet().name("test-rule-set-update").description("test-rule-set-desc-update").build();
        PolicyRuleSet policyRuleSet =osv2().gbp().policyRuleSet().update(id, policyRuleSetUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Upate Policy Rule Set : "+policyRuleSet);
        assertEquals("test-rule-set-desc-update", policyRuleSet.getDescription());

    }
    @Test
    public void testDeletePolicyRuleSet() {
        respondWith(200);
        String id = "1bbc10a8-aeb2-4e53-ab31-a1fed18763f4";
        ActionResponse result = osv2().gbp().policyRuleSet().delete(id);
        assertTrue(result.isSuccess());
    }


}
