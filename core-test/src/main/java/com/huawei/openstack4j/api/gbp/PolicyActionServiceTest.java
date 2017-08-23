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
import com.huawei.openstack4j.model.gbp.PolicyAction;
import com.huawei.openstack4j.model.gbp.PolicyActionUpdate;
import com.huawei.openstack4j.model.gbp.PolicyAction.PolicyActionProtocol;
/**
 * Test cases for policy action on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/policy_actions")
public class PolicyActionServiceTest extends AbstractTest {

    private static final String POLICY_ACTIONS="/network/gbp/policy_actions.json";
    private static final String POLICY_ACTION="/network/gbp/policy_action.json";
    private static final String POLICY_ACTION_UPDATE="/network/gbp/policy_action_update.json";
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListPolicyAction() throws Exception{
        respondWith(POLICY_ACTIONS);
        List<? extends PolicyAction> policyActionList = osv2().gbp().policyAction().list();
        assertEquals(2, policyActionList.size()); 
        Preconditions.checkNotNull(policyActionList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Action from List : "+policyActionList.get(0));
        assertEquals(policyActionList.get(0).getId(), "b25bbad7-224b-4810-ae1c-7d10fb4468b5");
   }
    @Test
    public void testGetPolicyAction() throws Exception{
        respondWith(POLICY_ACTION);
        String id = "b25bbad7-224b-4810-ae1c-7d10fb4468b5";
        PolicyAction policyAction = osv2().gbp().policyAction().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Action by ID : "+policyAction);
        assertNotNull(policyAction);
        assertEquals(id, policyAction.getId());
    }
    @Test
    public void testCreatePolicyAction() throws Exception{
        respondWith(POLICY_ACTION);
        PolicyAction policyActionCreate= Builders.policyAction().name("allow").actionType(PolicyActionProtocol.ALLOW).build();
        PolicyAction policyAction = osv2().gbp().policyAction().create(policyActionCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Create Policy Action : "+policyAction);
        assertEquals(PolicyActionProtocol.ALLOW.value(), policyAction.getActionType().value());
        assertEquals("allow", policyAction.getName());
    }
    @Test
    public void testUpdatePolicyAction() throws Exception{
        respondWith(POLICY_ACTION_UPDATE);
        String id = "b25bbad7-224b-4810-ae1c-7d10fb4468b5";
        PolicyActionUpdate policyActionUpdate= Builders.policyActionUpdate().name("redirect").build();
        PolicyAction policyAction =osv2().gbp().policyAction().update(id, policyActionUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Upate Policy Action : "+policyAction);
        assertEquals("redirect", policyAction.getName());

    }
    @Test
    public void testDeletePolicyAction() {
        respondWith(200);
        String id = "b25bbad7-224b-4810-ae1c-7d10fb4468b5";
        ActionResponse result = osv2().gbp().policyAction().delete(id);
        assertTrue(result.isSuccess());
    }


}
