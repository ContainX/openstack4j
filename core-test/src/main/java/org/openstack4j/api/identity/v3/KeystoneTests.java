package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

/**
 * Tests the to the Identity/Keystone API version 3
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneTests extends AbstractTest {

    private static final String JSON_AUTH_PROJECT = "/identity.v3/authv3_project.json";
    private static final String JSON_AUTH_TOKEN = "/identity.v3/authv3_token.json";
    private static final ImmutableMap<String, String> HEADER_AUTH_PROJECT_RESPONSE = ImmutableMap.of("X-Subject-Token", "763fd7e197ab4e00b2e6e0a8d22a8e87", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_AUTH_TOKEN_RESPONSE = ImmutableMap.of("X-Subject-Token", "3ecb5c2063904566be4b10406c0f7568", "Content-Type", "application/json");

    // module test
    private String userName = "admin";
    private String userId = "aa9f25defa6d4cafb48466df83106065";
    private String projectId = "123ac695d4db400a9001b91bb3b8aa46";
    private String projectDomainId = "default";
    private String password = "test";
    // private String regionName = "RegionOne";
    // private String projectDomainName = "Default";
    // private String projectName = "admin";
    // private String userDomainId = "default";
    // private String userDomainName = "Default";

    /**
     * @return the identity service
     */
    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Authentication Tests ------------

    /**
     * authenticates with userId+password+projectId+projectDomainId
     * 
     * @throws Exception
     */
    @Test(enabled = true)
    public void auth_project_Test_A() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

    /**
     * authenticates with userName+password+projectId+projectDomainId
     * 
     * @throws Exception
     */
    @Test(enabled = true)
    public void auth_project_Test_B() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userName, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

    /**
     * authenticates with a token+projectId+projectDomainId
     * 
     * @throws Exception
     */
    @Test(enabled = true)
    public void auth_Token_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        OSClient myOs = OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate();

        respondWithHeaderAndResource(HEADER_AUTH_TOKEN_RESPONSE, 200, JSON_AUTH_TOKEN);

        String tokenId = myOs.getToken().getId();

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .token(tokenId)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        String newTokenId = osv3().getToken().getId();

        assertTrue(newTokenId != tokenId);
        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

}
