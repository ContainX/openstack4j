package org.openstack4j.openstack.artifact.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.artifact.ArtifactService;
import org.openstack4j.api.artifact.ToscaTemplatesArtifactService;
import org.openstack4j.model.artifact.ArtifactType;
import org.openstack4j.model.artifact.Artifacts;
import org.openstack4j.model.artifact.ToscaTemplatesArtifacts;

/**
 * Created by vadavi on 18-01-2017.
 */
public class ArtifactServiceImpl implements ArtifactService {


    @Override
    public ToscaTemplatesArtifactService toscaTemplatesArtifact() {
        return Apis.get(ToscaTemplatesArtifactService.class);
    }
}
