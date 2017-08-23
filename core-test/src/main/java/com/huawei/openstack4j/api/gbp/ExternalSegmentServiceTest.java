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
import com.huawei.openstack4j.model.gbp.ExternalSegment;
/**
 * Test cases for External segments on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/external_segments")
public class ExternalSegmentServiceTest extends AbstractTest {

    private static final String EXTERNAL_SEGMENTS="/network/gbp/external_segments.json";
    private static final String EXTERNAL_SEGMENT="/network/gbp/external_segment.json";
    private static final String EXTERNAL_SEGMENT_UPDATE="/network/gbp/external_segment_update.json";
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListExternalSegment() throws Exception{
        respondWith(EXTERNAL_SEGMENTS);
        List<? extends ExternalSegment> externalSegList = osv2().gbp().externalSegment().list();
        assertEquals(2, externalSegList.size());
        Preconditions.checkNotNull(externalSegList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : External Segment from List : "+externalSegList.get(0));
        assertEquals(externalSegList.get(0).getId(), "fff89eb8-8f9d-49a2-b66c-ad75d9a95287");
    }
    @Test
    public void testGetExternalSegment() throws Exception{
        respondWith(EXTERNAL_SEGMENT);
        String id = "df9e5238-084e-4053-8871-81b63167e2f4";
        ExternalSegment externalSegment = osv2().gbp().externalSegment().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : External Segment by ID : "+externalSegment);
        assertNotNull(externalSegment);
        assertEquals(id, externalSegment.getId());
    }
    @Test
    public void testCreateExternalSegment() throws Exception{
        respondWith(EXTERNAL_SEGMENT);
        ExternalSegment externalSegment = osv2().gbp().externalSegment().create(Builders.externalSegment().ipVersion(4).cidr("172.16.0.0/12").name("extSeg01").description("extSeg01-desc").build());
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created External Policy : "+externalSegment);
        assertEquals("extSeg01", externalSegment.getName());
        assertEquals(4, externalSegment.getIpVersion());
        assertEquals("172.16.0.0/12", externalSegment.getCidr());
    }
    @Test
    public void testUpdateExternalSegment() throws Exception{
        respondWith(EXTERNAL_SEGMENT_UPDATE);
        String id = "fff89eb8-8f9d-49a2-b66c-ad75d9a95287";
        ExternalSegment externalSegment =osv2().gbp().externalSegment().update(id, Builders.externalSegment().name("extSeg01-update").description("extSeg01-desc-update").build());
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated External Segment : "+externalSegment);
        assertEquals("extSeg01-desc-update", externalSegment.getDescription());

    }
    @Test
    public void testDeleteExternalSegment() {
        respondWith(200);
        String id = "fff89eb8-8f9d-49a2-b66c-ad75d9a95287";
        ActionResponse result = osv2().gbp().externalSegment().delete(id);
        assertTrue(result.isSuccess());
    }


}
