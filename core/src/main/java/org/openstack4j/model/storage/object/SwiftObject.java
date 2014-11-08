package org.openstack4j.model.storage.object;

import java.util.Date;
import java.util.Map;

import org.openstack4j.model.ModelEntity;

/**
 * Represents an Object which is a File or Directory within a Container
 * 
 * @author Jeremy Unruh
 */
public interface SwiftObject extends ModelEntity {
    
    /**
     * The MD5 checksum value of the object content.
     * 
     * @return the MD5 checksum
     */
    String getMD5Checksum();
    
    /**
     * The date and time when the object was last modified.
     * 
     * @return the last modified date
     */
    Date getLastModified();
    
    /**
     * The total number of bytes that are stored in Object Storage for the account
     * 
     * @return total number of bytes
     */
    long getSizeInBytes();
    
    /**
     * The name of the object
     * 
     * @return the name of the object
     */
    String getName();
    
    /**
     * The content type of the object
     * 
     * @return the content type
     */
    String getMimeType();
    
    /**
     * @return the container name this object belongs to
     */
    String getContainerName();
    
    /**
     * Determines if this object is a pseudo directory
     * 
     * @return true if this is a directory
     */
    boolean isDirectory();
    
    /**
     * Gets the object metadata  (this is a lazy fetch)
     * 
     * @return the metadata for this object
     */
    Map<String, String> getMetadata();
}
