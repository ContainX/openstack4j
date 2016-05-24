package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.identity.v3.Region;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 RegionService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneRegionServiceTest extends AbstractTest {

    private static final String JSON_REGIONS_GET_BYID = "/identity/v3/regions_get_byId.json";
    private static final String JSON_REGIONS_UPDATE = "/identity/v3/regions_update_response.json";
    private static final String REGION_CRUD_ID = "Region_CRUD";
    private static final String REGION_CRUD_PARENTREGIONID = "RegionOne";
    private static final String REGION_CRUD_DESCRIPTION = "No description provided.";
    private static final String REGION_CRUD_DESCRIPTION_UPDATE = "A updated region used for CRUD tests.";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Region Tests ------------

    // The following tests are to verify the update() method of the
    // RegionService
    // using HTTP PATCH, which is not supported by betamax.
    // Find more tests in KeystoneRegionServiceSpec in core-integration-test
    // module.

    public void region_crud_test() throws Exception {

        respondWith(JSON_REGIONS_GET_BYID);

        Region region_setToUpdate = osv3().identity().regions().get(REGION_CRUD_ID);

        assertEquals(region_setToUpdate.getId(), REGION_CRUD_ID);
        assertEquals(region_setToUpdate.getDescription(), REGION_CRUD_DESCRIPTION);

        respondWith(JSON_REGIONS_UPDATE);

        Region updatedRegion = osv3().identity().regions()
                .update(region_setToUpdate.toBuilder().description(REGION_CRUD_DESCRIPTION_UPDATE).build());

        assertEquals(updatedRegion.getId(), REGION_CRUD_ID);
        assertEquals(updatedRegion.getDescription(), REGION_CRUD_DESCRIPTION_UPDATE);
        assertEquals(updatedRegion.getParentRegionId(), REGION_CRUD_PARENTREGIONID);

    }

}
