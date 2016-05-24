package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.ShareInstance;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test Cases for share instances
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="ShareInstance")
public class ShareInstanceTests extends AbstractTest {
    private static final String JSON_SHARE_INSTANCES = "/manila/share_instances.json";
    private static final String JSON_SHARE_INSTANCE = "/manila/share_instance.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SHARE_INSTANCES);

        List<? extends ShareInstance> shareInstances = osv3().share().shareInstances().list();
        assertEquals(shareInstances.size(), 2);

        ShareInstance shareInstance1 = shareInstances.get(0);
        ShareInstance shareInstance2 = shareInstances.get(1);

        assertEquals(shareInstance1.getStatus(), ShareInstance.Status.ERROR);
        assertEquals(shareInstance1.getShareId(), "406ea93b-32e9-4907-a117-148b3945749f");
        assertEquals(shareInstance1.getAvailabilityZone(), "nova");
        assertEquals(shareInstance1.getCreatedAt(), "2015-09-07T08:41:20.000000");
        assertEquals(
                shareInstance1.getExportLocation(),
                "10.254.0.3:/shares/share-081f7030-c54f-42f5-98ee-93a37393e0f2");
        assertEquals(shareInstance1.getShareNetworkId(), "713df749-aac0-4a54-af52-10f6c991e80c");
        assertEquals(
                shareInstance1.getExportLocations().get(0),
                "10.254.0.3:/shares/share-081f7030-c54f-42f5-98ee-93a37393e0f2");
        assertEquals(shareInstance1.getShareServerId(), "ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");
        assertEquals(shareInstance1.getHost(), "manila2@generic1#GENERIC1");
        assertEquals(shareInstance1.getId(), "081f7030-c54f-42f5-98ee-93a37393e0f2");

        assertEquals(shareInstance2.getStatus(), ShareInstance.Status.AVAILABLE);
        assertEquals(shareInstance2.getShareId(), "d94a8548-2079-4be0-b21c-0a887acd31ca");
        assertEquals(shareInstance2.getAvailabilityZone(), "nova");
        assertEquals(shareInstance2.getCreatedAt(), "2015-09-07T08:51:34.000000");
        assertEquals(
                shareInstance2.getExportLocation(),
                "10.254.0.3:/shares/share-75559a8b-c90c-42a7-bda2-edbe86acfb7b");
        assertEquals(shareInstance2.getShareNetworkId(), "713df749-aac0-4a54-af52-10f6c991e80c");
        assertEquals(
                shareInstance2.getExportLocations().get(0),
                "10.254.0.3:/shares/share-75559a8b-c90c-42a7-bda2-edbe86acfb7b");
        assertEquals(shareInstance2.getShareServerId(), "ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");
        assertEquals(shareInstance2.getHost(), "manila2@generic1#GENERIC1");
        assertEquals(shareInstance2.getId(), "75559a8b-c90c-42a7-bda2-edbe86acfb7b");
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_SHARE_INSTANCE);

        ShareInstance shareInstance = osv3().share().shareInstances().get("75559a8b-c90c-42a7-bda2-edbe86acfb7b");

        assertEquals(shareInstance.getStatus(), ShareInstance.Status.AVAILABLE);
        assertEquals(shareInstance.getShareId(), "d94a8548-2079-4be0-b21c-0a887acd31ca");
        assertEquals(shareInstance.getAvailabilityZone(), "nova");
        assertEquals(shareInstance.getCreatedAt(), "2015-09-07T08:51:34.000000");
        assertEquals(
                shareInstance.getExportLocation(),
                "10.254.0.3:/shares/share-75559a8b-c90c-42a7-bda2-edbe86acfb7b");
        assertEquals(shareInstance.getShareNetworkId(), "713df749-aac0-4a54-af52-10f6c991e80c");
        assertEquals(
                shareInstance.getExportLocations().get(0),
                "10.254.0.3:/shares/share-75559a8b-c90c-42a7-bda2-edbe86acfb7b");
        assertEquals(shareInstance.getShareServerId(), "ba11930a-bf1a-4aa7-bae4-a8dfbaa3cc73");
        assertEquals(shareInstance.getHost(), "manila2@generic1#GENERIC1");
        assertEquals(shareInstance.getId(), "75559a8b-c90c-42a7-bda2-edbe86acfb7b");
    }

    @Test
    public void resetState() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareInstances().resetState(
                "081f7030-c54f-42f5-98ee-93a37393e0f2",
                ShareInstance.Status.AVAILABLE);
        assertTrue(response.isSuccess());
    }

    @Test
    public void forceDelete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareInstances().forceDelete("081f7030-c54f-42f5-98ee-93a37393e0f2");
        assertTrue(response.isSuccess());
    }
}
