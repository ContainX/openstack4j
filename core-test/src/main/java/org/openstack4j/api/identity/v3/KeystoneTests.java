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
 * Tests the Identity/Keystone API version 3
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneTests extends AbstractTest {

    private static final String JSON_AUTH_PROJECT = "/identity.v3/authv3_project.json";
    private static final String JSON_AUTH_DOMAINID_USERID = "/identity.v3/authv3_domainId_userId.json";
    private static final String JSON_AUTH_DOMAINID_USERNAME = "/identity.v3/authv3_domainId_userName.json";
    private static final String JSON_AUTH_DOMAINNAME_USERID = "/identity.v3/authv3_domainName_userId.json";
    private static final String JSON_AUTH_UNSCOPED = "/identity.v3/authv3_unscoped.json";
    private static final String JSON_AUTH_TOKEN = "/identity.v3/authv3_token.json";
    private static final String JSON_AUTH_ERROR_401 = "/identity.v3/authv3_authorizationerror.json";
    private static final String JSON_USER_LIST = "/identity.v3/usersv3_list.json";
    private static final ImmutableMap<String, String> HEADER_AUTH_PROJECT_RESPONSE = ImmutableMap.of("X-Subject-Token", "763fd7e197ab4e00b2e6e0a8d22a8e87", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_REAUTH_PROJECT_RESPONSE = ImmutableMap.of("X-Subject-Token", "8f57cce49fd04b3cb72afdf8c0445b87", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_AUTH_TOKEN_RESPONSE = ImmutableMap.of("X-Subject-Token", "3ecb5c2063904566be4b10406c0f7568", "Content-Type", "application/json");

    // module test
    private String userName = "admin";
    private String userId = "aa9f25defa6d4cafb48466df83106065";
    private String projectId = "123ac695d4db400a9001b91bb3b8aa46";
    private String domainId = "default";
    private String domainName = "Default";
    private String projectDomainId = "default";
    private String password = "test";

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
    public void authenticate_userId_password_projectId_projectDomainId_Test() throws Exception {

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
    public void authenticate_userName_password_projectId_projectDomainId_Test() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userName, password, Identifier.byId(projectDomainId))
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

    /**
     * authenticates with userId+password+domainId
     *
     * @throws Exception
     */
    public void authenticate_userId_password_domainId_Test() throws Exception {

        respondWith(JSON_AUTH_DOMAINID_USERID);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToDomain(Identifier.byId(domainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);

    }

    /**
     * authenticates with userName+password+domainId
     *
     * @throws Exception
     */
    public void authenticate_userName_password_domainId_Test() throws Exception {

        respondWith(JSON_AUTH_DOMAINID_USERNAME);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password, Identifier.byId(domainId))
                .scopeToDomain(Identifier.byId(domainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);

    }

    /**
     * authenticates with userId+password+domainName
     *
     * @throws Exception
     */
    public void authenticate_userId_password_domainName_Test() throws Exception {

        respondWith(JSON_AUTH_DOMAINNAME_USERID);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToDomain(Identifier.byName(domainName))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);

    }

    /**
     * try to authenticate unscoped. should return an
     * UnsupportedOperationException saying it's not implemented yet.
     *
     * @throws Exception
     */
    @Test(expectedExceptions = { UnsupportedOperationException.class })
    public void authenticate_unscoped_exception_Test() throws Exception {

        respondWith(JSON_AUTH_UNSCOPED);

        OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .authenticate();

    }

    /**
     * authenticates with a token+projectId+projectDomainId
     *
     * @throws Exception
     */
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

    /**
     * reAuthenticate after authorizationError e.g. the token expired
     *
     * @throws Exception
     */
    public void reAuthentication_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        respondWith(JSON_USER_LIST);
        assertEquals(os().identity().users().list().size(), 6);

        // 401 Authorization error for example due to expired session..
        respondWithCodeAndResource(401, JSON_AUTH_ERROR_401);

        // .. triggers re-authentication
        respondWithHeaderAndResource(HEADER_REAUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        respondWith(JSON_USER_LIST);
        assertEquals(os().identity().users().list().size(), 6);

    }

}
