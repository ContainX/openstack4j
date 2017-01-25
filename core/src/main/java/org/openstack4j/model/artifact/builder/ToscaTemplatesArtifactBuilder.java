package org.openstack4j.model.artifact.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.artifact.Template;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;

/**
 * Created by vadavi on 19-01-2017.
 */
public interface ToscaTemplatesArtifactBuilder extends ArtifactBuilder,  Builder<ToscaTemplatesArtifactBuilder, ToscaTemplatesArtifact> {

    ToscaTemplatesArtifactBuilder template(Template template);

    ToscaTemplatesArtifactBuilder templateFormat(String templateFormat);
}
