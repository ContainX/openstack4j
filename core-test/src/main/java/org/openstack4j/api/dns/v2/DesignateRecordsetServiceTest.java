/*******************************************************************************
 *  Copyright 2017 HuaWei Tld
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
package org.openstack4j.api.dns.v2;

import static org.testng.Assert.*;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.dns.v2.Action;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.model.dns.v2.Status;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

/**
 * Tests the DNS/Designate API version 2 ZoneService
 */
@Test(groups = "dnsV2", suiteName = "DNS/Designate_V2")
public class DesignateRecordsetServiceTest extends AbstractTest {

    private static final String JSON_RECORDSET = "/dns/v2/create_recordset.json";
    private static final String JSON_RECORDSETLIST = "/dns/v2/list_recordsets.json";

    private static final String ZONE_ID = "2150b1bf-dee2-4221-9d85-11f7886fb15f";
    private static final String RECORDSET_NAME = "example.org.";
    private static final String RECORDSET_TYPE = "A";
    private static final ImmutableList<String> RECORDSET_RECORDS = ImmutableList.of("10.1.0.2");
    private static final Status RECORDSET_STATUS = Status.PENDING;
    private static final Action RECORDSET_ACTION = Action.CREATE;
    private static final Integer RECORDSET_VERSION = 1;

    @Override
    protected Service service() {
        return Service.DNS;
    }

    public void recordsetCreateTest() throws Exception {
        respondWith(JSON_RECORDSET);
        Recordset recordset = osv3().dns().recordsets().create(ZONE_ID, RECORDSET_NAME, RECORDSET_TYPE, RECORDSET_RECORDS);
        assertEquals(recordset.getZoneId(), ZONE_ID);
        assertEquals(recordset.getName(), RECORDSET_NAME);
        assertEquals(recordset.getType(), RECORDSET_TYPE);
        assertEquals(recordset.getRecords(), RECORDSET_RECORDS);
        assertEquals(recordset.getStatus(), RECORDSET_STATUS);
        assertEquals(recordset.getAction(), RECORDSET_ACTION);
    }

}
