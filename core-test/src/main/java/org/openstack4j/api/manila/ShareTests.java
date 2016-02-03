package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.Extension;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Test cases for share services
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="Share")
public class ShareTests extends AbstractTest {
    private static final String JSON_EXTENSIONS = "/manila/extensions.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void listExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = os().share().listExtensions();
        assertTrue(extensions.isEmpty());
    }
}
