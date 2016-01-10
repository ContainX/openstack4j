package org.openstack4j.api.identity;

import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 ServiceManager
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneServiceEndpointServiceTest extends AbstractTest {

    private static final String JSON_SERVICES_GET_BYID = "/identity/services_get_byId.json";
    private static final String JSON_SERVICES_UPDATE = "/identity/services_update_response.json";
    private static final String SERVICE_CRUD_ID = "5439da9864004dd088fce14c1c626a4b";
    private static final String SERVICE_CRUD_DESCRIPTION_UPDATE = "An updated service used for tests";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    public void service_update_Test() throws Exception {

        respondWith(JSON_SERVICES_GET_BYID);

        org.openstack4j.model.identity.Service service_setToUpdate = os().identity().serviceEndpoints()
                .get(SERVICE_CRUD_ID);

        respondWith(JSON_SERVICES_UPDATE);

        org.openstack4j.model.identity.Service updatedService = os().identity().serviceEndpoints()
                .update(service_setToUpdate.toBuilder().description(SERVICE_CRUD_DESCRIPTION_UPDATE).build());

        assertEquals(updatedService.getId(), SERVICE_CRUD_ID);
        assertEquals(updatedService.getDescription(), SERVICE_CRUD_DESCRIPTION_UPDATE);
    }

}
