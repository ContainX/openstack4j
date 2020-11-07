package org.openstack4j.model.artifact;

import org.openstack4j.model.ModelEntity;

/**
 * A Glare Artifact Template
 *
 * @author Pavan Vadavi
 */
public interface Template extends ModelEntity {

    String getMd5();

    String getSha256();

    String getContentType();

    Boolean getExternal();

    String getUrl();

    String getSha1();

    String getStatus();
}
