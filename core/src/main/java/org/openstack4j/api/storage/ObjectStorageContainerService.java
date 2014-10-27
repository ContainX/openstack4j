package org.openstack4j.api.storage;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.storage.object.SwiftContainer;

/**
 * Provides access to the OpenStack Object Storage (Swift) Container API features.
 * 
 * @author Jeremy Unruh
 */
public interface ObjectStorageContainerService extends RestService {

    /**
     * Listing of all containers associated with the Account (max result size is 10,000);
     * 
     * @return List of containers ordered by name
     */
    List<? extends SwiftContainer> list();
    
    /**
     * Creates a new container with the specified {@code name}
     * 
     * @param name the name of the new container
     */
    void create(String name);
    
    /**
     * Creates a new container with the specified {@code name} with metadata
     * 
     * @param name the name of the new container
     * @param metadata metadata to be stored with the new container
     */
    void create(String name, Map<String, String> metadata);
    
    /**
     * Gets the Metadata for a given container
     * 
     * @param name the container name
     * @return Map of key to value metadata
     */
    Map<String, String> getMetadata(String name);
    
}
