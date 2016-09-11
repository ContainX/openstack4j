package org.openstack4j.api.trove;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.trove.DatabaseUser;
import org.openstack4j.openstack.trove.builder.TroveBuilders;
import org.openstack4j.openstack.trove.domain.TroveDatabaseUser;
import org.openstack4j.openstack.trove.domain.TroveDatabaseUser.DatabaseUsers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by sumit gandhi on 8/22/2016.
 */

@Test(suiteName = "trove/users")
public class DBUserServiceImplTest extends AbstractTest {

    private static final String TROVE_DATABASE_USERS = "/trove/database_users.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListDatabaseUsers() throws Exception{
        String databaseInstanceId = "54c91755526e44b9808385a263db4aa6";
        respondWith(TROVE_DATABASE_USERS);
        List<? extends DatabaseUser> databaseUsers = osv2().trove().databaseUsersService().list(databaseInstanceId);
        assertEquals(2, databaseUsers.size());
        Preconditions.checkNotNull(databaseUsers.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Database User from List : "+ databaseUsers.get(0));
    }

    @Test
    public void testCreateDatabaseUser() throws Exception{
        String databaseInstanceId = "54c91755526e44b9808385a263db4aa6";
        respondWith(200);
        TroveBuilders troveBuilders = new TroveBuilders();
        TroveDatabaseUser databaseUser = (TroveDatabaseUser)troveBuilders.databaseUserCreate().username("dbuser4")
                .password("password").build();
        List<TroveDatabaseUser> troveDatabaseUsers = new ArrayList<>();
        troveDatabaseUsers.add(databaseUser);
        DatabaseUsers databaseUsers = new DatabaseUsers();
        databaseUsers.setTroveDatabaseUserList(troveDatabaseUsers);
        ActionResponse result = osv2().trove().databaseUsersService().create(databaseInstanceId, databaseUsers);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testDeleteDatabaseUser() {
        String databaseId = "54c91755526e44b9808385a263db4aa6";
        respondWith(200);
        ActionResponse result = osv2().trove().databaseUsersService().delete(databaseId, "dbuser3");
        assertTrue(result.isSuccess());
    }
}
