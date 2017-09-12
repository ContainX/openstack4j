package org.openstack4j.api.dns.v2;

import com.google.common.collect.ImmutableList;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.dns.v2.Action;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.model.dns.v2.Status;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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

    public void recordsetListTest() throws Exception {

        respondWith(JSON_RECORDSETLIST);

        List<? extends Recordset> recordsetList = osv3().dns().recordsets().list(ZONE_ID);

        assertFalse(recordsetList.isEmpty());
        assertEquals(recordsetList.get(0).getZoneId(), ZONE_ID);
        assertEquals(recordsetList.get(0).getVersion(), RECORDSET_VERSION);

    }

}
