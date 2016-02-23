package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.AbsoluteLimit;
import org.openstack4j.model.manila.Limits;
import org.openstack4j.model.manila.RateLimit;
import org.openstack4j.openstack.manila.domain.ManilaService;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for share services
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="Share")
public class ShareTests extends AbstractTest {
    private static final String JSON_EXTENSIONS = "/manila/extensions.json";
    private static final String JSON_LIMITS = "/manila/limits.json";
    private static final String JSON_SERVICES = "/manila/os-services.json";
    private static final String JSON_SERVICE_ENABLE = "/manila/os-services_enable.json";
    private static final String JSON_SERVICE_DISABLE = "/manila/os-services_disable.json";

    private static final DateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    @Override
    protected Service service() {
        return AbstractTest.Service.SHARE;
    }

    @Test
    public void listExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = os().share().listExtensions();
        assertTrue(extensions.isEmpty());
    }

    @Test
    public void limits() throws Exception {
        respondWith(JSON_LIMITS);
        Limits limits = os().share().limits();

        RateLimit rateLimit = limits.getRate().get(0);
        RateLimit.Limit limit = rateLimit.getLimit().get(0);
        AbsoluteLimit absoluteLimit = limits.getAbsolute();

        // test the rate limit
        assertEquals(rateLimit.getRegex(), "^/shares");
        assertEquals(rateLimit.getUri(), "\"*/shares\"");
        assertEquals(limit.getNextAvailable(), ISO8601.parse("2016-02-11T14:30:06Z"));
        assertEquals(limit.getUnit(), RateLimit.TimeIntervalUnit.MINUTE);
        assertEquals(limit.getVerb(), HttpMethod.POST);
        assertEquals(limit.getRemaining(), 120);
        assertEquals(limit.getValue(), 120);

        // test the absolute limit
        assertEquals(absoluteLimit.getTotalShareNetworksUsed(), 0);
        assertEquals(absoluteLimit.getMaxTotalShareGigabytes(), 1000);
        assertEquals(absoluteLimit.getMaxTotalShareNetworks(), 10);
        assertEquals(absoluteLimit.getTotalSharesUsed(), 0);
        assertEquals(absoluteLimit.getTotalShareGigabytesUsed(), 0);
        assertEquals(absoluteLimit.getTotalShareSnapshotsUsed(), 0);
        assertEquals(absoluteLimit.getMaxTotalShares(), 50);
        assertEquals(absoluteLimit.getTotalSnapshotGigabytesUsed(), 0);
        assertEquals(absoluteLimit.getMaxTotalSnapshotGigabytes(), 1000);
        assertEquals(absoluteLimit.getMaxTotalShareSnapshots(), 50);
    }

    @Test
    public void services() throws Exception {
        respondWith(JSON_SERVICES);

        List<? extends org.openstack4j.model.manila.Service> services = os().share().services();
        assertEquals(services.size(), 2);

        org.openstack4j.model.manila.Service service1 = services.get(0);
        org.openstack4j.model.manila.Service service2 = services.get(1);

        assertEquals(service1.getStatus(), org.openstack4j.model.manila.Service.Status.ENABLED);
        assertEquals(service1.getBinary(), "manila-share");
        assertEquals(service1.getZone(), "nova");
        assertEquals(service1.getHost(), "manila2@generic1");
        assertEquals(service1.getUpdatedAt(), "2015-09-07T13:03:57.000000");
        assertEquals(service1.getState(), org.openstack4j.model.manila.Service.State.UP);
        assertEquals((int) service1.getId(), 1);

        assertEquals(service2.getStatus(), org.openstack4j.model.manila.Service.Status.ENABLED);
        assertEquals(service2.getBinary(), "manila-scheduler");
        assertEquals(service2.getZone(), "nova");
        assertEquals(service2.getHost(), "manila2");
        assertEquals(service2.getUpdatedAt(), "2015-09-07T13:03:57.000000");
        assertEquals(service2.getState(), org.openstack4j.model.manila.Service.State.UP);
        assertEquals((int) service2.getId(), 2);
    }

    @Test
    public void enableService() throws Exception {
        respondWith(JSON_SERVICE_ENABLE);

        ManilaService.ServiceStatus status = os().share().enableService("manila-share", "openstack@cmode");

        assertFalse(status.getDisabled());
        assertEquals(status.getBinary(), "manila-share");
        assertEquals(status.getHost(), "openstack@cmode");
    }

    @Test
    public void disableService() throws Exception {
        respondWith(JSON_SERVICE_DISABLE);

        ManilaService.ServiceStatus status = os().share().disableService("manila-share", "openstack@cmode");

        assertTrue(status.getDisabled());
        assertEquals(status.getBinary(), "manila-share");
        assertEquals(status.getHost(), "openstack@cmode");
    }
}
