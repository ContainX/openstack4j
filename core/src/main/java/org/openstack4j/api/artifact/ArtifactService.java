package org.openstack4j.api.artifact;

import org.openstack4j.api.OSClient;
import org.openstack4j.common.RestService;
import org.openstack4j.model.artifact.Artifacts;

/**
 * OpenStack (Glare) Artifact Type
 *
 * @author Pavan Vadavi
 */
public interface ArtifactService extends RestService {

    /**
     * Tosca Template Service
     *
     * @return Tosca Template Service
     */
    ToscaTemplatesArtifactService toscaTemplatesArtifact();
}
