package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.ShareNetwork;
import org.openstack4j.model.manila.ShareNetworkCreate;
import org.openstack4j.model.manila.ShareNetworkUpdateOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for share networks
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="ShareNetwork")
public class ShareNetworkTests extends AbstractTest {
    private static final String JSON_SHARE_NETWORK = "/manila/share_network.json";
    private static final String JSON_SHARE_NETWORK_CREATE = "/manila/share_network_create.json";
    private static final String JSON_SHARE_NETWORK_UPDATE = "/manila/share_network_update.json";
    private static final String JSON_SHARE_NETWORKS = "/manila/share_networks.json";
    private static final String JSON_SHARE_NETWORKS_DETAIL = "/manila/share_networks_detail.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_SHARE_NETWORK_CREATE);

        ShareNetworkCreate shareNetworkCreate = Builders.shareNetwork()
                .neutronNet("9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e", "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694")
                .name("my_network")
                .description("This is my share network")
                .build();

        ShareNetwork shareNetwork = osv3().share().shareNetworks().create(shareNetworkCreate);

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(shareNetwork.getNeutronNetId(), "9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e");
        assertEquals(shareNetwork.getNeutronSubnetId(), "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694");
        assertNull(shareNetwork.getNovaNetId());
        assertNull(shareNetwork.getNetworkType());
        assertNull(shareNetwork.getSegmentationId());
        assertNull(shareNetwork.getCidr());
        assertNull(shareNetwork.getIpVersion());
        assertEquals(shareNetwork.getName(), "my_network");
        assertEquals(shareNetwork.getDescription(), "This is my share network");
        assertEquals(shareNetwork.getCreatedAt(), "2016-02-12T20:40:23.242421");
        assertNull(shareNetwork.getUpdatedAt());
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SHARE_NETWORKS);

        List<? extends ShareNetwork> shareNetworks = osv3().share().shareNetworks().list();
        assertEquals(shareNetworks.size(), 1);

        ShareNetwork shareNetwork = shareNetworks.get(0);

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getName(), "my_network");
    }

    @Test
    public void listDetails() throws Exception {
        respondWith(JSON_SHARE_NETWORKS_DETAIL);

        List<? extends ShareNetwork> shareNetworks = osv3().share().shareNetworks().listDetails();
        assertEquals(shareNetworks.size(), 1);

        ShareNetwork shareNetwork = shareNetworks.get(0);

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(shareNetwork.getNeutronNetId(), "9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e");
        assertEquals(shareNetwork.getNeutronSubnetId(), "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694");
        assertNull(shareNetwork.getNovaNetId());
        assertNull(shareNetwork.getNetworkType());
        assertNull(shareNetwork.getSegmentationId());
        assertNull(shareNetwork.getCidr());
        assertNull(shareNetwork.getIpVersion());
        assertEquals(shareNetwork.getName(), "my_network");
        assertEquals(shareNetwork.getDescription(), "This is my share network");
        assertEquals(shareNetwork.getCreatedAt(), "2016-02-12T20:40:23.000000");
        assertNull(shareNetwork.getUpdatedAt());
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_SHARE_NETWORK);

        ShareNetwork shareNetwork = osv3().share().shareNetworks().get("b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(shareNetwork.getNeutronNetId(), "9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e");
        assertEquals(shareNetwork.getNeutronSubnetId(), "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694");
        assertNull(shareNetwork.getNovaNetId());
        assertNull(shareNetwork.getNetworkType());
        assertNull(shareNetwork.getSegmentationId());
        assertNull(shareNetwork.getCidr());
        assertNull(shareNetwork.getIpVersion());
        assertEquals(shareNetwork.getName(), "my_network");
        assertEquals(shareNetwork.getDescription(), "This is my share network");
        assertEquals(shareNetwork.getCreatedAt(), "2016-02-12T20:40:23.000000");
        assertNull(shareNetwork.getUpdatedAt());
    }

    @Test
    public void update() throws Exception {
        respondWith(JSON_SHARE_NETWORK_UPDATE);

        ShareNetwork shareNetwork = osv3().share().shareNetworks().update(
                "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a",
                ShareNetworkUpdateOptions.create().description("This is my updated share network"));

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(shareNetwork.getNeutronNetId(), "9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e");
        assertEquals(shareNetwork.getNeutronSubnetId(), "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694");
        assertNull(shareNetwork.getNovaNetId());
        assertNull(shareNetwork.getNetworkType());
        assertNull(shareNetwork.getSegmentationId());
        assertNull(shareNetwork.getCidr());
        assertNull(shareNetwork.getIpVersion());
        assertEquals(shareNetwork.getName(), "my_network");
        assertEquals(shareNetwork.getDescription(), "This is my updated share network");
        assertEquals(shareNetwork.getCreatedAt(), "2016-02-12T20:40:23.000000");
        assertEquals(shareNetwork.getUpdatedAt(), "2016-02-15T12:01:37.012117");
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareNetworks().delete("b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertTrue(response.isSuccess());
    }

    @Test
    public void addSecurityService() throws Exception {
        respondWith(JSON_SHARE_NETWORK);

        ShareNetwork shareNetwork = osv3().share().shareNetworks().addSecurityService(
                "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a",
                "32e921ed-f399-4e7a-b05b-786f482bd369");

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(shareNetwork.getNeutronNetId(), "9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e");
        assertEquals(shareNetwork.getNeutronSubnetId(), "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694");
        assertNull(shareNetwork.getNovaNetId());
        assertNull(shareNetwork.getNetworkType());
        assertNull(shareNetwork.getSegmentationId());
        assertNull(shareNetwork.getCidr());
        assertNull(shareNetwork.getIpVersion());
        assertEquals(shareNetwork.getName(), "my_network");
        assertEquals(shareNetwork.getDescription(), "This is my share network");
        assertEquals(shareNetwork.getCreatedAt(), "2016-02-12T20:40:23.000000");
        assertNull(shareNetwork.getUpdatedAt());
    }

    @Test
    public void removeSecurityService() throws Exception {
        respondWith(JSON_SHARE_NETWORK);

        ShareNetwork shareNetwork = osv3().share().shareNetworks().removeSecurityService(
                "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a",
                "32e921ed-f399-4e7a-b05b-786f482bd369");

        assertEquals(shareNetwork.getId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(shareNetwork.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(shareNetwork.getNeutronNetId(), "9ae26d09-f4a3-4ee0-a1e4-6ab11b2bb77e");
        assertEquals(shareNetwork.getNeutronSubnetId(), "9fe6a763-0f0e-4b6c-b1e7-339eb61f0694");
        assertNull(shareNetwork.getNovaNetId());
        assertNull(shareNetwork.getNetworkType());
        assertNull(shareNetwork.getSegmentationId());
        assertNull(shareNetwork.getCidr());
        assertNull(shareNetwork.getIpVersion());
        assertEquals(shareNetwork.getName(), "my_network");
        assertEquals(shareNetwork.getDescription(), "This is my share network");
        assertEquals(shareNetwork.getCreatedAt(), "2016-02-12T20:40:23.000000");
        assertNull(shareNetwork.getUpdatedAt());
    }
}
