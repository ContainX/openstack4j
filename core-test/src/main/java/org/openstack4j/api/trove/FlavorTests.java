package org.openstack4j.api.trove;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.trove.Flavor;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by cp16net on 2/15/16.
 */
@Test(suiteName="Instances")
public class FlavorTests extends AbstractTest {

    private static final String JSON_FLAVOR = "/trove/flavor.json";
    private static final String JSON_FLAVORS = "/trove/flavors.json";

    public void getFlavor() throws Exception {
        respondWith(JSON_FLAVOR);
        Flavor f = os().trove().flavors().get("1");

        assertEquals(512, f.getRam());
        assertEquals("m1.tiny", f.getName());
    }


    public void listFlavors() throws Exception {
        respondWith(JSON_FLAVORS);
        List<? extends Flavor> flavors = os().trove().flavors().list();
        assertEquals(4, flavors.size());
    }
    @Override
    protected Service service()  {
        return Service.TROVE;
    }
}
