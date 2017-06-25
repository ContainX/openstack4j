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
	private static final String PTRDNAME = "www.example.com";
	private static final String ZONE_ID = "ff8080825ca865e8015caa9f452700a8";
	private static final String ROUTER_ID = "19664294-0bf6-4271-ad3a-94b8c79c6558";
	private static final String RECORDSET_ID = "d4f2557d248e4860829f5fef030b209c";

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
		Zone zone = builder.name("a.example1.com.").description("This is an example zone.").build();
		Zone zoneResult = osclient.dns().zones().create(zone);
		logger.info("Create zone: {}", zoneResult);
	}

	@Test
	public void testCreatePrivateZones() {
		DesignateZone.Router router = new DesignateZone.Router("5fbf2de5-c7e5-4ec5-92ef-1e0b128f729f", REGION, null);
		ZoneBuilder builder = Builders.zone();
		Zone sourceZone = builder.name("example.com.").description("This is an example zone.").type(ZoneType.PRIVATE).router(router).build();
		Zone zoneResult = osclient.dns().zones().create(sourceZone);
		logger.info("Create zone: {}", zoneResult);
	}

	@Test
	public void testGetZone() {
		Zone zone = osclient.dns().zones().get("ff8080825ca86646015cc5d399c505c2");
		logger.info("Get zone: {}", zone);
	}

	@Test
	public void testDeleteZones() {
		Zone deletedZone = osclient.dns().zones().delete("ff8080825ca865e8015ca99563af004a");
		logger.info("Delete zone: {}", deletedZone);
	}

	@Test
	public void testGetNameServers() {
		List<? extends Nameserver> nameserversList = osclient.dns().zones().listNameservers(ZONE_ID);
		logger.info("Get name servers: {}", nameserversList);
	}

	@Test
	public void testAssociateRouter() {
		DesignateZone.Router router = new DesignateZone.Router("62615060-5a38-42d4-a391-9b8a109da548", REGION, null);
		DesignateZone.Router routerResult = osclient.dns().zones().associateRouter("ff8080825ca86646015cc5d399c505c2", router);
		logger.info("Associate router: {}", routerResult);
	}

	@Test
	public void testDisassociateRouter() {
		DesignateZone.Router router = new DesignateZone.Router("5fbf2de5-c7e5-4ec5-92ef-1e0b128f729f", REGION, null);
		DesignateZone.Router routerResult = osclient.dns().zones().disassociateRouter("ff8080825ca86646015cc5d399c505c2", router);
		logger.info("Associate router: {}", routerResult);
	}

	@Test
	public void testCreateRecordset() {
		// create with recordset model
//		Recordset recordset = Builders.recordset().name("api.turnbig.net").type(RecordSetType.A).ttl(300).records(Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3")).build();
//		Recordset created = osclient.dns().recordsets().create("ff8080825ca865e8015caa9f452700a8", recordset);
//		logger.info("Create record set with recordset model: {}", created);

//		// create Type A directly
//		Recordset created2 = osclient.dns().recordsets().create(ZONE_ID, "example.com.", "This is a type A example record set.", "A", 7200,
//				Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3"));
//		logger.info("Create type A record set directly: {}", created2);
//
		// create Type AAAA directly
		Recordset created3 = osclient.dns().recordsets().create("ff8080825ca865e8015caa9f452700a8", "api.turnbig.net", "This is a type AAAA example record set.", "AAAA", 7200,
				Lists.newArrayList("fe80:0:0:0:202:b3ff:fe1e:8329", "ff03:0db8:85a3:0:0:8a2e:0370:7334"));
		logger.info("Create type AAAA record set directly: {}", created3);
//
//		// create Type MX directly
//		Recordset created4 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type MX example record set.", "MX", 7200,
//				Lists.newArrayList("1 mail.example.com"));
//		logger.info("Create type MX record set directly: {}", created4);
//
//		// create Type CNAME directly
//		Recordset created5 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type CNAME example record set.", "CNAME", 7200,
//				Lists.newArrayList("server1.example.com"));
//		logger.info("Create type CNAME record set directly: {}", created5);
//
//		// create Type TXT directly
//		Recordset created6 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type TXT record set.", "TXT", 7200,
//				Lists.newArrayList("This host is used for sale."));
//		logger.info("Create type TXT record set directly: {}", created6);
//
//		// create Type NS directly
//		Recordset created7 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type NS record set.", "NS", 7200,
//				Lists.newArrayList("node1.example.com.", "node2.example.com."));
//		logger.info("Create type NS record set directly: {}", created7);
	}

	@Test
	public void listRecordsets() {
		List<? extends Recordset> allRecordsetsOfZone = osclient.dns().recordsets().list(ZONE_ID);
		logger.info("all recordsets of zone: {}", allRecordsetsOfZone);

		List<? extends Recordset> recordsetsOfZone = osclient.dns().recordsets().list(ZONE_ID, "2", null);
		logger.info("recordsets of zone: {}", recordsetsOfZone);

		List<? extends Recordset> allRecordsets = osclient.dns().recordsets().list();
		logger.info("all recordsets for project: {}", allRecordsets);

		List<? extends Recordset> recordsetsOfProject = osclient.dns().recordsets().list("2", null);
		logger.info("recordsets for project: {}", recordsetsOfProject);
	}

	@Test
	public void getRecordset() {
		Recordset recordset = osclient.dns().recordsets().get("ff80808259367d380159687a3b0e07b7", "ff80808259367d380159687a3b0e07b8");
		logger.info("Get recordset: {}", recordset);
	}

	@Test
	public void delRecordset() {
		Recordset recordset = osclient.dns().recordsets().delete("ff80808259367d380159687a3b0e07b7", "ff80808259367d380159687a3b0e07b8");
		logger.info("Delete recordset: {}", recordset);
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
