package org.openstack4j.openstack.storage.object.domain;

import static org.openstack4j.core.transport.ClientConstants.CONTENT_TYPE_DIRECTORY;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.openstack4j.api.Apis;
import org.openstack4j.api.storage.ObjectStorageObjectService;
import org.openstack4j.model.storage.object.SwiftObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Represents an Object which is a File or Directory within a Container
 * 
 * @author Jeremy Unruh
 */
public class SwiftObjectImpl implements SwiftObject {

    private static final long serialVersionUID = 1L;

    @JsonProperty("hash")
    private String md5sum;
    @JsonProperty("last_modified")
    private Date lastModified;
    @JsonProperty("bytes")
    private long sizeBytes;
    @JsonProperty
    private String name;
    @JsonProperty("content_type")
    private String mimeType;
    
    @JsonIgnore
    private Map<String, String> metadata;
    
    @JsonIgnore
    private String containerName;
    
    @Override
    public String getMD5Checksum() {
        return md5sum;
    }

    @Override
    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public long getSizeInBytes() {
        return sizeBytes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }
    
    @Override
    public boolean isDirectory() {
        return CONTENT_TYPE_DIRECTORY.equals(mimeType);
    }
    
    @Override
    public String getContainerName() {
        return containerName;
    }
    
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }
    
    @Override
    public Map<String, String> getMetadata() {
        if (metadata == null)
            metadata = Apis.get(ObjectStorageObjectService.class).getMetadata(containerName, name);
        return metadata;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                 .add("name", name).add("last_modified", lastModified).add("mimeType", mimeType)
                 .add("size_bytes", sizeBytes).add("md5_checksum", md5sum).add("directory", isDirectory())
                 .add("containerName", containerName)
                 .toString();
    }

    public static class SwiftObjects extends ArrayList<SwiftObjectImpl> {
        private static final long serialVersionUID = 1L;
    }
}
