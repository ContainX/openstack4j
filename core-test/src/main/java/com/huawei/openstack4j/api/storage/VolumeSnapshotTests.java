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

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.storage.block.VolumeSnapshot;

import okhttp3.mockwebserver.RecordedRequest;



@Test(suiteName="Block Storage Tests")
public class VolumeSnapshotTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }
    
    public void listVolumeSnaphotsV1() throws Exception {
        // Check list volumes
        respondWith("/storage/v1/volumesnapshots.json");
        List<? extends VolumeSnapshot> volumes = osv3().blockStorage().snapshots().list();
        assertEquals(volumes.size(), 2);
        
        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v[12]/project-id/snapshots"));
        
        // Check list volumes with filters
        respondWith("/storage/v1/volumesnapshots_filtered.json");
        final String volName = "snap-vol-test-1";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("display_name", volName);
        List<? extends VolumeSnapshot> filteredVolumes = osv3().blockStorage().snapshots().list(filters);
        assertEquals(filteredVolumes.size(), 1);
        
        // Check that the list request is the one we expect
        RecordedRequest filteredListRequest = server.takeRequest();
        assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
        assertTrue(filteredListRequest.getPath().matches("/v[12]/project-id/snapshots\\?display_name=" + volName));
    }
    
}
