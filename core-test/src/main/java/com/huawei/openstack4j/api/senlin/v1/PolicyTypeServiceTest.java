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
import com.huawei.openstack4j.model.senlin.PolicyType;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for policyType on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/policyType")
public class PolicyTypeServiceTest extends AbstractTest {

    private static final String POLICYTYPES="/senlin/v1/policy_types.json";
    private static final String POLICYTYPE="/senlin/v1/policy_type.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListPolicyType() throws Exception{
        respondWith(POLICYTYPES);
        List<? extends PolicyType> policyTypeList = osv3().senlin().policyType().list();
        assertEquals(6, policyTypeList.size());
        Preconditions.checkNotNull(policyTypeList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : PolicyType from List : "+ policyTypeList.get(0));
        assertEquals(policyTypeList.get(0).getName(), "ScalingPolicy");
    }
    @Test
    public void testGetPolicyType() throws Exception{
        respondWith(POLICYTYPE);
        PolicyType policyType = osv3().senlin().policyType().get("senlin.policy.deletion");
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : PolicyType by name : "+ policyType);
        assertNotNull(policyType);
        assertEquals("senlin.policy.deletion", policyType.getName());
    }

}
