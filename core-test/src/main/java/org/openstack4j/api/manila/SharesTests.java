package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.Access;
import org.openstack4j.model.manila.Share;
import org.openstack4j.model.manila.ShareCreate;
import org.openstack4j.model.manila.ShareUpdateOptions;
import org.openstack4j.model.manila.actions.AccessOptions;
import org.openstack4j.openstack.common.Metadata;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Test cases for shares
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="Shares")
public class SharesTests extends AbstractTest{
    private static final String JSON_SHARE = "/manila/share.json";
    private static final String JSON_SHARE_CREATE = "/manila/share_create.json";
    private static final String JSON_SHARE_UPDATE = "/manila/share_update.json";
    private static final String JSON_SHARES = "/manila/shares.json";
    private static final String JSON_SHARES_DETAIL = "/manila/shares_detail.json";
    private static final String JSON_SHARE_METADATA = "/manila/share_metadata.json";
    private static final String JSON_SHARE_METADATA_UPDATE = "/manila/share_metadata_update.json";
    private static final String JSON_SHARE_METADATA_SET = "/manila/share_metadata_set.json";
    private static final String JSON_SHARE_ACTION_GRANTACCESS = "/manila/share_action_grantaccess.json";
    private static final String JSON_SHARE_ACTION_LISTACCESS = "/manila/share_action_listaccess.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_SHARE_CREATE);

        ShareCreate shareCreate = Builders.share()
                .description("My third share")
                .shareProto(Share.Protocol.NFS)
                .shareNetworkId("b1fb4828-93a2-4bbe-b388-7c9ccd69c17a")
                .name("my_third_share")
                .isPublic(true)
                .size(1)
                .build();

        Share share = osv3().share().shares().create(shareCreate);

