package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.QuotaSet;
import org.openstack4j.model.manila.QuotaSetUpdateOptions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for quota sets.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="QuotaSet")
public class QuotaSetTests extends AbstractTest {
    private static final String JSON_QUOTA_SET = "/manila/quota_set.json";
    private static final String JSON_QUOTA_SET_UPDATE = "/manila/quota_set_update.json";
    private static final String JSON_QUOTA_SET_DEFAULTS = "/manila/quota_set_defaults.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_QUOTA_SET);

        QuotaSet quotaSet = osv3().share().quotaSets().get("test_tenant");

        assertEquals((int) quotaSet.getGigabytes(), 1000);
        assertEquals((int) quotaSet.getShares(), 50);
        assertEquals((int) quotaSet.getSnapshotGigabytes(), 1000);
        assertEquals((int) quotaSet.getSnapshots(), 50);
        assertEquals(quotaSet.getId(), "16e1ab15c35a457e9c2b2aa189f544e1");
        assertEquals((int) quotaSet.getShareNetworks(), 10);
    }

    @Test
    public void update() throws Exception {
        respondWith(JSON_QUOTA_SET_UPDATE);



        QuotaSet quotaSet = osv3().share().quotaSets().update(
                "test_tenant",
                "test_user",
                QuotaSetUpdateOptions
                        .create()
                        .snapshotGigabytes(999)
                        .snapshots(49)
                        .shareNetworks(9));

        assertEquals((int) quotaSet.getGigabytes(), 1000);
        assertEquals((int) quotaSet.getSnapshotGigabytes(), 999);
        assertEquals((int) quotaSet.getShares(), 50);
        assertEquals((int) quotaSet.getSnapshots(), 49);
        assertEquals((int) quotaSet.getShareNetworks(), 9);
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().quotaSets().delete("test_tenant");
        assertTrue(response.isSuccess());
    }

    @Test
    public void getDefault() throws Exception {
        respondWith(JSON_QUOTA_SET_DEFAULTS);

        QuotaSet quotaSet = osv3().share().quotaSets().get("test_tenant");

        assertEquals((int) quotaSet.getGigabytes(), 1000);
        assertEquals((int) quotaSet.getShares(), 50);
        assertEquals((int) quotaSet.getSnapshotGigabytes(), 1000);
        assertEquals((int) quotaSet.getSnapshots(), 50);
        assertEquals(quotaSet.getId(), "16e1ab15c35a457e9c2b2aa189f544e1");
        assertEquals((int) quotaSet.getShareNetworks(), 10);
    }
}
