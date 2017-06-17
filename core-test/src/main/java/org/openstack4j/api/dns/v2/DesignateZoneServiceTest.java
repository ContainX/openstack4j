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
package org.openstack4j.api.dns.v2;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.dns.v2.*;
import org.openstack4j.model.dns.v2.builder.ZoneBuilder;
import org.openstack4j.openstack.dns.v2.domain.DesignateZone;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Tests the DNS/Designate API version 2 ZoneService
 */
@Test(groups = "dnsV2", suiteName = "DNS/Designate_V2")
public class DesignateZoneServiceTest extends AbstractTest {

    private static final String JSON_ZONE = "/dns/v2/create_zone.json";
    private static final String JSON_ZONELIST_PUBLIC = "/dns/v2/list_zones_public.json";
    private static final String JSON_ZONELIST_PRIVATE = "/dns/v2/list_zones_private.json";
    private static final String JSON_ZONE_GET_PRIVATE = "/dns/v2/get_zone_private.json";
    private static final String JSON_ZONENAMESERVERS_PUBLIC = "/dns/v2/zone_nameservers_public.json";
    private static final String JSON_ZONENAMESERVERS_PRIVATE = "/dns/v2/zone_nameservers_private.json";
    private static final String JSON_ZONEDELETE = "/dns/v2/zone_delete.json";
    private static final String JSON_ZONEDELETE_PRIVATE = "/dns/v2/zone_delete_private.json";
    private static final String JSON_ZONE_PRIVATE = "/dns/v2/create_private_zone.json";
    private static final String JSON_ZONE_ASSOCIATE_ROUTER = "/dns/v2/zone_associate_router.json";
    private static final String JSON_ZONE_DISASSOCIATE_ROUTER = "/dns/v2/zone_disassociate_router.json";

    private static final String ZONE_NAME = "example.org.";
    private static final String ZONE_EMAIL = "joe@example.org";
    private static final Status ZONE_STATUS = Status.PENDING_CREATE;
    private static final ZoneType ZONE_TYPE = ZoneType.PUBLIC;
    private static final Integer ZONE_TTL = 7200;
    private static final String ZONE_ID = "a86dba58-0043-4cc6-a1bb-69d5e86f3ca3";
    private static final String ZONE_DESCRIPTION = "This is an example zone.";
    private static final String ZONE_PROJECT_ID = "4335d1f0-f793-11e2-b778-0800200c9a66";
    private static final String REGION = "eu-de";
    private static final String ROUTER_ID = "19664294-0bf6-4271-ad3a-94b8c79c6558";

    @Override
    protected Service service() {
        return Service.DNS;
    }

    public void zoneCreateTest() throws Exception {

        respondWith(JSON_ZONE);

        Zone zone = osv3().dns().zones().create(ZONE_NAME, ZONE_EMAIL);

        assertEquals(zone.getName(), ZONE_NAME);
        assertEquals(zone.getEmail(), ZONE_EMAIL);
        assertEquals(zone.getStatus(), ZONE_STATUS);
        assertEquals(zone.getType(), ZONE_TYPE);
        assertEquals(zone.getTTL(), ZONE_TTL);

    }

    public void zoneCreatePrivateTest() throws Exception {
        respondWith(JSON_ZONE_PRIVATE);

        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
        ZoneBuilder builder = Builders.zone();
        Zone sourceZone = builder.name("example.com.").description("This is an example zone.").type(ZoneType.PRIVATE).router(router).build();

        DesignateZone zone = (DesignateZone) osv3().dns().zones().create(sourceZone);
        assertEquals(zone.getName(), ZONE_NAME);
        assertEquals(zone.getEmail(), ZONE_EMAIL);
        assertEquals(zone.getStatus(), ZONE_STATUS);
        assertEquals(zone.getType(), ZoneType.PRIVATE);
        assertNotNull(zone.getRouter());
        assertEquals(zone.getRouter().getId(), ROUTER_ID);

    }

    public void zoneGetPublicSuccessTest() throws Exception {
        respondWith(JSON_ZONE);

        Zone zone = osv3().dns().zones().get(ZONE_ID);
        assertEquals(zone.getId(), ZONE_ID);
        assertEquals(zone.getName(), ZONE_NAME);
        assertEquals(zone.getEmail(), ZONE_EMAIL);
        assertEquals(zone.getStatus(), ZONE_STATUS);
        assertEquals(zone.getType(), ZONE_TYPE);
        assertEquals(zone.getTTL(), ZONE_TTL);
    }

