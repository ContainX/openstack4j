package org.openstack4j.api.trove;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.exceptions.ServerResponseException;
import org.openstack4j.model.trove.Instance;
import org.testng.Assert;
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
        assertEquals(Instance.Status.ACTIVE, i.getStatus());
        assertEquals("instance-1", i.getName());
    }

    @Test(expectedExceptions = ServerResponseException.class, invocationCount = 10)
    public void instanceError() {
        String jsonResponse = "{\"itemNotFound\": {"
                + "\"message\": \"Resource test cannot be found.\", "
                + "\"code\": 404}}";

        respondWith(404, jsonResponse);
        os().trove().instances().get("05184ba3-00ba-4fbc-b7a2-03b62b884931");
        Assert.fail("Exception should have been thrown.");
    }

    @Test
    public void createInstance() throws Exception {
        respondWith(JSON_INSTANCE_CREATE);

        Server server = os().trove().instances().create(Builders.server().name("server-test-1").build());
        assertEquals("server-test-1", server.getName());
    }

    @Override
    protected Service service() {
        return Service.TROVE;
    }
}
