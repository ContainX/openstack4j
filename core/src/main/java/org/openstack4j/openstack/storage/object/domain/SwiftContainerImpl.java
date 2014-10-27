package org.openstack4j.openstack.storage.object.domain;

import org.openstack4j.model.storage.object.SwiftContainer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Represents an OpenStack Swift Container which holds Objects
 * 
 * @author Jeremy Unruh
 */
public class SwiftContainerImpl implements SwiftContainer {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String name;
    
    @JsonProperty("count")
    private int objectCount;
    
    @JsonProperty("bytes")
    private long totalSize;
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getObjectCount() {
        return objectCount;
    }

    @Override
    public long getTotalSize() {
        return totalSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                   .add("name", name).add("count", objectCount).add("total size", totalSize)
                   .toString();
    }
}
