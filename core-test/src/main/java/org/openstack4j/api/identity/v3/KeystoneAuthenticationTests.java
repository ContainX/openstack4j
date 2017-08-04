package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

/**
 * Tests the Identity/Keystone API version 3 Authentication
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneAuthenticationTests extends AbstractTest {

    private static final String JSON_AUTH_PROJECT = "/identity/v3/authv3_project.json";
    private static final String JSON_AUTH_DOMAIN = "/identity/v3/authv3_domain.json";
    private static final String JSON_AUTH_TOKEN = "/identity/v3/authv3_token.json";
    private static final String JSON_AUTH_TOKEN_UNSCOPED = "/identity/v3/authv3_token_unscoped.json";
    private static final String JSON_AUTH_UNSCOPED = "/identity/v3/authv3_unscoped.json";
    private static final String JSON_AUTH_ERROR_401 = "/identity/v3/authv3_authorizationerror.json";
    private static final String JSON_USERS = "/identity/v3/users.json";
    private static final ImmutableMap<String, String> HEADER_AUTH_PROJECT_RESPONSE = ImmutableMap.of("X-Subject-Token", "763fd7e197ab4e00b2e6e0a8d22a8e87", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_AUTH_TOKEN_RESPONSE = ImmutableMap.of("X-Subject-Token", "3ecb5c2063904566be4b10406c0f7568", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_REAUTH_TOKEN_RESPONSE = ImmutableMap.of("X-Subject-Token", "3e3f7ec1180e4f1b8ca884d32e04ccfb", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_REAUTH_PROJECT_RESPONSE = ImmutableMap.of("X-Subject-Token", "8f57cce49fd04b3cb72afdf8c0445b87", "Content-Type", "application/json");

    private static final String USER_NAME = "admin";
    private static final String USER_ID = "aa9f25defa6d4cafb48466df83106065";
    private static final String DOMAIN_ID = "default";
    private static final String DOMAIN_NAME = "Default";
    private static final String PROJECT_ID = "123ac695d4db400a9001b91bb3b8aa46";
    private static final String PROJECT_NAME = "admin";
    private static final String PROJECT_DOMAIN_ID = "default";
    private static final String PASSWORD = "test";
    private static final String REGION_EUROPE = "europe";
    private static final String TOKEN_UNSCOPED_ID = "3ecb5c2063904566be4b10406c0f7568";

    /**
     * @return the identity service
     */
    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Authentication Tests ------------

    /**
     * authenticates with userId+password++projectId
     *
     * @throws Exception
     */
    public void authenticate_userId_password_projectId_Test() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .authenticate());

        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getUser().getId(), USER_ID);
        assertEquals(osv3.getToken().getProject().getId(), PROJECT_ID);

    }

    /**
     * authenticates with userId+password+projectId+projectDomainId
     *
     * @throws Exception
     */
    public void authenticate_userId_password_projectId_projectDomainId_Test() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_NAME), Identifier.byId(PROJECT_DOMAIN_ID))
                .authenticate());

        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getUser().getId(), USER_ID);
        assertEquals(osv3.getToken().getProject().getId(), PROJECT_ID);

    }

    /**
     * authenticates with userName+password+projectId+projectDomainId
     *
     * @throws Exception
     */
    public void authenticate_userName_password_projectId_projectDomainId_Test() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_NAME, PASSWORD, Identifier.byId(PROJECT_DOMAIN_ID))
                .scopeToProject(Identifier.byId(PROJECT_NAME), Identifier.byId(PROJECT_DOMAIN_ID))
                .authenticate());

        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getUser().getId(), USER_ID);
        assertEquals(osv3.getToken().getProject().getId(), PROJECT_ID);
    }

    /**
     * authenticates with userId+password+domainId
     *
     * @throws Exception
     */
    public void authenticate_userId_password_domainId_Test() throws Exception {

        respondWith(JSON_AUTH_DOMAIN);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .authenticate());

        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getUser().getId(), USER_ID);
        assertEquals(osv3.getToken().getDomain().getId(), DOMAIN_ID);

    }

    /**
     * authenticates with userName+password+domainId
     *
     * @throws Exception
     */
    public void authenticate_userName_password_domainId_Test() throws Exception {

        respondWith(JSON_AUTH_DOMAIN);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_NAME, PASSWORD, Identifier.byId(DOMAIN_ID))
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .authenticate());

        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getUser().getId(), USER_ID);
        assertEquals(osv3.getToken().getDomain().getId(), DOMAIN_ID);

    }

    /**
     * authenticates with userId+password+domainName
     *
     * @throws Exception
     */
    public void authenticate_userId_password_domainName_Test() throws Exception {

        respondWith(JSON_AUTH_DOMAIN);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byName(DOMAIN_NAME))
                .authenticate());

        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getUser().getId(), USER_ID);
        assertEquals(osv3.getToken().getDomain().getId(), DOMAIN_ID);

    }

    /**
     * authenticates with a token
     *
     * @throws Exception
     */
    public void auth_Token_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .authenticate());

        String tokenId = osv3.getToken().getId();

        respondWithHeaderAndResource(HEADER_AUTH_TOKEN_RESPONSE, 200, JSON_AUTH_TOKEN);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .token(tokenId)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .authenticate());

        String newTokenId = osv3.getToken().getId();

        assertTrue(newTokenId != tokenId);
        assertEquals(osv3.getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3.getToken().getProject().getId(), PROJECT_ID);
    }

    /**
     * get an unscoped token, then reauthenticate with it
     *
     * @throws Exception
     */
    public void authenticate_userId_password_unscoped_reauthenticate_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 201, JSON_AUTH_UNSCOPED);

        OSClientV3 osclient_unscoped = OSFactory.builderV3()
                                .endpoint(authURL("/v3"))
                                .credentials(USER_ID, PASSWORD)
                                .authenticate();

       String tokenId_unscoped = osclient_unscoped.getToken().getId();

       respondWithHeaderAndResource(HEADER_AUTH_TOKEN_RESPONSE, 200, JSON_AUTH_PROJECT);

       OSClientV3 osclient_scoped = (OSFactory.builderV3()
               .endpoint(authURL("/v3"))
               .token(tokenId_unscoped)
               .scopeToProject(Identifier.byId(PROJECT_ID))
               .authenticate());

       String tokenId_scoped = osclient_scoped.getToken().getId();

       respondWithHeaderAndResource(HEADER_REAUTH_TOKEN_RESPONSE, 200, JSON_AUTH_PROJECT);

       associateClientV3(OSFactory.builderV3()
               .endpoint(authURL("/v3"))
               .token(tokenId_scoped)
               .scopeToProject(Identifier.byId(PROJECT_ID))
               .authenticate());

       assertEquals(osv3.getToken().getUser().getId(), USER_ID);
       assertEquals(osv3.getToken().getProject().getId(), PROJECT_ID);

    }

    /**
     * get an unscoped token, then use it to get a scoped token
     *
     * @throws Exception
     */
    public void authenticate_userId_password_unscopedTokenToScopedToken_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_TOKEN_RESPONSE, 201, JSON_AUTH_UNSCOPED);

        OSClientV3 osclient_unscoped = OSFactory.builderV3()
                                        .endpoint(authURL("/v3"))
                                        .credentials(USER_ID, PASSWORD)
                                        .authenticate();

        String tokenUnscopedId = osclient_unscoped.getToken().getId();

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 200, JSON_AUTH_PROJECT);

        OSClientV3 osclient_scoped = OSFactory.builderV3()
                                        .endpoint(authURL("/v3"))
                                        .token(tokenUnscopedId)
                                        .scopeToProject(Identifier.byId(PROJECT_ID))
                                        .authenticate();

        String tokenScopedId = osclient_scoped.getToken().getId();

        assertTrue(tokenUnscopedId != tokenScopedId);

    }

    /**
     * authenticates in another existing region
     *
     * @throws Exception
     */
    @Test(priority=-1)
    public void authenticate_userId_password_domain_region_Test() throws Exception {

        try {

            osv3().useRegion(REGION_EUROPE);

            respondWith(JSON_USERS);
            List<? extends User> userList = osv3().identity().users().list();
            assertNotNull(userList);
        }
        finally {
            osv3().removeRegion();
        }
    }

    /**
     * attempt authenticate in a non-existing region results in an RegionEndpointNotFoundException
     *
     * @throws Exception
     */
    @Test(expectedExceptions= {RegionEndpointNotFoundException.class})
    public void authenticate_userId_password_domain_regionInvalid_Test() throws Exception {

        try {
            osv3().useRegion("regionInvalid");

            osv3().identity().users().list();
        }
        finally {
            osv3().removeRegion();
        }

    }

    /**
     * reAuthenticate after authorizationError e.g. the token expired
     *
     * @throws Exception
     */
    public void reAuthentication_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        associateClientV3(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID), Identifier.byId(PROJECT_ID))
                .authenticate());

        respondWith(JSON_USERS);
        assertEquals(osv3().identity().users().list().size(), 6);

        // 401 Authorization error for example due to expired token..
        respondWithCodeAndResource(401, JSON_AUTH_ERROR_401);

        // .. triggers re-authentication
        respondWithHeaderAndResource(HEADER_REAUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        respondWith(JSON_USERS);
        assertEquals(osv3().identity().users().list().size(), 6);

    }

    /*
     * token based authentication
     *
     * @throws Exception
     */
    public void authenticate_token_unscoped() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_TOKEN_RESPONSE, 201, JSON_AUTH_TOKEN_UNSCOPED);

        OSClientV3 osclient_token_unscoped = (OSFactory.builderV3()
        .endpoint(authURL("/v3"))
        .token(TOKEN_UNSCOPED_ID)
        .authenticate());

        assertEquals(osclient_token_unscoped.getToken().getId(),TOKEN_UNSCOPED_ID);

    }

}
