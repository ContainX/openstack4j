package org.openstack4j.api.storage;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.ObjectListOptions;
import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;

/**
 * A service responsible for maintaining directory and file objects within containers for
 * an Object Service within OpenStack
 * 
 * @author Jeremy Unruh
 */
public interface ObjectStorageObjectService extends RestService {

    /**
     * Lists all objects for the given container name
     * 
     * @param containerName the container name
     * @return List of File objects including Directories
     */
    List<? extends SwiftObject> list(String containerName);
    
    /**
     * Lists all objects for the given container name and list options
     * 
     * @param containerName the container name
     * @param options additional filter based options
     * @return List of File objects including Directories
     */
    List<? extends SwiftObject> list(String containerName, ObjectListOptions options);
    
    /**
     * Adds/Updates a file to the specified container
     * 
     * @param containerName the container name
     * @param name the name of the file
     * @param payload the file payload
     * @return the ETAG checksum
     */
    String put(String containerName, String name, Payload<?> payload);
    
    /**
     * Adds/Updates a file to the specified container
     * 
     * @param containerName the container name
     * @param name the name of the file
     * @param payload the file payload
     * @param options extended options to associate with this object
     * @return the ETAG checksum
     */
    String put(String containerName, String name, Payload<?> payload, ObjectPutOptions options);
    
    /**
     * Deletes an Object from the specified container
     * 
     * @param containerName the container name
     * @param name the name of the object (full path if directories are used)
     */
    void delete(String containerName, String name);
    
    /**
     * Deletes an Object from the specified container
     * 
     * @param location location containing container name and object name
     */
    void delete(ObjectLocation location);
    
    /**
     * Copies an object to another object in the object store
     * 
     * @param source the source container and object name
     * @param dest the destination container and object name
     * @return the ETAG checksum if successful
     */
    String copy(ObjectLocation source, ObjectLocation dest);
    
    /**
     * Gets the metadata for the specified object location
     * @param location the object location
     * @return Map of Key to Value metadata
     */
    Map<String, String> getMetadata(ObjectLocation location);
    
    /**
     * See {@link #getMetadata(ObjectLocation)} 
     * 
     * @param containerName the container name
     * @param name the object name
     * @return Map of Key to Value metadata
     */
    Map<String, String> getMetadata(String containerName, String name);
    
    /**
     * Creates or Updates the given metadata for the specified object location
     * 
     * @param location the location of the object to create/update metadata
     * @param metadata the metadata
     * @return true if successful
     */
    boolean updateMetadata(ObjectLocation location, Map<String, String> metadata);

}
