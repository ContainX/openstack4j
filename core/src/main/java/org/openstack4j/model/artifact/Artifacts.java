package org.openstack4j.model.artifact;

import org.openstack4j.model.ModelEntity;

import java.util.List;

/**
 * A Glare list of Artifacts
 *
 * @author Pavan Vadavi
 */
public interface Artifacts extends ModelEntity {

    ArtifactType artifactType = ArtifactType.ALL;

    public String getSchema();

    public String getFirst();

}
