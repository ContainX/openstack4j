package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.Share;
import org.openstack4j.model.manila.ShareSnapshot;
import org.openstack4j.model.manila.ShareSnapshotCreate;
import org.openstack4j.model.manila.ShareSnapshotUpdateOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for share snapshots.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="ShareSnapshot")
public class ShareSnapshotTests extends AbstractTest {
    private static final String JSON_SHARE_SNAPSHOT = "/manila/share_snapshot.json";
    private static final String JSON_SHARE_SNAPSHOT_CREATE = "/manila/share_snapshot_create.json";
    private static final String JSON_SHARE_SNAPSHOT_UPDATE = "/manila/share_snapshot_update.json";
    private static final String JSON_SHARE_SNAPSHOTS = "/manila/share_snapshots.json";
    private static final String JSON_SHARE_SNAPSHOTS_DETAIL = "/manila/share_snapshots_detail.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_SHARE_SNAPSHOT_CREATE);

        ShareSnapshotCreate snapshotCreate = Builders.shareSnapshot()
                .shareId("406ea93b-32e9-4907-a117-148b3945749f")
                .force(true)
                .name("snapshot_share1")
                .description("Here is a snapshot of share Share1")
                .build();

        ShareSnapshot snapshot = osv3().share().shareSnapshots().create(snapshotCreate);

        assertEquals(snapshot.getStatus(), ShareSnapshot.Status.CREATING);
        assertEquals(snapshot.getShareId(), "406ea93b-32e9-4907-a117-148b3945749f");
        assertEquals(snapshot.getName(), "snapshot_share1");
        assertEquals(snapshot.getLinks().size(), 2);
        assertEquals(
                snapshot.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot.getCreatedAt(), "2015-09-07T11:50:39.756808");
        assertEquals(snapshot.getDescription(), "Here is a snapshot of share Share1");
        assertEquals(snapshot.getShareProto(), Share.Protocol.NFS);
        assertEquals((int) snapshot.getShareSize(), 1);
        assertEquals(snapshot.getId(), "6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals((int) snapshot.getSize(), 1);
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SHARE_SNAPSHOTS);

        List<? extends ShareSnapshot> snapshots = osv3().share().shareSnapshots().list();

        ShareSnapshot snapshot1 = snapshots.get(0);
        ShareSnapshot snapshot2 = snapshots.get(1);

