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
package com.huawei.openstack4j.api.storage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeType;

@Test(suiteName = "Block Storage Tests")
public class VolumeTypeTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    @Test
    public void createVolumeType() throws Exception {
        respondWith("/storage/v2/createVolumeTypeResponse.json");
        final String volumeTypeName = "testVolume";
        VolumeType volumeType = Builders.volumeType().name(volumeTypeName).build();
        volumeType = osv3().blockStorage().volumes().createVolumeType(volumeType);
        assertEquals(volumeType.getName(), volumeTypeName);
        assertEquals(volumeType.getExtraSpecs().get("capabilities"), "gpu");
    }

    @Test
    public void deleteVolumeType() throws IOException, Exception {
        respondWith(200);
        String volumeTypeId = "volumeTypeToBeDeleted";
        osv3().blockStorage().volumes().deleteVolumeType(volumeTypeId);
    }

    @Test
    public void extendVolume() throws Exception {
        respondWith(200);
        String volumeId = "volumeTestId";
        ActionResponse response = osv3().blockStorage().volumes().extend(volumeId, 30);
        assertTrue(response.isSuccess(), "The http response was not successful");
    }
}
