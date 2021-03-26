package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.SubPort;

/**
 * A builder which creates a subport
 *
 * @author Kashyap Jha
 */
public interface SubPortBuilder extends Builder<SubPortBuilder, SubPort> {

    /**
     * Set the segmentation ID
     */
    SubPortBuilder segmentationId(int segmentationId);

    /**
     * Set the port ID
     */
    SubPortBuilder portId(String portId);

    /**
     * Set the segmentation type
     */
    SubPortBuilder segmentationType(String segmentationType);

}
