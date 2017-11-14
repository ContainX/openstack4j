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
package com.huawei.openstack4j.api.dns.v2;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.dns.v2.Action;
import com.huawei.openstack4j.model.dns.v2.RecordSetType;
import com.huawei.openstack4j.model.dns.v2.Recordset;
import com.huawei.openstack4j.model.dns.v2.Status;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.huawei.openstack4j.openstack.dns.v2.options.RecordsetListOptions;

/**
 * Tests the DNS/Designate API version 2 ZoneService
 */
@Test(groups = "dnsV2", suiteName = "DNS/Designate_V2")
public class DesignateRecordsetServiceTest extends AbstractTest {

	private static final String JSON_RECORDSET_A = "/dns/v2/create_recordset_A.json";
	private static final String JSON_RECORDSETLIST = "/dns/v2/list_recordsets.json";
	private static final String JSON_RECORDSETLIST_ZONE = "/dns/v2/list_recordsets_zone.json";
	private static final String JSON_RECORDSET_DELETE = "/dns/v2/delete_recordset.json";

	private static final String ZONE_ID = "2c9eb155587194ec01587224c9f90149";
	private static final String RECORDSET_NAME = "www.example.com.";
	private static final ImmutableList<String> RECORDSET_RECORDS = ImmutableList.of("10.1.0.2");
	private static final Status RECORDSET_STATUS = Status.PENDING_CREATE;
	private static final Action RECORDSET_ACTION = Action.CREATE;
	public static final String DESCRIPTION = "This is an example record set.";
	public static final int TTL = 300;
	public static final String RECORDSET_ID = "2c9eb155587228570158722b6ac30007";

	@Override
	protected Service service() {
		return Service.DNS;
	}

	public void recordsetCreateASuccessTest() throws Exception {
		respondWith(JSON_RECORDSET_A);
		Recordset recordset = osv3().dns().recordsets().create(ZONE_ID, RECORDSET_NAME, DESCRIPTION, "A", TTL,
				Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3"));
		assertEquals(recordset.getZoneId(), ZONE_ID);
		assertEquals(recordset.getName(), RECORDSET_NAME);
		assertEquals(recordset.getType(), RecordSetType.A);
		assertEquals(recordset.getRecords().size(), 3);
		assertEquals(recordset.getStatus(), RECORDSET_STATUS);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void recordsetCreateFailed2Test() throws Exception {
		osv3().dns().recordsets().create(ZONE_ID, null, DESCRIPTION, "A", TTL,
				Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3"));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void recordsetCreateFailed3Test() throws Exception {
		osv3().dns().recordsets().create(ZONE_ID, RECORDSET_NAME, DESCRIPTION, "A", null,
				Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3"));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void recordsetCreateFailed4Test() throws Exception {
		osv3().dns().recordsets().create(ZONE_ID, RECORDSET_NAME, DESCRIPTION, "A", TTL, null);
	}

	public void recordsetGetSuccess() throws Exception {
		respondWith(JSON_RECORDSET_A);
		Recordset recordset = osv3().dns().recordsets().get(ZONE_ID, RECORDSET_ID);
		assertEquals(recordset.getZoneId(), ZONE_ID);
		assertEquals(recordset.getName(), RECORDSET_NAME);
		assertEquals(recordset.getType(), RecordSetType.A);
		assertEquals(recordset.getRecords().size(), 3);
		assertEquals(recordset.getStatus(), RECORDSET_STATUS);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void recordsetGetFailed1Test() throws Exception {
		osv3().dns().recordsets().get(ZONE_ID, null);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void recordsetGetFailed2Test() throws Exception {
		osv3().dns().recordsets().get(null, RECORDSET_ID);
	}

	public void recordsetListTest() throws Exception {
		respondWith(JSON_RECORDSETLIST);
		List<? extends Recordset> allRecordsets = osv3().dns().recordsets()
				.list(RecordsetListOptions.create().limit(50).marker("last-record-set-id").zoneType(ZoneType.PRIVATE));
		assertNotNull(allRecordsets);
		assertEquals(allRecordsets.size(), 5);
		assertEquals(allRecordsets.get(0).getId(), RECORDSET_ID);
	}

	public void recordsetListForZoneWithParamsTest() throws Exception {
		respondWith(JSON_RECORDSETLIST_ZONE);
		List<? extends Recordset> allRecordsets = osv3().dns().recordsets().list(ZONE_ID, 5, null);
		assertNotNull(allRecordsets);
		assertEquals(allRecordsets.size(), 3);
		assertEquals(allRecordsets.get(0).getId(), RECORDSET_ID);
	}

	public void recordsetDeleteTest() throws Exception {
		respondWith(JSON_RECORDSET_DELETE);
		Recordset recordset = osv3().dns().recordsets().get(ZONE_ID, RECORDSET_ID);
		assertEquals(recordset.getZoneId(), ZONE_ID);
		assertEquals(recordset.getType(), RecordSetType.A);
		assertEquals(recordset.getStatus(), Status.PENDING_DELETE);
	}
}
