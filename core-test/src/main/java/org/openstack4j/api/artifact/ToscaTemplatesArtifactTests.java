package org.openstack4j.api.artifact;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.artifact.ArtifactUpdate;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;
import org.openstack4j.model.artifact.ToscaTemplatesArtifacts;
import org.openstack4j.model.artifact.builder.ArtifactUpdateBuilder;
import org.openstack4j.model.artifact.builder.ToscaTemplatesArtifactBuilder;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Tests the Artifact -> Tosca Template Artifact API against the mock webserver and spec based
 * json responses
 *
 * @author Pavan Vadavi
 */
@Test(suiteName = "ToscaTemplatesArtifact")
public class ToscaTemplatesArtifactTests extends AbstractTest {

    private static final String JSON_GET_ARTIFACT = "/artifact/tosca_templates_get_artifact.json";
    private static final String JSON_CREATE_ARTIFACT = "/artifact/tosca_templates_create_artifact.json";
    private static final String JSON_UPDATE_ARTIFACT = "/artifact/tosca_templates_update_artifact.json";
    private static final String JSON_UPLOAD_ARTIFACT = "/artifact/tosca_templates_upload_artifact.json";
    private static final String JSON_ACTIVATE_ARTIFACT = "/artifact/tosca_templates_activate_artifact.json";
    private static final String JSON_DEACTIVATE_ARTIFACT = "/artifact/tosca_templates_deactivate_artifact.json";
    private static final String JSON_PUBLISH_ARTIFACT = "/artifact/tosca_templates_publish_artifact.json";
    private static final String ARTIFACT_ID = "b8f88696-80e7-4f89-abbc-1975991ba879";
    private static final String JSON_ARTIFACTS = "/artifact/tosca_templates_artifacts.json";

    @Override
    protected Service service() {
        return Service.ARTIFACT;
    }

    @Test
    public void getArtifact() throws Exception {
        respondWith(JSON_GET_ARTIFACT);
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().get(ARTIFACT_ID);
        assertEquals(toscaTemplatesArtifact.getName(), "test.zip");
        assertEquals(toscaTemplatesArtifact.getStatus(), "active");
    }

    @Test
    public void getAllArtifacts() throws Exception {
        respondWith(JSON_ARTIFACTS);
        ToscaTemplatesArtifacts toscaTemplatesArtifacts = osv3().artifact().toscaTemplatesArtifact().list();
        assertEquals(toscaTemplatesArtifacts.getToscaTemplates().get(0).getName(), "test.zip");
        assertEquals(toscaTemplatesArtifacts.getToscaTemplates().get(0).getStatus(), "active");
    }

    @Test
    public void createArtifact() throws Exception {
        respondWith(JSON_CREATE_ARTIFACT);
        ToscaTemplatesArtifactBuilder builder = Builders.toscaTemplatesArtifact();
        builder.name("sample");
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().create(builder.build());
        assertEquals(toscaTemplatesArtifact.getName(), "sample");
        assertEquals(toscaTemplatesArtifact.getStatus(), "drafted");
        assertEquals(toscaTemplatesArtifact.getVisibility(), "private");
    }

    @Test
    public void updateArtifact() throws Exception {
        respondWith(JSON_UPDATE_ARTIFACT);
        ArtifactUpdateBuilder builder = Builders.artifactUpdate();
        builder.op("replace");
        builder.path("/template_format");
        builder.value("yaml");
        List<ArtifactUpdate> updates = new ArrayList<>();
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().update("b8f88696-80e7-4f89-abbc-1975991ba879",updates);
        assertEquals(toscaTemplatesArtifact.getName(), "sample");
        assertEquals(toscaTemplatesArtifact.getTemplateFormat(), "yaml");
        assertEquals(toscaTemplatesArtifact.getStatus(), "drafted");
        assertEquals(toscaTemplatesArtifact.getVisibility(), "private");
    }


    @Test
    public void activateArtifact() throws Exception {
        respondWith(JSON_ACTIVATE_ARTIFACT);
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().activate("b8f88696-80e7-4f89-abbc-1975991ba879");
        assertEquals(toscaTemplatesArtifact.getName(), "sample");
        assertEquals(toscaTemplatesArtifact.getStatus(), "active");
        assertEquals(toscaTemplatesArtifact.getVisibility(), "private");
    }

    @Test
    public void deactivateArtifact() throws Exception {
        respondWith(JSON_DEACTIVATE_ARTIFACT);
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().deactivate("b8f88696-80e7-4f89-abbc-1975991ba879");
        assertEquals(toscaTemplatesArtifact.getName(), "sample");
        assertEquals(toscaTemplatesArtifact.getStatus(), "deactivated");
        assertEquals(toscaTemplatesArtifact.getVisibility(), "private");
    }

    @Test
    public void reactivateArtifact() throws Exception {
        respondWith(JSON_ACTIVATE_ARTIFACT);
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().reactivate("b8f88696-80e7-4f89-abbc-1975991ba879");
        assertEquals(toscaTemplatesArtifact.getName(), "sample");
        assertEquals(toscaTemplatesArtifact.getStatus(), "active");
        assertEquals(toscaTemplatesArtifact.getVisibility(), "private");
    }

    @Test
    public void publishArtifact() throws Exception {
        respondWith(JSON_PUBLISH_ARTIFACT);
        ToscaTemplatesArtifact toscaTemplatesArtifact = osv3().artifact().toscaTemplatesArtifact().publish("b8f88696-80e7-4f89-abbc-1975991ba879");
        assertEquals(toscaTemplatesArtifact.getName(), "sample");
        assertEquals(toscaTemplatesArtifact.getStatus(), "active");
        assertEquals(toscaTemplatesArtifact.getVisibility(), "public");
    }
}
