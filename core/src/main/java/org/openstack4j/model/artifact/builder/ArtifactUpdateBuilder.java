package org.openstack4j.model.artifact.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.artifact.ArtifactUpdate;

/**
 * Created by vadavi on 20-01-2017.
 */
public interface ArtifactUpdateBuilder extends Buildable.Builder<ArtifactUpdateBuilder, ArtifactUpdate> {

    ArtifactUpdateBuilder op(String op);

    ArtifactUpdateBuilder path(String path);

    ArtifactUpdateBuilder value(String value);
}
