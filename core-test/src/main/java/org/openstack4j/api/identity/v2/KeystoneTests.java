package org.openstack4j.api.identity.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.identity.v2.Role;
import org.openstack4j.model.identity.v2.Tenant;
import org.openstack4j.model.identity.v2.TenantUser;
import org.openstack4j.model.identity.v2.TokenV2;
import org.openstack4j.model.identity.v2.User;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName = "Identity/Keystone_V2")
public class KeystoneTests extends AbstractTest {

    private static final DateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    private static final String JSON_ROLES = "/identity/v2/roles.json";
    private static final String JSON_ROLE = "/identity/v2/member-role.json";
    private static final String JSON_USERS = "/identity/v2/users.json";
    private static final String JSON_TENANTS = "/identity/v2/tenants.json";
    private static final String JSON_TENANT = "/identity/v2/tenant-admin.json";
    private static final String JSON_EXTENSIONS = "/identity/v2/extensions.json";
    private static final String JSON_TENANT_USERS = "/identity/v2/tenant-users.json";

    /**
     * Tests authentication and receiving the Access object with the current
     * Token
     *
     * @throws Exception the exception
     */
    @Test(priority = Integer.MAX_VALUE)
    public void authenticateTest() throws Exception {

        respondWith(JSON_ACCESS);
        associateClientV2(OSFactory.builderV2().endpoint(authURL("/v2.0")).credentials("admin", "test")
                .tenantName("admin").authenticate());

        assertEquals(((TokenV2) osv2().getAccess().getToken()).getTenant().getId(), "b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(osv2.getAccess().getUser().getName(), "admin");
    }

    public void testAuthorizationFailure() throws IOException {
        // First we simulate a 401 Authorization error
        respondWith(401);
        // The retry logic should re-authenticate and would be expecting the
        // Access object
        respondWith(JSON_ACCESS);
        // The retry logic would then run the user lists expecting the proper
        // result
        respondWith(JSON_USERS);
        assertEquals(osv2.identity().users().list().size(), 8);

        // Positive test to make sure we are back in sync
        respondWith(JSON_TENANT);
        Tenant t = osv2.identity().tenants().get("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(t.getName(), "admin");

    }

    /**
     * Tests the Role based Operations
     *
     * @throws Exception the exception
     */

    public void rolesTest() throws Exception {
        respondWith(JSON_ROLES);

        List<? extends Role> roles = osv2().identity().roles().list();
        assertEquals(roles.size(), 5);

        respondWith(JSON_ROLES);
        Role role = osv2().identity().roles().getByName("admin");
        assertEquals(role.getName(), "admin");

        respondWith(JSON_ROLE);
        role = osv2().identity().roles().get("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(role.getName(), "Member");
    }

    /**
     * Tests the User based Operations
     *
     * @throws Exception the exception
     */
    public void usersTest() throws Exception {
        respondWith(JSON_USERS);
        List<? extends User> users = osv2().identity().users().list();

        assertEquals(users.size(), 8);
    }

    /**
     * Tests finding an endpoint for Region Two which does exist
     *
     * @throws Exception
     */

    public void testRegionTwo() throws Exception {
        try {
            osv2().useRegion("RegionOne");
            respondWith(JSON_USERS);
            List<? extends User> users = osv2().identity().users().list();

            System.out.println("users.size = " + users.size());

            assertEquals(users.size(), 8);
        } finally {
            osv2().removeRegion();
        }
    }

    /**
     * Tests finding an endpoint for which does not exist
     *
     * @throws Exception
     */
    @Test(expectedExceptions = { RegionEndpointNotFoundException.class })
    public void testInvalidRegion() throws Exception {
        osv2().useRegion("RegionInvalid");
        try {
            osv2().identity().users().list();
        } finally {
            osv2().removeRegion();
        }
    }

    /**
     * Tests common API patterns such as expecting a Null if a query by an
     * identifier fails or Empty list on results that have no data
     */
    public void testAPIPatterns() {

        respondWith(404);
        assertNull(osv2().identity().users().get("bogus_id"));

        respondWith(404);
        assertEquals(osv2().identity().users().list().size(), 0);
    }

    /**
     * Tests tenant based operations
     *
     * @throws Exception
     */
    public void testTenants() throws Exception {
        respondWith(JSON_TENANTS);

        List<? extends Tenant> tenants = osv2().identity().tenants().list();
        assertEquals(tenants.size(), 3);

        respondWith(JSON_TENANT);
        Tenant t = osv2().identity().tenants().get("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(t.getName(), "admin");
    }

    public void testTenantUsers() throws Exception {
        respondWith(JSON_TENANT_USERS);
        List<? extends TenantUser> users = osv2().identity().tenants().listUsers("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(users.size(), 2);
    }

    /**
     * Tests retrieving Extensions
     *
     * @throws Exception
     */
    public void testExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = osv2().identity().listExtensions();

        assertEquals(extensions.size(), 4);

        Extension extension0 = extensions.get(0);
        assertEquals(extension0.getUpdated(), ISO8601.parse("2013-07-07T12:00:0-00:00"));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

}
