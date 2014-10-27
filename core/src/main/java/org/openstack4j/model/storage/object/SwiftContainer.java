package org.openstack4j.model.storage.object;

import org.openstack4j.model.ModelEntity;

/**
 * Represents an OpenStack Swift Container which holds Objects
 * 
 * @author Jeremy Unruh
 */
public interface SwiftContainer extends ModelEntity {

    /**
     * The name of the Container
     * 
     * @return the container name
     */
    String getName();

    /**
     * The current object count for this container
     * 
     * @return the number of objects
     */
    int getObjectCount();

    /**
     * The total size of all the objects within this contain in bytes
     * 
     * @return total size in bytes
     */
    long getTotalSize();
}
