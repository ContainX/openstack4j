package org.openstack4j.openstack.storage.object.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.storage.ObjectStorageAccountService;
import org.openstack4j.api.storage.ObjectStorageService;

/**
 * OpenStack Object Storage service implementation
 * 
 * @author Jeremy Unruh
 */
public class ObjectStorageServiceImpl implements ObjectStorageService {

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectStorageAccountService account() {
        return Apis.get(ObjectStorageAccountService.class);
    }

}