        assertEquals(share.getStatus(), Share.Status.CREATING);
        assertEquals(share.getLinks().size(), 2);
        assertEquals(
                share.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share.getLinks().get(0).getRel(), "self");
        assertEquals(
                share.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share.getLinks().get(1).getRel(), "bookmark");
        assertNull(share.getExportLocation());
        assertEquals(share.getName(), "my_third_share");
        assertEquals(share.getShareType(), "default");
        assertNull(share.getAvailabilityZone());
        assertEquals(share.getCreatedAt(), "2016-02-16T11:03:45.000000");
        assertEquals(share.getDescription(), "My third share");
        assertTrue(share.getExportLocations().isEmpty());
        assertEquals(share.getShareProto(), Share.Protocol.NFS);
        assertEquals(share.getShareNetworkId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(share.getMetadata().size(), 2);
        assertEquals(share.getMetadata().get("project"), "my_app");
        assertEquals(share.getMetadata().get("aim"), "doc");
        assertNull(share.getShareServerId());
        assertEquals(share.getHost(), "");
        assertEquals(share.getVolumeType(), "default");
        assertNull(share.getSnapshotId());
        assertTrue(share.isPublic());
        assertEquals(share.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(share.getId(), "3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals((int) share.getSize(), 1);


    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SHARES);

        List<? extends Share> shares = osv3().share().shares().list();
        assertEquals(shares.size(), 3);

        Share share1 = shares.get(0);
        Share share2 = shares.get(1);
        Share share3 = shares.get(2);

        assertEquals(share1.getId(), "3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(
                share1.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share1.getLinks().get(0).getRel(), "self");
        assertEquals(
                share1.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share1.getLinks().get(1).getRel(), "bookmark");
        assertEquals(share1.getName(), "my_third_share");

        assertEquals(share2.getId(), "6cd19e9f-23f2-4f71-b334-ebde804c33de");
        assertEquals(
                share2.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/6cd19e9f-23f2-4f71-b334-ebde804c33de");
        assertEquals(share2.getLinks().get(0).getRel(), "self");
        assertEquals(
                share2.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/6cd19e9f-23f2-4f71-b334-ebde804c33de");
        assertEquals(share2.getLinks().get(1).getRel(), "bookmark");
        assertEquals(share2.getName(), "my_second_share");

        assertEquals(share3.getId(), "45485ab3-7da5-4521-bf51-477bceb73b38");
        assertEquals(
                share3.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/45485ab3-7da5-4521-bf51-477bceb73b38");
        assertEquals(share3.getLinks().get(0).getRel(), "self");
        assertEquals(
                share3.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/45485ab3-7da5-4521-bf51-477bceb73b38");
        assertEquals(share3.getLinks().get(1).getRel(), "bookmark");
        assertEquals(share3.getName(), "my_first_share");
    }

    @Test
    public void listDetails() throws Exception {
        respondWith(JSON_SHARES_DETAIL);

        List<? extends Share> shares = osv3().share().shares().listDetails();
        assertEquals(shares.size(), 3);

        Share share1 = shares.get(0);
        Share share2 = shares.get(1);
        Share share3 = shares.get(2);

        assertEquals(share1.getStatus(), Share.Status.ERROR);
        assertEquals(
                share1.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share1.getLinks().get(0).getRel(), "self");
        assertEquals(
                share1.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share1.getLinks().get(1).getRel(), "bookmark");
        assertNull(share1.getExportLocation());
        assertEquals(share1.getName(), "my_third_share");
        assertEquals(share1.getShareType(), "default");
        assertEquals(share1.getAvailabilityZone(), "nova");
        assertEquals(share1.getCreatedAt(), "2016-02-16T11:03:45.000000");
        assertEquals(share1.getDescription(), "My third share");
        assertTrue(share1.getExportLocations().isEmpty());
        assertEquals(share1.getShareProto(), Share.Protocol.NFS);
        assertEquals(share1.getShareNetworkId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(share1.getMetadata().size(), 2);
        assertEquals(share1.getMetadata().get("project"), "my_app");
        assertEquals(share1.getMetadata().get("aim"), "doc");
        assertEquals(share1.getShareServerId(), "8efb539e-76e3-4e7c-9c6c-c732fb9aa00d");
        assertEquals(share1.getHost(), "vagrant-ubuntu-trusty-64.localdomain@generic1#GENERIC1");
        assertEquals(share1.getVolumeType(), "default");
        assertNull(share1.getSnapshotId());
        assertTrue(share1.isPublic());
        assertEquals(share1.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(share1.getId(), "3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals((int) share1.getSize(), 1);

        assertEquals(share2.getStatus(), Share.Status.ERROR);
        assertEquals(
                share2.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/6cd19e9f-23f2-4f71-b334-ebde804c33de");
        assertEquals(share2.getLinks().get(0).getRel(), "self");
        assertEquals(
                share2.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/6cd19e9f-23f2-4f71-b334-ebde804c33de");
        assertEquals(share2.getLinks().get(1).getRel(), "bookmark");
        assertNull(share2.getExportLocation());
        assertEquals(share2.getName(), "my_second_share");
        assertEquals(share2.getShareType(), "default");
        assertEquals(share2.getAvailabilityZone(), "nova");
        assertEquals(share2.getCreatedAt(), "2016-02-16T11:03:19.000000");
        assertEquals(share2.getDescription(), "My second share");
        assertTrue(share2.getExportLocations().isEmpty());
        assertEquals(share2.getShareProto(), Share.Protocol.NFS);
        assertEquals(share2.getShareNetworkId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertTrue(share2.getMetadata().isEmpty());
        assertEquals(share2.getShareServerId(), "719e2f3d-74ce-4264-a5e1-58e94d09e6ae");
        assertEquals(share2.getHost(), "vagrant-ubuntu-trusty-64.localdomain@generic1#GENERIC1");
        assertEquals(share2.getVolumeType(), "default");
        assertNull(share2.getSnapshotId());
        assertTrue(share2.isPublic());
        assertEquals(share2.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(share2.getId(), "6cd19e9f-23f2-4f71-b334-ebde804c33de");
        assertEquals((int) share2.getSize(), 1);

        assertEquals(share3.getStatus(), Share.Status.ERROR);
        assertEquals(
                share3.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/45485ab3-7da5-4521-bf51-477bceb73b38");
        assertEquals(share3.getLinks().get(0).getRel(), "self");
        assertEquals(
                share3.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/45485ab3-7da5-4521-bf51-477bceb73b38");
        assertEquals(share3.getLinks().get(1).getRel(), "bookmark");
        assertNull(share3.getExportLocation());
        assertEquals(share3.getName(), "my_first_share");
        assertEquals(share3.getShareType(), "default");
        assertEquals(share3.getAvailabilityZone(), "nova");
        assertEquals(share3.getCreatedAt(), "2016-02-16T11:02:56.000000");
        assertEquals(share3.getDescription(), "My first share");
        assertTrue(share3.getExportLocations().isEmpty());
        assertEquals(share3.getShareProto(), Share.Protocol.NFS);
        assertEquals(share3.getShareNetworkId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertTrue(share3.getMetadata().isEmpty());
        assertEquals(share3.getShareServerId(), "26b8101d-f014-4d65-b2a3-346771df1386");
        assertEquals(share3.getHost(), "vagrant-ubuntu-trusty-64.localdomain@generic1#GENERIC1");
        assertEquals(share3.getVolumeType(), "default");
        assertNull(share3.getSnapshotId());
        assertTrue(share3.isPublic());
        assertEquals(share3.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(share3.getId(), "45485ab3-7da5-4521-bf51-477bceb73b38");
        assertEquals((int) share3.getSize(), 1);
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_SHARE);

        Share share = osv3().share().shares().get("3d503524-a906-4757-8576-77a2029ff0c4");

        assertEquals(share.getStatus(), Share.Status.ERROR);
        assertEquals(
                share.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share.getLinks().get(0).getRel(), "self");
        assertEquals(
                share.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share.getLinks().get(1).getRel(), "bookmark");
        assertNull(share.getExportLocation());
        assertEquals(share.getName(), "my_third_share");
        assertEquals(share.getShareType(), "default");
        assertEquals(share.getAvailabilityZone(), "nova");
        assertEquals(share.getCreatedAt(), "2016-02-16T11:03:45.000000");
        assertEquals(share.getDescription(), "My third share");
        assertTrue(share.getExportLocations().isEmpty());
        assertEquals(share.getShareProto(), Share.Protocol.NFS);
        assertEquals(share.getShareNetworkId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(share.getMetadata().size(), 2);
        assertEquals(share.getMetadata().get("project"), "my_app");
        assertEquals(share.getMetadata().get("aim"), "doc");
        assertEquals(share.getShareServerId(), "8efb539e-76e3-4e7c-9c6c-c732fb9aa00d");
        assertEquals(share.getHost(), "vagrant-ubuntu-trusty-64.localdomain@generic1#GENERIC1");
        assertEquals(share.getVolumeType(), "default");
        assertNull(share.getSnapshotId());
        assertTrue(share.isPublic());
        assertEquals(share.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(share.getId(), "3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals((int) share.getSize(), 1);
    }

    @Test
    public void update() throws Exception {
        respondWith(JSON_SHARE_UPDATE);

        Share share = osv3().share().shares().update(
                "3d503524-a906-4757-8576-77a2029ff0c4",
                ShareUpdateOptions
                        .create()
                        .isPublic(false)
                        .displayDescription("Changing the share description."));

        assertEquals(share.getStatus(), Share.Status.ERROR);
        assertEquals(
                share.getLinks().get(0).getHref(),
                "http://localhost:8786/v2/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share.getLinks().get(0).getRel(), "self");
        assertEquals(
                share.getLinks().get(1).getHref(),
                "http://localhost:8786/d401b6b1f81943e8919f2b6819755fb6/shares/3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals(share.getLinks().get(1).getRel(), "bookmark");
        assertNull(share.getExportLocation());
        assertEquals(share.getName(), "my_third_share");
        assertEquals(share.getShareType(), "default");
        assertEquals(share.getAvailabilityZone(), "nova");
        assertEquals(share.getCreatedAt(), "2016-02-16T11:03:45.000000");
        assertEquals(share.getDescription(), "Changing the share description.");
        assertTrue(share.getExportLocations().isEmpty());
        assertEquals(share.getShareProto(), Share.Protocol.NFS);
        assertEquals(share.getShareNetworkId(), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(share.getMetadata().size(), 2);
        assertEquals(share.getMetadata().get("project"), "my_app");
        assertEquals(share.getMetadata().get("aim"), "doc");
        assertEquals(share.getShareServerId(), "8efb539e-76e3-4e7c-9c6c-c732fb9aa00d");
        assertEquals(share.getHost(), "vagrant-ubuntu-trusty-64.localdomain@generic1#GENERIC1");
        assertEquals(share.getVolumeType(), "default");
        assertNull(share.getSnapshotId());
        assertFalse(share.isPublic());
        assertEquals(share.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(share.getId(), "3d503524-a906-4757-8576-77a2029ff0c4");
        assertEquals((int) share.getSize(), 1);
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shares().delete("3d503524-a906-4757-8576-77a2029ff0c4");
        assertTrue(response.isSuccess());
    }

    @Test
    public void getMetadata() throws Exception {
        respondWith(JSON_SHARE_METADATA);

        Metadata metadata = osv3().share().shares().getMetadata("3d503524-a906-4757-8576-77a2029ff0c4");

        assertTrue(metadata.containsKey("project"));
        assertTrue(metadata.containsKey("aim"));
        assertEquals(metadata.get("project"), "my_app");
        assertEquals(metadata.get("aim"), "doc");
    }

    @Test
    public void updateMetadata() throws Exception {
        respondWith(JSON_SHARE_METADATA_UPDATE);

        Map<String, String> metadataMap = new HashMap<String, String>();
        metadataMap.put("aim", "changed_doc");
        metadataMap.put("project", "my_app");
        metadataMap.put("new_metadata_key", "new_information");

        Metadata metadata = osv3().share().shares().updateMetadata(
                "3d503524-a906-4757-8576-77a2029ff0c4",
                Metadata.toMetadata(metadataMap));

        assertTrue(metadata.containsKey("project"));
        assertTrue(metadata.containsKey("aim"));
        assertTrue(metadata.containsKey("new_metadata_key"));
        assertEquals(metadata.get("project"), "my_app");
        assertEquals(metadata.get("aim"), "changed_doc");
        assertEquals(metadata.get("new_metadata_key"), "new_information");
    }

    @Test
    public void setMetadata() throws Exception {
        respondWith(JSON_SHARE_METADATA_SET);

        Map<String, String> metadataMap = new HashMap<String, String>();
        metadataMap.put("key1", "value1");

        Metadata metadata = osv3().share().shares().setMetadata(
                "3d503524-a906-4757-8576-77a2029ff0c4",
                Metadata.toMetadata(metadataMap));

        assertTrue(metadata.containsKey("project"));
        assertTrue(metadata.containsKey("aim"));
        assertTrue(metadata.containsKey("new_metadata_key"));
        assertTrue(metadata.containsKey("key"));
        assertTrue(metadata.containsKey("key1"));
        assertEquals(metadata.get("project"), "my_app");
        assertEquals(metadata.get("aim"), "changed_doc");
        assertEquals(metadata.get("new_metadata_key"), "new_information");
        assertEquals(metadata.get("key"), "value");
        assertEquals(metadata.get("key1"), "value1");
    }

    @Test
    public void unsetMetadata() throws Exception {
        respondWith(200);

        ActionResponse response = osv3().share().shares().unsetMetadata(
                "3d503524-a906-4757-8576-77a2029ff0c4",
                "key");
        assertTrue(response.isSuccess());
    }

    @Test
    public void grantAccess() throws Exception {
        respondWith(JSON_SHARE_ACTION_GRANTACCESS);

        Access access = osv3().share().shares().grantAccess(
                "406ea93b-32e9-4907-a117-148b3945749f",
                AccessOptions.create(
                        Access.Level.RW,
                        Access.Type.IP,
                        "0.0.0.0/0"));

        assertEquals(access.getShareId(), "406ea93b-32e9-4907-a117-148b3945749f");
        assertEquals(access.getCreatedAt(), "2015-09-07T09:14:48.000000");
        assertNull(access.getUpdatedAt());
        assertEquals(access.getAccessType(), Access.Type.IP);
        assertEquals(access.getAccessTo(), "0.0.0.0/0");
        assertEquals(access.getAccessLevel(), Access.Level.RW);
        assertEquals(access.getId(), "a25b2df3-90bd-4add-afa6-5f0dbbd50452");
    }

    @Test
    public void revokeAccess() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shares().revokeAccess(
                "406ea93b-32e9-4907-a117-148b3945749f",
                "a25b2df3-90bd-4add-afa6-5f0dbbd50452");
        assertTrue(response.isSuccess());
    }

    @Test
    public void listAccess() throws Exception {
        respondWith(JSON_SHARE_ACTION_LISTACCESS);

        List<? extends Access> accessList = osv3().share().shares().listAccess("406ea93b-32e9-4907-a117-148b3945749f");

        Access access1 = accessList.get(0);
        Access access2 = accessList.get(1);

        assertEquals(access1.getAccessLevel(), Access.Level.RW);
        assertEquals(access1.getState(), Access.State.ERROR);
        assertEquals(access1.getId(), "507bf114-36f2-4f56-8cf4-857985ca87c1");
        assertEquals(access1.getAccessType(), Access.Type.CERT);
        assertEquals(access1.getAccessTo(), "example.com");

        assertEquals(access2.getAccessLevel(), Access.Level.RW);
        assertEquals(access2.getState(), Access.State.ACTIVE);
        assertEquals(access2.getId(), "a25b2df3-90bd-4add-afa6-5f0dbbd50452");
        assertEquals(access2.getAccessType(), Access.Type.IP);
        assertEquals(access2.getAccessTo(), "0.0.0.0/0");


    }

    @Test
    public void resetState() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shares().resetState(
                "406ea93b-32e9-4907-a117-148b3945749f",
                Share.Status.ERROR);
        assertTrue(response.isSuccess());
    }

    @Test
    public void forceDelete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shares().forceDelete("406ea93b-32e9-4907-a117-148b3945749f");
        assertTrue(response.isSuccess());
    }

    @Test
    public void extend() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shares().extend("406ea93b-32e9-4907-a117-148b3945749f", 2);
        assertTrue(response.isSuccess());
    }

    @Test
    public void shrink() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shares().shrink("406ea93b-32e9-4907-a117-148b3945749f", 1);
        assertTrue(response.isSuccess());
    }
}
