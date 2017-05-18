package org.openstack4j.api.identity.v3;

import okhttp3.mockwebserver.RecordedRequest;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.OSClient;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests the Identity/Keystone API version 3 Keystone tokenless
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneTokenlessTest extends AbstractTest {

    private static final String JSON_USERS = "/identity/v3/users.json";
    private static final String DOMAIN_ID = "default";

    /**
     * check headers whether right from request
     *
     * @throws Exception
     */
    public void pass_headers_Test() throws Exception {

        respondWith(JSON_USERS);

        OSClient.OSClientV3 osClient = OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .authenticate();
        osClient.identity().users().list();

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getHeader(ClientConstants.HEADER_X_DOMAIN_ID), DOMAIN_ID);
    }

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }
}
