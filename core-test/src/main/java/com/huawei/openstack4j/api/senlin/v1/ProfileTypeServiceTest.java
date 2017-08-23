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
import com.huawei.openstack4j.model.senlin.ProfileType;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for profileType on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/profileType")
public class ProfileTypeServiceTest extends AbstractTest {

    private static final String PROFILETYPES="/senlin/v1/profile_types.json";
    private static final String PROFILETYPE="/senlin/v1/profile_type.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListProfileType() throws Exception{
        respondWith(PROFILETYPES);
        List<? extends ProfileType> profileTypeList = osv3().senlin().profileType().list();
        assertEquals(3, profileTypeList.size());
        Preconditions.checkNotNull(profileTypeList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : ProfileType from List : "+ profileTypeList.get(0));
        assertEquals(profileTypeList.get(0).getName(), "os.heat.stack");
    }
    @Test
    public void testGetProfileType() throws Exception{
        respondWith(PROFILETYPE);
        ProfileType profileType = osv3().senlin().profileType().get("os.heat.stack");
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : ProfileType by name : "+ profileType);
        assertNotNull(profileType);
        assertEquals("os.heat.stack", profileType.getName());
    }

}
