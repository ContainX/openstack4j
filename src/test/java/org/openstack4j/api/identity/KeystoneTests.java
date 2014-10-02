package org.openstack4j.api.identity;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.identity.Role;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.TenantUser;
import org.openstack4j.model.identity.TokenV2;
import org.openstack4j.model.identity.User;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Identity/Keystone")
public class KeystoneTests extends AbstractTest {

    private static final String JSON_ROLES = "/identity/roles.json";
    private static final String JSON_ROLE = "/identity/member-role.json";
    private static final String JSON_USERS = "/identity/users.json";
    private static final String JSON_TENANTS = "/identity/tenants.json";
    private static final String JSON_TENANT = "/identity/tenant-admin.json";
    private static final String JSON_EXTENSIONS = "/identity/extensions.json";
    private static final String JSON_TENANT_USERS = "/identity/tenant-users.json";

    /**
     * Tests authentication and receiving the Access object with the current Token
     *
     * @throws Exception the exception
     */
    @Test(priority=Integer.MIN_VALUE)
    public void authenticateTest() throws Exception {

        respondWith(JSON_ACCESS);
        associateClient(OSFactory.builder()
                .endpoint(authURL("/v2.0"))
                .credentials("admin", "test")
                .tenantName("admin")
                .authenticate());

        assertEquals(((TokenV2)os().getAccess().getToken()).getTenant().getId(), "b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(os().getAccess().getUser().getName(), "admin");
    }

    public void testAuthorizationFailure() throws IOException {
        // First we simulate a 401 Authorization error
        respondWith(401);
        // The retry logic should re-authenticate and would be expecting the Access object
        respondWith(JSON_ACCESS);
        // The retry logic would then run the user lists expecting the proper result
        respondWith(JSON_USERS);
        assertEquals(os().identity().users().list().size(), 8);

        // Positive test to make sure we are back in sync
        respondWith(JSON_TENANT);
        Tenant t = os().identity().tenants().get("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(t.getName(), "admin");

    }


    /**
     * Tests the Role based Operations
     *
     * @throws Exception the exception
     */
    public void rolesTest() throws Exception {
        respondWith(JSON_ROLES);

        List<? extends Role> roles = os().identity().roles().list();
        assertEquals(roles.size(), 5);

        respondWith(JSON_ROLES);
        Role role = os().identity().roles().getByName("admin");
        assertEquals(role.getName(), "admin");

        respondWith(JSON_ROLE);
        role = os().identity().roles().get("b8e55a37fc3748de887f165954448db5");
        assertEquals(role.getName(), "Member");
    }

    /**
     * Tests the User based Operations
     *
     * @throws Exception the exception
     */
    public void usersTest() throws Exception 
    {
        respondWith(JSON_USERS);
        List<? extends User> users = os().identity().users().list();

        assertEquals(users.size(), 8);
    }

    /**
     * Tests finding an endpoint for Region Two which does exist
     * 
     * @throws Exception
     */
    public void testRegionTwo() throws Exception {
        try
        {
            os().useRegion("RegionOne");
            respondWith(JSON_USERS);
            List<? extends User> users = os().identity().users().list();

            assertEquals(users.size(), 8);	   
        }
        finally {
            os().removeRegion();
        }
    }

    /**
     * Tests finding an endpoint for which does not exist
     * 
     * @throws Exception
     */
    @Test(expectedExceptions = { RegionEndpointNotFoundException.class })
    public void testInvalidRegion() throws Exception {
        os().useRegion("RegionInvalid");
        try
        {
            os().identity().users().list();
        }
        finally {
            os().removeRegion();
        }
    }

    /**
     * Tests common API patterns such as expecting a Null if a query by an identifier fails or Empty list on
     * results that have no data
     */
    public void testAPIPatterns() {

        respondWith(404);
        assertNull(os().identity().users().get("bogus_id"));

        respondWith(404);
        assertEquals(os().identity().users().list().size(), 0);
    }

    /**
     * Tests tenant based operations
     * 
     * @throws Exception
     */
    public void testTenants() throws Exception {
        respondWith(JSON_TENANTS);

        List<? extends Tenant> tenants = os().identity().tenants().list();
        assertEquals(tenants.size(), 3);

        respondWith(JSON_TENANT);
        Tenant t = os().identity().tenants().get("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(t.getName(), "admin");
    }

    public void testTenantUsers() throws Exception {
        respondWith(JSON_TENANT_USERS);
        List<? extends TenantUser> users = os().identity().tenants().listUsers("b80f8d4e28b74188858b654cb1fccf7d");
        assertEquals(users.size(), 2);
    }

    /**
     * Tests retrieving Extensions 
     * 
     * @throws Exception
     */
    public void testExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = os().identity().listExtensions();
        assertEquals(extensions.size(), 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

}
