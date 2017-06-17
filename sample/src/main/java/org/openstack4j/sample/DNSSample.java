/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.*;
import org.openstack4j.model.dns.v2.builder.ZoneBuilder;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR.DesignatePTRBuilder;
import org.openstack4j.openstack.dns.v2.domain.DesignateZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-12 10:45:22
 */
public class DNSSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(DNSSample.class);
	private static final String FLOATING_IP_ID = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
	private static final String REGION = "eu-de";
	public static final String PTRDNAME = "www.example.com";
	public static final String ZONE_ID = "2c9eb155587194ec01587224c9f90149";
	private static final String ROUTER_ID = "19664294-0bf6-4271-ad3a-94b8c79c6558";

	@Test
	public void testListZones() {
		List<? extends Zone> list = osclient.dns().zones().list();
		logger.info("All zones: {}", list);
	}

	@Test
	public void testListZonesWithParams() {
		List<? extends Zone> list = osclient.dns().zones().list("public", null, "2");
		logger.info("Public zones: {}", list);

		list = osclient.dns().zones().list("private", null, "1");
		logger.info("Private zones: {}", list);
	}

	@Test
	public void testCreateZones() {
		ZoneBuilder builder = Builders.zone();
		Zone zone = builder.name("example.com.").description("This is an example zone.").build();
		Zone zoneResult = osclient.dns().zones().create(zone);
		logger.info("Create zone: {}", zoneResult);
	}

	@Test
	public void testCreatePrivateZones() {
		DesignateZone.Router router = new DesignateZone.Router("19664294-0bf6-4271-ad3a-94b8c79c6558", REGION, null);
		ZoneBuilder builder = Builders.zone();
		Zone sourceZone = builder.name("example.com.").description("This is an example zone.").type(ZoneType.PRIVATE).router(router).build();
		Zone zoneResult = osclient.dns().zones().create(sourceZone);
		logger.info("Create zone: {}", zoneResult);
	}

	@Test
	public void testGetZone() {
		Zone zone = osclient.dns().zones().get(ZONE_ID);
		logger.info("Get zone: {}", zone);
	}

	@Test
	public void testDeleteZones() {
		Zone deletedZone = osclient.dns().zones().delete(ZONE_ID);
		logger.info("Delete zone: {}", deletedZone);
	}

	@Test
	public void testGetNameServers() {
		List<? extends Nameserver> nameserversList = osclient.dns().zones().listNameservers(ZONE_ID);
		logger.info("Get name servers: {}", nameserversList);
	}

	@Test
	public void testAssociateRouter() {
		DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
		DesignateZone.Router routerResult = osclient.dns().zones().associateRouter(ZONE_ID, router);
		logger.info("Associate router: {}", routerResult);
	}

	@Test
	public void testDisassociateRouter() {
		DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
		DesignateZone.Router routerResult = osclient.dns().zones().disassociateRouter(ZONE_ID, router);
		logger.info("Associate router: {}", routerResult);
	}

	@Test
	public void testCreateRecordset() {
		// create with recordset model
		Recordset recordset = Builders.recordset().name("").type("A").build();
		Recordset created = osclient.dns().recordsets().create("zone-id", recordset);
		logger.info("{}", created);

		// create directly
		Recordset created2 = osclient.dns().recordsets().create("zone-id", "name", "dns-type",
				Lists.newArrayList("record1", "records2", "..."));
		logger.info("{}", created2);
	}

	@Test
	public void listRecordsets() {
		List<? extends Recordset> recordsetsOfZone = osclient.dns().recordsets().list("zone-id");
		logger.info("recordsets of zone: {}", recordsetsOfZone);

		List<? extends Recordset> allRecordsets = osclient.dns().recordsets().list();
		logger.info("all recordsets", allRecordsets);
	}

	@Test
	public void getRecordset() {
		Recordset recordset = osclient.dns().recordsets().get("zone-id", "recordset-id");
		logger.info("recordset", recordset);
	}

	@Test
	public void delRecordset() {
		ActionResponse response = osclient.dns().recordsets().delete("zone-id", "recordset-id");
		if (response.isSuccess()) {
			//
		}
	}
	
	@Test
	public void setupPTR() {
		DesignatePTRBuilder builder = DesignatePTR.builder().ptrdname(PTRDNAME + "1").description("Description for this PTR record").region(REGION).floatingIpId(FLOATING_IP_ID).ttl(300);
		DesignatePTR ptrRecord = builder.build();
		DesignatePTR ptr = osclient.dns().ptrs().setup(ptrRecord);
		logger.info("SetupPTR: {}", ptr);
	}

	@Test
	public void getPTR() {
		DesignatePTR ptr = osclient.dns().ptrs().get(REGION,
				FLOATING_IP_ID);
		logger.info("PTR: {}", ptr);
	}
	@Test
	public void listPTR() {
		List<? extends PTR> list = osclient.dns().ptrs().list();
		logger.info("PTR list: {}", list);
	}

	@Test
	public void listWithFilterPTR() {
		Map<String, Object> filters = new HashMap<>();
		filters.put("limit", "2");
		List<? extends PTR> list = osclient.dns().ptrs().list(filters);
		logger.info("PTR list: {}", list);
	}

	@Test
	public void restorePTR() {
		ActionResponse actionResponse = osclient.dns().ptrs().restore(REGION, FLOATING_IP_ID);
		logger.info("PTR restore: {}", actionResponse);
	}


	public static void main(String[] args) {
		DesignatePTRBuilder builder = DesignatePTR.builder().id("1").ptrdname("example.com");
		DesignatePTR reverseRecord = builder.build();
		System.out.println(reverseRecord);
	}

}
