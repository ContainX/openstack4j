package org.openstack4j.api.trove;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.trove.Instance;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by cp16net on 2/14/16.
 */
@Test(suiteName="Instances")
public class InstanceTests extends AbstractTest {
    private static final String JSON_INSTANCES = "/trove/instances.json";
    private static final String JSON_INSTANCE_CREATE = "/trove/instance_create.json";

    @Test
    public void listInstances()throws Exception {
        respondWith(JSON_INSTANCES);

        List<? extends Instance> instances = os().trove().instances().list();
        assertEquals(1, instances.size());

        Instance i = instances.get(0);
//        assertEquals(1, i.getAddresses().getAddresses("private").size());
//        assertEquals("192.168.0.3", i.getAddresses().getAddresses("private").get(0).getAddr());
        assertEquals(Instance.Status.ACTIVE, i.getStatus());
        assertEquals("instance-1", i.getName());
    }

    @Override
    protected Service service() {
        return Service.TROVE;
    }
}
