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
import com.huawei.openstack4j.model.gbp.L2Policy;
/**
 * Test cases for l2 policies on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/l2_policies")
public class L2policyServiceTest extends AbstractTest {

    private static final String L2_POLICIES="/network/gbp/l2_policies.json";
    private static final String L2_POLICY="/network/gbp/l2_policy.json";
    private static final String L2_POLICY_UPDATE="/network/gbp/l2_policy_update.json";
    
    @Override
    protected Service service() { 
        return Service.NETWORK;
    }
    @Test
    public void testListl2Policy() throws Exception{
        respondWith(L2_POLICIES);
        List<? extends L2Policy> l2policyList = osv2().gbp().l2Policy().list();
        assertEquals(10, l2policyList.size()); 
        Preconditions.checkNotNull(l2policyList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : L2 Policy from List : "+l2policyList.get(0));
        assertEquals(l2policyList.get(0).getId(), "08c1c093-6337-4383-938e-2d9c6cac531a");
    }
    @Test
    public void testGetl2Policy() throws Exception{
        respondWith(L2_POLICY);
        String id = "08c1c093-6337-4383-938e-2d9c6cac531a";
        L2Policy l2policy = osv2().gbp().l2Policy().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : L2 Policy by ID : "+l2policy);
        assertNotNull(l2policy);
        assertEquals(id, l2policy.getId());
    }
    @Test
    public void testCreatel2Policy() throws Exception{
        respondWith(L2_POLICY);
        L2Policy l2PolicyCreate= Builders.l2Policy().name("test-policy-target-group").description("Implicitly created L2 policy").build();
        L2Policy l2Policy = osv2().gbp().l2Policy().create(l2PolicyCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created L2 Policy : "+l2Policy);
        assertEquals("test-policy-target-group", l2Policy.getName());
        assertEquals("f9c1f545-6ea6-4b05-99d5-50f02ed3c640", l2Policy.getNetworkId());
    }
    @Test
    public void testUpdatel2Policy() throws Exception{
        respondWith(L2_POLICY_UPDATE);
        String id = "08c1c093-6337-4383-938e-2d9c6cac531a";
        L2Policy l2PolicyUpdate= Builders.l2Policy().name("test-policy-target-group-update").description("Implicitly created L2 policy-update").build();
        L2Policy l2Policy =osv2().gbp().l2Policy().update(id, l2PolicyUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated L2 Policy : "+l2Policy);
        assertEquals("Implicitly created L2 policy-update", l2Policy.getDescription());

    }
    @Test
    public void testDeletel2Policy() {
        respondWith(200);
        String id = "08c1c093-6337-4383-938e-2d9c6cac531a";
        ActionResponse result = osv2().gbp().l2Policy().delete(id);
        assertTrue(result.isSuccess());
    }


}
