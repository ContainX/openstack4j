package org.openstack4j.model.common.resolvers;

import java.util.SortedSet;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.Access.Service;

/**
 * Resolves the service version to the latest version found within the Service Catalog
 * 
 * @author Jeremy Unruh
 */
public final class LatestServiceVersionResolver implements ServiceVersionResolver {

    public static final LatestServiceVersionResolver INSTANCE = new LatestServiceVersionResolver();
    
    private LatestServiceVersionResolver() {
        
    }
    
    @Override
    public Service resolve(ServiceType type, SortedSet<? extends Service> services) {
        return services.last();
    }

}
