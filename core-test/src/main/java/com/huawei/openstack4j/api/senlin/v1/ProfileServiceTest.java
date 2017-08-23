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
import com.huawei.openstack4j.model.senlin.Profile;
import com.huawei.openstack4j.model.senlin.ProfileCreate;
import com.huawei.openstack4j.openstack.senlin.domain.SenlinProfileCreate;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for profile on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/profile")
public class ProfileServiceTest extends AbstractTest {

    private static final String PROFILES="/senlin/v1/profiles.json";
    private static final String PROFILE="/senlin/v1/profile.json";
    private static final String ID="dc89e919-91f5-4733-81b0-91257c24bba1";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListProfile() throws Exception{
        respondWith(PROFILES);
        List<? extends Profile> profileList = osv3().senlin().profile().list();
        assertEquals(4, profileList.size());
        Preconditions.checkNotNull(profileList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Profile from List : "+profileList.get(0));
        assertEquals(profileList.get(0).getId(), "ddf94acb-492a-41cb-a278-9ba7fbb31bb7");
    }
    @Test
    public void testGetProfile() throws Exception{
        respondWith(PROFILE);
        Profile profile = osv3().senlin().profile().get(ID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Profile by ID : "+ profile);
        assertNotNull(profile);
        assertEquals(ID, profile.getId());
    }
    @Test
    public void testCreateProfile() throws Exception{
        respondWith(PROFILE);
        String profileName = "lion-test";
        ProfileCreate newProfile = new SenlinProfileCreate();
        newProfile.toBuilder()
                .name(profileName);
        Profile profile = osv3().senlin().profile().create(newProfile);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Profile : " + profile);
        assertEquals(profileName, profile.getName());
    }
    @Test
    public void testUpdateProfile() throws Exception{
        respondWith(PROFILE);
        String profileName = "lion-test";
        ProfileCreate newProfile = new SenlinProfileCreate();
        newProfile.toBuilder()
                .name(profileName);
        Profile profile =osv3().senlin().profile().update(ID, newProfile);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Profile : " + profile);
        assertEquals(profileName, profile.getName());

    }
    @Test
    public void testDeleteProfile() {
        respondWith(200);
        ActionResponse result = osv3().senlin().profile().delete(ID);
        assertTrue(result.isSuccess());
    }

}
