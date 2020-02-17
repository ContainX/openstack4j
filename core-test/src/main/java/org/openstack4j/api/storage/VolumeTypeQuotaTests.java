package org.openstack4j.api.storage;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.block.BlockQuotaSet;
import org.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test(suiteName = "Block Storage Tests")
public class VolumeTypeQuotaTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    @Test
    public void volumeTypeGigabytesQuota() throws Exception {
        respondWith("/storage/v2/updateQuotaSetResponse.json");
        String volumeTypeGigabytesQuota = "gigabytes_ruby";
        BlockQuotaSet blockQuotaSet = new CinderBlockQuotaSet()
                .toBuilder()
                .volumeTypeQuota( volumeTypeGigabytesQuota, 100 )
                .build();
        blockQuotaSet = osv3().blockStorage().quotaSets().updateForTenant("1-2-3", blockQuotaSet);
        String requestBody = server.takeRequest().getBody().readUtf8();
        assertTrue(requestBody.contains("\"gigabytes_ruby\" : 100"));
        assertTrue(blockQuotaSet.getVolumeTypesQuotas().containsKey( volumeTypeGigabytesQuota ), "Should contain the ruby volume type gigabytes quota");
    }
}