    public void zoneGetPrivateSuccessTest() throws Exception {
        respondWith(JSON_ZONE_GET_PRIVATE);

        DesignateZone zone = (DesignateZone) osv3().dns().zones().get("ff8080825b8fc86c015b94bc6f8712c3");
        assertNotNull(zone);
        assertNotNull(zone.getRouters());
        assertTrue(zone.getRouters().size() > 0);
        assertEquals(zone.getRouters().get(0).getStatus(), Status.ACTIVE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneGetFailedTest() throws Exception {
        osv3().dns().zones().get(null);
    }

    public void zoneGetNameserversPublicSuccessTest() throws Exception {
        respondWith(JSON_ZONENAMESERVERS_PUBLIC);

        List<? extends Nameserver> nameserversList = osv3().dns().zones().listNameservers(ZONE_ID);
        assertTrue(nameserversList.size() == 2);
        assertEquals(nameserversList.get(0).getHostname(), "ns1.huawei.com.");
    }

    public void zoneGetNameserversPrivateSuccessTest() throws Exception {
        respondWith(JSON_ZONENAMESERVERS_PRIVATE);

        List<? extends Nameserver> nameserversList = osv3().dns().zones().listNameservers(ZONE_ID);
        assertTrue(nameserversList.size() == 2);
        assertEquals(nameserversList.get(0).getAddress(), "100.125.0.81");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneGetNameserversFailedTest() throws Exception {
        osv3().dns().zones().listNameservers(null);
    }

    public void zoneListTest() throws Exception {

        respondWith(JSON_ZONELIST_PUBLIC);

        List<? extends Zone> zoneList = osv3().dns().zones().list();
        assertFalse(zoneList.isEmpty());
        assertEquals(zoneList.get(0).getProjectId(), ZONE_PROJECT_ID);
    }

    public void zonePublicListWithParamsTest() throws Exception {
        respondWith(JSON_ZONELIST_PUBLIC);

        List<? extends Zone> zoneList = osv3().dns().zones().list("public", null, "1");
        assertFalse(zoneList.isEmpty());
        assertTrue(zoneList.size() == 1);
        assertEquals(zoneList.get(0).getType(), ZoneType.PUBLIC);
        assertEquals(zoneList.get(0).getProjectId(), ZONE_PROJECT_ID);
    }

    public void zonePrivateListWithParamsTest() throws Exception {
        respondWith(JSON_ZONELIST_PRIVATE);

        List<? extends Zone> zoneList = osv3().dns().zones().list("private", null, "2");
        assertFalse(zoneList.isEmpty());
        assertTrue(zoneList.size() == 2);
        assertEquals(zoneList.get(0).getType(), ZoneType.PRIVATE);
        assertEquals(zoneList.get(0).getProjectId(), ZONE_PROJECT_ID);
        assertNotNull(((DesignateZone)zoneList.get(0)).getRouters());
        assertTrue(((DesignateZone)zoneList.get(0)).getRouters().size() > 0);
        assertEquals(((DesignateZone)zoneList.get(0)).getRouters().get(0).getStatus(), Status.ACTIVE);
    }

    public void zoneDeleteSuccessTest() throws Exception {
        respondWith(JSON_ZONEDELETE);
        Zone zoneDelete = osv3().dns().zones().delete(ZONE_ID);

        assertNotNull(zoneDelete);
        assertEquals(zoneDelete.getId(), ZONE_ID);
        assertEquals(zoneDelete.getStatus(), Status.PENDING_DELETE);
    }

    public void zoneDeletePrivateSuccessTest() throws Exception {
        respondWith(JSON_ZONEDELETE_PRIVATE);
        DesignateZone zoneDelete = (DesignateZone) osv3().dns().zones().delete(ZONE_ID);

        assertNotNull(zoneDelete);
        assertEquals(zoneDelete.getId(), ZONE_ID);
        assertEquals(zoneDelete.getStatus(), Status.PENDING_DELETE);
        assertNotNull(zoneDelete.getRouters());
        assertTrue(zoneDelete.getRouters().size() > 0);
        assertEquals(zoneDelete.getRouters().get(0).getStatus(), Status.PENDING_DELETE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneDeleteFailedTest() throws Exception {
        osv3().dns().zones().delete(null);
    }

    public void zoneAssociateSuccessRouterTest() throws Exception {
        respondWith(JSON_ZONE_ASSOCIATE_ROUTER);
        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
        DesignateZone.Router routerResult = osv3().dns().zones().associateRouter(ZONE_ID, router);

        assertNotNull(routerResult);
        assertEquals(routerResult.getStatus(), Status.PENDING_CREATE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneAssociateFailedBecauseZoneIDNullRouterTest() throws Exception {
        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
        osv3().dns().zones().associateRouter(null, router);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneAssociateFailedBecauseRouterNullRouterTest() throws Exception {
        osv3().dns().zones().associateRouter(ROUTER_ID, null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneAssociateFailedBecauseRouterRegionNullRouterTest() throws Exception {
        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, null, null);
        osv3().dns().zones().associateRouter(ROUTER_ID, router);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneAssociateFailedBecauseRouterIDNullRouterTest() throws Exception {
        DesignateZone.Router router = new DesignateZone.Router(null, REGION, null);
        osv3().dns().zones().associateRouter(ROUTER_ID, router);
    }

    public void zoneDisassociateSuccessRouterTest() throws Exception {
        respondWith(JSON_ZONE_DISASSOCIATE_ROUTER);
        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
        DesignateZone.Router routerResult = osv3().dns().zones().disassociateRouter(ZONE_ID, router);

        assertNotNull(routerResult);
        assertEquals(routerResult.getStatus(), Status.PENDING_DELETE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneDisassociateFailedBecauseZoneIDNullRouterTest() throws Exception {
        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, REGION, null);
        osv3().dns().zones().disassociateRouter(null, router);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneDisassociateFailedBecauseRouterNullRouterTest() throws Exception {
        osv3().dns().zones().disassociateRouter(ROUTER_ID, null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneDisassociateFailedBecauseRouterRegionNullRouterTest() throws Exception {
        DesignateZone.Router router = new DesignateZone.Router(ROUTER_ID, null, null);
        osv3().dns().zones().disassociateRouter(ROUTER_ID, router);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void zoneDisassociateFailedBecauseRouterIDNullRouterTest() throws Exception {
        DesignateZone.Router router = new DesignateZone.Router(null, REGION, null);
        osv3().dns().zones().disassociateRouter(ROUTER_ID, router);
    }

}
