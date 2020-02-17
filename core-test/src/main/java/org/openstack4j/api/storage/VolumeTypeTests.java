package org.openstack4j.api.storage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.storage.block.BlockQuotaSet;
import org.openstack4j.model.storage.block.VolumeType;
import org.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import org.testng.annotations.Test;

@Test(suiteName = "Block Storage Tests")
public class VolumeTypeTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    @Test
    public void createVolumeType() throws Exception {
        respondWith("/storage/v2/createVolumeTypeResponse.json");
        final String volumeTypeName = "testVolume";
        VolumeType volumeType = Builders.volumeType().name(volumeTypeName).build();
        volumeType = osv3().blockStorage().volumes().createVolumeType(volumeType);
        assertEquals(volumeType.getName(), volumeTypeName);
        assertEquals(volumeType.getExtraSpecs().get("capabilities"), "gpu");
    }

    @Test
    public void deleteVolumeType() throws IOException, Exception {
        respondWith(200);
        String volumeTypeId = "volumeTypeToBeDeleted";
        osv3().blockStorage().volumes().deleteVolumeType(volumeTypeId);
    }

    @Test
    public void extendVolume() throws Exception {
        respondWith(200);
        String volumeId = "volumeTestId";
        ActionResponse response = osv3().blockStorage().volumes().extend(volumeId, 30);
        assertTrue(response.isSuccess(), "The http response was not successful");
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
