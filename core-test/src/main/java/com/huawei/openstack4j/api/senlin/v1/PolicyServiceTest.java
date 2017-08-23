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
package com.huawei.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.Policy;
import com.huawei.openstack4j.model.senlin.PolicyCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinPolicyCreate;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for policy on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/policy")
public class PolicyServiceTest extends AbstractTest {

    private static final String POLICYS="/senlin/v1/policies.json";
    private static final String POLICY="/senlin/v1/policy.json";
    
    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListPolicy() throws Exception{
        respondWith(POLICYS);
        List<? extends Policy> policyList = osv3().senlin().policy().list();
        assertEquals(9, policyList.size());
        Preconditions.checkNotNull(policyList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy from List : "+policyList.get(0));
        assertEquals(policyList.get(0).getId(), "be24729e-c60c-4f06-9ba4-2d3872c23199");
    }
    @Test
    public void testGetPolicy() throws Exception{
        respondWith(POLICY);
        String id = "3ad6a92c-f969-4d69-8a83-0301f319fc78";
        Policy policy = osv3().senlin().policy().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy by ID : "+ policy);
        assertNotNull(policy);
        assertEquals(id, policy.getId());
    }
    @Test
    public void testCreatePolicy() throws Exception{
        respondWith(POLICY);
        String policyName = "lion-policy123";
        PolicyCreate newPolicy = new SenlinPolicyCreate();
        newPolicy.toBuilder()
                .name(policyName);
        Policy policy = osv3().senlin().policy().create(newPolicy);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Policy : " + policy);
        assertEquals(policyName, policy.getName());
    }
    @Test
    public void testUpdatePolicy() throws Exception{
        respondWith(POLICY);
        String id = "3ad6a92c-f969-4d69-8a83-0301f319fc78";
        String policyName = "lion-policy123";
        PolicyCreate newPolicy = new SenlinPolicyCreate();
        newPolicy.toBuilder()
                .name(policyName);
        Policy policy =osv3().senlin().policy().update(id, newPolicy);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Policy : " + policy);
        assertEquals(policyName, policy.getName());

    }
    @Test
    public void testDeletePolicy() {
        respondWith(200);
        String id = "3ad6a92c-f969-4d69-8a83-0301f319fc78";
        ActionResponse result = osv3().senlin().policy().delete(id);
        assertTrue(result.isSuccess());
    }

    

}
