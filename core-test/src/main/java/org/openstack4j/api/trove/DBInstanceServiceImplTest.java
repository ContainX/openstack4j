package org.openstack4j.api.trove;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.trove.Instance;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


@Test(suiteName="trove/instances")
public class DBInstanceServiceImplTest extends AbstractTest{

    private static final String TROVE_INSTANCES = "/trove/instances.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListInstances() throws Exception{
        respondWith(TROVE_INSTANCES);
        List<? extends Instance> instances = osv2().trove().instanceService().list();
        assertEquals(1, instances.size());
        Instance instance = instances.get(0);
        assertEquals(instance.getFlavor().getId(), "1");
        assertEquals(instance.getHostname(), "e09ad9a3f73309469cf1f43d11e79549caf9acf2.troveexampledb.com");
        assertEquals(instance.getId(), "44b277eb-39be-4921-be31-3d61b43651d7");
        assertEquals(instance.getName(), "json_rack_instance");
        assertEquals(instance.getDatastoreType(), "mysql");
        assertEquals(instance.getDatastoreVersion(), "5.5");
    }

}
