package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.Image;
import org.testng.annotations.Test;

/**
 * Test cases for Compute Images
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Images")
public class ImageTests extends AbstractTest {

    private static final String JSON_IMAGES = "/compute/images.json";

    public void serverListingTest() throws Exception {
        respondWith(JSON_IMAGES);
        
        List<? extends Image> images = osv3().compute().images().list();
        assertEquals(7, images.size());

    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

}
