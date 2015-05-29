package org.openstack4j.model.common.resolvers;

import java.util.SortedSet;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.Access.Service;

/**
 * Resolves each service to the lowest version which we consider most stable and tested
 * 
 * @author Jeremy Unruh
 */
public final class StableServiceVersionResolver implements ServiceVersionResolver {

    public static final StableServiceVersionResolver INSTANCE = new StableServiceVersionResolver();
    
    private StableServiceVersionResolver() { 
    }
    
    @Override
    public Service resolve(ServiceType type, SortedSet<? extends Service> services) {
        return services.first();
    }

}
