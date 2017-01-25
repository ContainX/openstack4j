package org.openstack4j.api.artifact;

import org.openstack4j.common.RestService;
import org.openstack4j.model.artifact.ArtifactUpdate;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;
import org.openstack4j.model.artifact.ToscaTemplatesArtifacts;
import org.openstack4j.model.common.ActionResponse;

import java.io.File;
import java.util.List;

/**
 * Created by vadavi on 24-01-2017.
 */
public interface BaseArtifactService extends RestService {

    /**
     * Lists all artifacts for a given artifact list type.
     */
    ToscaTemplatesArtifacts list();

    ToscaTemplatesArtifact get(String artifactId);

    ToscaTemplatesArtifact create(ToscaTemplatesArtifact toscaTemplatesArtifact);

    ToscaTemplatesArtifact upload(String artifactId, File file);

    ToscaTemplatesArtifact download(String artifactId, File file);

    ActionResponse delete(String artifactId);

    ToscaTemplatesArtifact update(String artifactId, List<ArtifactUpdate> artifactUpdates);

    ToscaTemplatesArtifact activate(String artifactId);

    ToscaTemplatesArtifact deactivate(String artifactId);

    ToscaTemplatesArtifact reactivate(String artifactId);

    ToscaTemplatesArtifact publish(String artifactId);
}
