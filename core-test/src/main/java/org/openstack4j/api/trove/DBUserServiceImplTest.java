package org.openstack4j.api.trove;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.trove.DatabaseUser;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by sumigand on 8/22/2016.
 */

@Test(suiteName = "trove/users")
public class DBUserServiceImplTest extends AbstractTest {

    private static final String TROVE_DATABASE_USERS = "/trove/database_users.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListDatabases() throws Exception{
        respondWith(TROVE_DATABASE_USERS);
        List<? extends DatabaseUser> databaseUsers = osv2().trove().databaseUsersService().list("54c91755526e44b9808385a263db4aa6");
        assertEquals(2, databaseUsers.size());
        Preconditions.checkNotNull(databaseUsers.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Database from List : "+ databaseUsers.get(0));
    }

    @Test
    public void testDeleteDatabaseUser() {
        respondWith(200);
        ActionResponse result = osv2().trove().databaseUsersService().delete("54c91755526e44b9808385a263db4aa6", "dbuser3");
        assertTrue(result.isSuccess());
    }
}
