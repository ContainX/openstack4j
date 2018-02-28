package org.openstack4j.api.cloudkitty.pyscripts;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.cloudkitty.pyscripts.Script;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/pyScripts", enabled = true)
public class PyScriptsServiceTests extends AbstractTest {

    private static final String JSON_SCRIPTS = "/cloudkitty/pyscripts-scripts.json";
    private static final String JSON_SCRIPT = "/cloudkitty/pyscripts-script.json";

    private static final String checksum = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
    private static final String data = "return 0";
    private static final String name = "policy1";
    private static final String scriptId = "bc05108d-f515-4984-8077-de319cbf35aa";

    public void testListScripts() throws IOException {
        respondWith(JSON_SCRIPTS);

        List<? extends Script> list = osv3().rating().pyscripts().list(true);

        assertEquals(list.size(), 1);
        performAssertions(list.get(0));
    }

    public void testGetScript() throws IOException {
        respondWith(JSON_SCRIPT);

        Script script = osv3().rating().pyscripts().get(scriptId);

        performAssertions(script);
    }

    public void testCreateScript() throws IOException {
        respondWith(JSON_SCRIPT);

        Script script = osv3().rating().pyscripts().create(
                Builders.cloudkitty().pyscripts().script()
                        .checksum(checksum)
                        .id(scriptId)
                        .data(data)
                        .name(name)
                        .build()
        );

        performAssertions(script);
    }

    public void testUpdateScript() throws IOException {
        respondWith(JSON_SCRIPT);

        Script script = osv3().rating().pyscripts().update(
                scriptId,
                Builders.cloudkitty().pyscripts().script()
                        .checksum(checksum)
                        .id(scriptId)
                        .data(data)
                        .name(name)
                        .build()
        );

        performAssertions(script);
    }

    public void testDeleteScript() throws IOException {
        respondWith(204);

        ActionResponse response = osv3().rating().pyscripts().delete(scriptId);

        assertTrue(response.isSuccess());
    }

    private void performAssertions(Script script) {
        assertEquals(script.getId(), scriptId);
        assertEquals(script.getChecksum(), checksum);
        assertEquals(script.getData(), data);
        assertEquals(script.getName(), name);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
