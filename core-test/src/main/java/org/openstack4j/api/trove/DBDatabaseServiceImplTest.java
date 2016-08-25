package org.openstack4j.api.trove;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.trove.Database;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by sumigand on 8/22/2016.
 */

@Test(suiteName="trove/databases")
public class DBDatabaseServiceImplTest extends AbstractTest{

    private static final String TROVE_DATABASES = "/trove/databases.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListDatabases() throws Exception{
        respondWith(TROVE_DATABASES);
        List<? extends Database> databases = osv2().trove().databaseService().list("54c91755526e44b9808385a263db4aa6");
        assertEquals(5, databases.size());
        Preconditions.checkNotNull(databases.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Database from List : "+ databases.get(0));
    }

    @Test
    public void testDeleteDatabase() {
        respondWith(200);
        String dbName = "exampledb";
        ActionResponse result = osv2().trove().databaseService().delete("54c91755526e44b9808385a263db4aa6",dbName);
        assertTrue(result.isSuccess());
    }


}
