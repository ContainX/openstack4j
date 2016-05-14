package org.openstack4j.api.compute;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.Extension;
import org.testng.annotations.Test;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for Nova ExtensionList
 *
 * @author Jeremy Unruh
 */
@Test(suiteName="ExtensionList")
public class ExtensionTests extends AbstractTest {
    private static final String JSON_EXTENSIONS = "/compute/extensions.json";

    private static final DateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    @Test
    public void testExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = osv3().compute().listExtensions();
        assertEquals(2, extensions.size());

        Extension extension1 = extensions.get(0);
        Extension extension2 = extensions.get(1);

        assertEquals(extension1.getUpdated(), ISO8601.parse("2014-12-03T00:00:00Z"));
        assertEquals(extension1.getName(), "Multinic");
        assertTrue(extension1.getLinks().isEmpty());
        assertEquals(extension1.getNamespace(), URI.create("http://docs.openstack.org/compute/ext/fake_xml"));
        assertEquals(extension1.getAlias(), "NMN");
        assertEquals(extension1.getDescription(), "Multiple network support.");

        assertEquals(extension2.getUpdated(), ISO8601.parse("2014-12-03T00:00:00Z"));
        assertEquals(extension2.getName(), "DiskConfig");
        assertTrue(extension2.getLinks().isEmpty());
        assertEquals(extension2.getNamespace(), URI.create("http://docs.openstack.org/compute/ext/fake_xml"));
        assertEquals(extension2.getAlias(), "OS-DCF");
        assertEquals(extension2.getDescription(), "Disk Management Extension.");
    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
}
