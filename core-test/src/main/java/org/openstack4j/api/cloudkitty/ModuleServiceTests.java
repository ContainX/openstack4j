package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.Module;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/hashMapField", enabled = true)
public class ModuleServiceTests extends AbstractTest {

    private final String JSON_MODULE = "/cloudkitty/rating-module.json";
    private final String JSON_MODULES = "/cloudkitty/rating-modules.json";

    private final String moduleId = "hashmap";

    public void testListModules() throws IOException {
        respondWith(JSON_MODULES);

        List<? extends Module> list = osv3().rating().module().list();

        assertEquals(list.size(), 1);
        performAssertions(list.get(0));
    }

    public void testGetModule() throws IOException {
        respondWith(JSON_MODULE);

        Module module = osv3().rating().module().get(moduleId);

        performAssertions(module);
    }

    public void testPutModule() throws IOException {
        respondWith(JSON_MODULE);

        Module module = osv3().rating().module().update(moduleId,
                Builders.cloudkitty().module()
                        .enabled(true)
                        .priority(2)
                        .id(moduleId)
                        .build()
        );

        performAssertions(module);
    }

    private void performAssertions(Module module) {
        assertEquals(module.getId(), moduleId);
        assertEquals(module.getDescription(), "Sample extension.");
        assertTrue(module.isEnabled());
        assertFalse(module.isHotConfig());
        assertEquals(module.getPriority(), 2);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
