package org.openstack4j.openstack.artifact.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.artifact.ArtifactType;
import org.openstack4j.model.artifact.Artifacts;
import org.openstack4j.model.artifact.ToscaTemplatesArtifacts;
import org.openstack4j.openstack.artifact.domain.ToscaTemplatesList;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Created by vadavi on 18-01-2017.
 */
public class BaseArtifactServiceImpl extends BaseOpenStackService {
    private ArtifactType artifactType;

    protected BaseArtifactServiceImpl(ArtifactType artifactType) {

        super(ServiceType.ARTIFACT, EnforceVersionToURL.instance(""));
        this.artifactType = artifactType;

    }

    protected <T> T list(Class<T> clazz) {

        return get(clazz,uri("/artifacts/"+ artifactType.value())).execute();

    }

}