        assertEquals(snapshot1.getId(), "086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals(
                snapshot1.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals(snapshot1.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot1.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals(snapshot1.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot1.getName(), "snapshot_My_share");

        assertEquals(snapshot2.getId(), "6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(
                snapshot2.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot2.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot2.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot2.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot2.getName(), "snapshot_share1");
    }

    @Test
    public void listDetails() throws Exception {
        respondWith(JSON_SHARE_SNAPSHOTS_DETAIL);

        List<? extends ShareSnapshot> snapshots = osv3().share().shareSnapshots().listDetails();
        assertEquals(snapshots.size(), 2);

        ShareSnapshot snapshot1 = snapshots.get(0);
        ShareSnapshot snapshot2 = snapshots.get(1);

        assertEquals(snapshot1.getStatus(), ShareSnapshot.Status.CREATING);
        assertEquals(snapshot1.getName(), "snapshot_My_share");
        assertEquals(snapshot1.getId(), "086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals(
                snapshot1.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals(snapshot1.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot1.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals(snapshot1.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot1.getCreatedAt(), "2015-09-07T11:55:09.000000");
        assertEquals(snapshot1.getDescription(), "Here is a snapshot of share My_share");
        assertEquals(snapshot1.getShareProto(), Share.Protocol.NFS);
        assertEquals((int) snapshot1.getShareSize(), 1);
        assertEquals(snapshot1.getId(), "086a1aa6-c425-4ecd-9612-391a3b1b9375");
        assertEquals((int) snapshot1.getSize(), 1);

        assertEquals(snapshot2.getStatus(), ShareSnapshot.Status.AVAILABLE);
        assertEquals(snapshot2.getShareId(), "406ea93b-32e9-4907-a117-148b3945749f");
        assertEquals(snapshot2.getName(), "snapshot_share1");
        assertEquals(snapshot2.getLinks().size(), 2);
        assertEquals(
                snapshot2.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot2.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot2.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot2.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot2.getCreatedAt(), "2015-09-07T11:50:39.000000");
        assertEquals(snapshot2.getDescription(), "Here is a snapshot of share Share1");
        assertEquals(snapshot2.getShareProto(), Share.Protocol.NFS);
        assertEquals((int) snapshot2.getShareSize(), 1);
        assertEquals(snapshot2.getId(), "6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals((int) snapshot2.getSize(), 1);
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_SHARE_SNAPSHOT);

        ShareSnapshot snapshot = osv3().share().shareSnapshots().get("6d221c1d-0200-461e-8d20-24b4776b9ddb");

        assertEquals(snapshot.getStatus(), ShareSnapshot.Status.AVAILABLE);
        assertEquals(snapshot.getShareId(), "406ea93b-32e9-4907-a117-148b3945749f");
        assertEquals(snapshot.getName(), "snapshot_share1");
        assertEquals(snapshot.getLinks().size(), 2);
        assertEquals(
                snapshot.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot.getCreatedAt(), "2015-09-07T11:50:39.000000");
        assertEquals(snapshot.getDescription(), "Here is a snapshot of share Share1");
        assertEquals(snapshot.getShareProto(), Share.Protocol.NFS);
        assertEquals((int) snapshot.getShareSize(), 1);
        assertEquals(snapshot.getId(), "6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals((int) snapshot.getSize(), 1);
    }

    @Test
    public void update() throws Exception {
        respondWith(JSON_SHARE_SNAPSHOT_UPDATE);

        ShareSnapshot snapshot = osv3().share().shareSnapshots().update(
                "6d221c1d-0200-461e-8d20-24b4776b9ddb",
                ShareSnapshotUpdateOptions
                        .create()
                        .displayName("snapshot_Share1")
                        .displayDescription("I am changing a description also. Here is a snapshot of share Share1"));

        assertEquals(snapshot.getStatus(), ShareSnapshot.Status.AVAILABLE);
        assertEquals(snapshot.getShareId(), "406ea93b-32e9-4907-a117-148b3945749f");
        assertEquals(snapshot.getName(), "snapshot_Share1");
        assertEquals(snapshot.getLinks().size(), 2);
        assertEquals(
                snapshot.getLinks().get(0).getHref(),
                "http://172.18.198.54:8786/v1/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot.getLinks().get(0).getRel(), "self");
        assertEquals(
                snapshot.getLinks().get(1).getHref(),
                "http://172.18.198.54:8786/16e1ab15c35a457e9c2b2aa189f544e1/snapshots/6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals(snapshot.getLinks().get(1).getRel(), "bookmark");
        assertEquals(snapshot.getCreatedAt(), "2015-09-07T11:50:39.000000");
        assertEquals(snapshot.getDescription(), "I am changing a description also. Here is a snapshot of share Share1");
        assertEquals(snapshot.getShareProto(), Share.Protocol.NFS);
        assertEquals((int) snapshot.getShareSize(), 1);
        assertEquals(snapshot.getId(), "6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertEquals((int) snapshot.getSize(), 1);
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareSnapshots().delete("6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertTrue(response.isSuccess());
    }

    @Test
    public void resetState() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareSnapshots().resetState(
                "6d221c1d-0200-461e-8d20-24b4776b9ddb",
                ShareSnapshot.Status.ERROR);
        assertTrue(response.isSuccess());
    }

    @Test
    public void forceDelete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().shareSnapshots().forceDelete("6d221c1d-0200-461e-8d20-24b4776b9ddb");
        assertTrue(response.isSuccess());
    }
}
