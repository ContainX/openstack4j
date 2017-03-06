package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.*;
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
    private static final String JSON_AVAILABILITY_ZONES = "/manila/os-availability-zones.json";
    private static final String JSON_SHARE_MANAGE = "/manila/os-share-manage.json";

    private static final DateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    @Override
    protected Service service() {
        return AbstractTest.Service.SHARE;
    }

    @Test
    public void listExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = osv3().share().listExtensions();
        assertTrue(extensions.isEmpty());
    }

    @Test
    public void limits() throws Exception {
        respondWith(JSON_LIMITS);
        Limits limits = osv3().share().limits();

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

        List<? extends org.openstack4j.model.manila.Service> services = osv3().share().services();
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

        ManilaService.ServiceStatus status = osv3().share().enableService("manila-share", "openstack@cmode");

        assertFalse(status.getDisabled());
        assertEquals(status.getBinary(), "manila-share");
        assertEquals(status.getHost(), "openstack@cmode");
    }

    @Test
    public void disableService() throws Exception {
        respondWith(JSON_SERVICE_DISABLE);

        ManilaService.ServiceStatus status = osv3().share().disableService("manila-share", "openstack@cmode");

        assertTrue(status.getDisabled());
        assertEquals(status.getBinary(), "manila-share");
        assertEquals(status.getHost(), "openstack@cmode");
    }

    @Test
    public void availabilityZones() throws Exception {
        respondWith(JSON_AVAILABILITY_ZONES);

        List<? extends AvailabilityZone> availabilityZones = osv3().share().availabilityZones();
        assertEquals(availabilityZones.size(), 1);

        AvailabilityZone availabilityZone = availabilityZones.get(0);

        assertEquals(availabilityZone.getName(), "nova");
        assertEquals(availabilityZone.getCreatedAt(), "2015-09-18T09:50:55.000000");
        assertNull(availabilityZone.getUpdatedAt());
        assertEquals(availabilityZone.getId(), "388c983d-258e-4a0e-b1ba-10da37d766db");
    }

    @Test
    public void manageShare() throws Exception {
        respondWith(JSON_SHARE_MANAGE);

        ShareManage shareManage = Builders.shareManage()
                .protocol(Share.Protocol.NFS)
                .name("share_texas1")
                .shareType("d")
                .addDriverOption("opt1", "opt1")
                .addDriverOption("opt2", "opt2")
                .exportPath("10.254.0.5:/shares/share-42033c24-0261-424f-abda-4fef2f6dbfd5")
                .serviceHost("manila2@unmanage1#UNMANAGE1")
                .description("Lets manage share.")
                .build();

        Share share = osv3().share().manageShare(shareManage);

        assertEquals(share.getLinks().size(), 2);
        assertEquals(
                share.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v2/16e1ab15c35a457e9c2b2aa189f544e1/shares/00137b40-ca06-4ae8-83a3-2c5989eebcce");
        assertEquals(share.getLinks().get(0).getRel(), "self");
        assertEquals(
                share.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/shares/00137b40-ca06-4ae8-83a3-2c5989eebcce");
        assertEquals(share.getLinks().get(1).getRel(), "bookmark");
        assertNull(share.getAvailabilityZone());
        assertNull(share.getShareNetworkId());
        assertTrue(share.getExportLocations().isEmpty());
        assertNull(share.getShareServerId());
        assertEquals(share.getId(), "00137b40-ca06-4ae8-83a3-2c5989eebcce");
        assertNull(share.getSize());
        assertEquals(share.getShareType(), "14747856-08e5-494f-ab40-a64b9d20d8f7");
        assertEquals(share.getShareTypeName(), "d");
        assertEquals(share.getExportLocation(), "10.254.0.5:/shares/share-42033c24-0261-424f-abda-4fef2f6dbfd5");
        assertNull(share.getConsistencyGroupId());
        assertEquals(share.getProjectId(), "16e1ab15c35a457e9c2b2aa189f544e1");
        assertTrue(share.getMetadata().isEmpty());
        assertEquals(share.getStatus(), Share.Status.MANAGE_STARTING);
        assertEquals(share.getDescription(), "Lets manage share.");
        assertEquals(share.getHost(), "manila2@unmanage1#UNMANAGE1");
        assertFalse(share.isPublic());
        assertTrue(share.getSnapshotSupport());
        assertEquals(share.getName(), "share_texas1");
        assertEquals(share.getCreatedAt(), "2015-09-17T16:21:12.000000");
        assertEquals(share.getShareProto(), Share.Protocol.NFS);
        assertEquals(share.getVolumeType(), "d");
        assertNull(share.getSourceCgsnapshotMemberId());


    }

    @Test
    public void unmanageShare() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().unmanageShare("00137b40-ca06-4ae8-83a3-2c5989eebcce");
        assertTrue(response.isSuccess());
    }
}
