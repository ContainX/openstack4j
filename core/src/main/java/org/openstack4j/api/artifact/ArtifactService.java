package org.openstack4j.api.artifact;

import org.openstack4j.api.OSClient;
import org.openstack4j.common.RestService;
import org.openstack4j.model.artifact.Artifacts;

/**
 * OpenStack (Glare) Artifact based Operations
 *
 * @author Pavan Vadavi
 */
public interface ArtifactService extends RestService {

    ToscaTemplatesArtifactService toscaTemplatesArtifact();
}
