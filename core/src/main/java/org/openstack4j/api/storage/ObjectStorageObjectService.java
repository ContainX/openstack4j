package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.ObjectListOptions;
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
}
