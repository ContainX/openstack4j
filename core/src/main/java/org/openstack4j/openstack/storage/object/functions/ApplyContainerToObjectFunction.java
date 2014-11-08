package org.openstack4j.openstack.storage.object.functions;

import org.openstack4j.openstack.storage.object.domain.SwiftObjectImpl;

import com.google.common.base.Function;

/**
 * Applies a container name specified through the function creation to a Swift Object
 * 
 * @author Jeremy Unruh
 *
 */
public class ApplyContainerToObjectFunction implements Function<SwiftObjectImpl, SwiftObjectImpl>{

    String containerName;
    
    private ApplyContainerToObjectFunction(String containerName) {
        this.containerName = containerName;
    }
    
    public static ApplyContainerToObjectFunction create(String containerName) {
        return new ApplyContainerToObjectFunction(containerName);
    }
    
    @Override
    public SwiftObjectImpl apply(SwiftObjectImpl obj) {
        obj.setContainerName(containerName);
        return obj;
    }

}
