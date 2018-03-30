package org.openstack4j.api.dns.v2;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.Action;
import org.openstack4j.model.dns.v2.Status;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.model.dns.v2.ZoneType;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Tests the DNS/Designate API version 2 ZoneService
 */
@Test(groups = "dnsV2", suiteName = "DNS/Designate_V2")
public class DesignateZoneServiceTest extends AbstractTest {

    private static final String JSON_ZONE = "/dns/v2/create_zone.json";
    private static final String JSON_ZONELIST = "/dns/v2/list_zones.json";

    private static final String ZONE_NAME = "example.org.";
    private static final String ZONE_EMAIL = "joe@example.org";
    private static final Status ZONE_STATUS = Status.ACTIVE;
    private static final Action ZONE_ACTION = Action.CREATE;
    private static final ZoneType ZONE_TYPE = ZoneType.PRIMARY;
    private static final Integer ZONE_TTL = 7200;
    private static final String ZONE_ID = "a86dba58-0043-4cc6-a1bb-69d5e86f3ca3";
    private static final String ZONE_DESCRIPTION = "This is an example zone.";
    private static final String ZONE_PROJECT_ID = "4335d1f0-f793-11e2-b778-0800200c9a66";

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
        assertEquals(zone.getAction(), ZONE_ACTION);
        assertEquals(zone.getType(), ZONE_TYPE);
        assertEquals(zone.getTTL(), ZONE_TTL);

    }

    public void zoneUpdateTest() throws Exception {

        respondWith(JSON_ZONE);

        Zone zone = osv3().dns().zones().update(Builders.dnsV2().zone().id(ZONE_ID).description(ZONE_DESCRIPTION).build());

        assertEquals(zone.getId(), ZONE_ID);
        assertEquals(zone.getDescription(), ZONE_DESCRIPTION);

    }

    public void zoneListTest() throws Exception {

        respondWith(JSON_ZONELIST);

        List<? extends Zone> zoneList = osv3().dns().zones().list();

        assertFalse(zoneList.isEmpty());
        assertEquals(zoneList.get(0).getProjectId(), ZONE_PROJECT_ID);
    }

    public void zoneDeleteTest() throws Exception {

        respondWith(204);

        ActionResponse zoneDeleteResponse = osv3().dns().zones().delete(ZONE_ID);

        assertTrue(zoneDeleteResponse.isSuccess());
    }

}
