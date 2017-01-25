package org.openstack4j.model.artifact;

import org.openstack4j.openstack.artifact.domain.ToscaTemplates;

import java.util.List;

/**
 * Created by vadavi on 18-01-2017.
 */
public interface ToscaTemplatesArtifacts extends Artifacts {

    List<ToscaTemplates> getToscaTemplates();
}
