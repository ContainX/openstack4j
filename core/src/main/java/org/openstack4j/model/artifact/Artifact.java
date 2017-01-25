package org.openstack4j.model.artifact;

import java.util.List;

/**
 * A Glare Artifact
 *
 * @author Pavan Vadavi
 */
public interface Artifact {

    String getDescription();

    List<Object> getTags();

    Metadata getMetadata();

    List<Object> getRelease();

    String getOwner();

    Object getSupportedBy();

    Object getLicenseUrl();

    String getVersion();

    Object getProvidedBy();

    String getVisibility();

    String getUpdatedAt();

    String getActivatedAt();

    String getCreatedAt();

    Object getLicense();

    Object getIcon();

    String getStatus();
}
