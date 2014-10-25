package org.openstack4j.api.storage;

import org.openstack4j.common.RestService;
import org.openstack4j.model.storage.object.SwiftAccount;

/**
 * The Object Storage Account based services
 * 
 * @author Jeremy Unruh
 */
public interface ObjectStorageAccountService extends RestService {

    /**
     * Gets the {@link SwiftAccount}.
     *
     * @return The {@link SwiftAccount} object.
     */
    SwiftAccount get();
    
}
