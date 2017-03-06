package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.ext.Migration;
import org.openstack4j.model.compute.ext.Migration.Status;
import org.testng.annotations.Test;

/**
 * Migrations based Test Cases
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Migrations", enabled=true)
public class MigrationTests extends AbstractTest {

    private static final String JSON_MIGRATIONS = "/compute/migrations.json";

    public void listMigrations() throws Exception {
        respondWith(JSON_MIGRATIONS);
        List<? extends Migration> migrations = osv3().compute().migrations().list();
        
        assertEquals(2, migrations.size());
        assertEquals(Status.DONE, migrations.get(0).getStatus());
    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

}
