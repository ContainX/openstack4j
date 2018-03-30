package org.openstack4j.model.magnum;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;

public interface Pod extends ModelEntity, Buildable<PodBuilder> {
    /**
     * Gets id
     * 
     * @return id
     */
    String getId();

    /**
     * Gets uuid
     * 
     * @return uuid
     */
    String getUuid();

    /**
     * Gets name
     * 
     * @return name
     */
    String getName();

    /**
     * Gets desc
     * 
     * @return desc
     */
    String getDesc();

    /**
     * Gets bayUuid
     * 
     * @return bayUuid
     */
    String getBayUuid();

    /**
     * Gets images
     * 
     * @return images
     */
    List<String> getImages();

    /**
     * Gets labels
     * 
     * @return labels
     */
    Label getLabels();

    /**
     * Gets status
     * 
     * @return status
     */
    String getStatus();

}
