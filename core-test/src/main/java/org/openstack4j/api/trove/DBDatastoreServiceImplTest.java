package org.openstack4j.api.trove;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.trove.Datastore;
import org.openstack4j.model.trove.DatastoreVersion;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * Created by sumigand on 8/22/2016.
 */

@Test(suiteName="trove/datastores")
public class DBDatastoreServiceImplTest extends AbstractTest{

    private static final String TROVE_DATASTORES = "/trove/datastores.json";
    private static final String TROVE_DATASTORE = "/trove/datastore.json";
    private static final String TROVE_DATASTORE_VERSIONS = "/trove/datastore_versions.json";
    private static final String TROVE_DATASTORE_VERSION = "/trove/datastore_version.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListDatastores() throws Exception{
        respondWith(TROVE_DATASTORES);
        List<? extends Datastore> datastores = osv2().trove().datastoreService().list();
        assertEquals(2, datastores.size());
        Preconditions.checkNotNull(datastores.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Database from List : "+ datastores.get(0));
    }

    @Test
    public void testGetOneDatabases() throws Exception{
        respondWith(TROVE_DATASTORE);
        Datastore datastore = osv2().trove().datastoreService().get("648d260d-c346-4145-8a2d-bbd4d78aedf6");
        Preconditions.checkNotNull(datastore);
        assertEquals(datastore.getId(), "648d260d-c346-4145-8a2d-bbd4d78aedf6");
    }

    @Test
    public void testListDatastoreVersions() throws Exception{
        respondWith(TROVE_DATASTORE_VERSIONS);
        List<? extends DatastoreVersion> datastoreVersions = osv2().trove().datastoreService().listDatastoreVersions("648d260d-c346-4145-8a2d-bbd4d78aedf6");
        assertEquals(2, datastoreVersions.size());
        Preconditions.checkNotNull(datastoreVersions.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Database from List : "+ datastoreVersions.get(0));
    }

    @Test
    public void testGetOneDatastoreVersion() throws Exception{
        respondWith(TROVE_DATASTORE_VERSION);
        DatastoreVersion datastoreVersion = osv2().trove().datastoreService().getDatastoreVersion("648d260d-c346-4145-8a2d-bbd4d78aedf6", "15b7d828-49a5-4d05-af65-e974e0aca7eb");
        Preconditions.checkNotNull(datastoreVersion);
        assertEquals(datastoreVersion.getId(), "15b7d828-49a5-4d05-af65-e974e0aca7eb");
    }

}
