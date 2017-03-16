package org.openstack4j.model.magnum;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.GenericLink;

public interface Bay extends ModelEntity, Buildable<BayBuilder> {
    /**
     * Gets status
     * @return status
     */
    String getStatus();


    /**
     * Gets uuid
     * @return uuid
     */
    String getUuid();


    /**
     * Gets links
     * @return links
     */
    List<GenericLink> getLinks();


    /**
     * Gets stackId
     * @return stackId
     */
    String getStackId();


    /**
     * Gets masterCount
     * @return masterCount
     */
    Integer getMasterCount();


    /**
     * Gets baymodelId
     * @return baymodelId
     */
    String getBaymodelId();


    /**
     * Gets nodeCount
     * @return nodeCount
     */
    Integer getNodeCount();


    /**
     * Gets bayCreateTimeout
     * @return bayCreateTimeout
     */
    String getBayCreateTimeout();


    /**
     * Gets name
     * @return name
     */
    String getName();


}
