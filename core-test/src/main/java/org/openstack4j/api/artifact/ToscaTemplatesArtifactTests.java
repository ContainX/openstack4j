package org.openstack4j.api.artifact;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests the Artifact -> Tosca Template Artifact API against the mock webserver and spec based
 * json responses
 *
 * @author Pavan Vadavi
 */
@Test(suiteName = "ToscaTemplatesArtifact")
public class ToscaTemplatesArtifactTests extends AbstractTest {

    private static final String JSON_ARTIFACT = "/artifact/tosca_templates_artifact.json";
    private static final String ARTIFACT_ID = "b8f88696-80e7-4f89-abbc-1975991ba879";

    @Override
    protected Service service() {
        return Service.ARTIFACT;
    }

    @Test
    public void getArtifact() throws Exception {
        respondWith(JSON_ARTIFACT);
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().get(ARTIFACT_ID);
        server.takeRequest();
        assertEquals(toscaTemplatesArtifact.getName(), "test.zip");
        assertEquals(toscaTemplatesArtifact.getStatus(), "active");
    }
}
