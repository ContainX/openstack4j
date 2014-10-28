package org.openstack4j.openstack.storage.object.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Base OpenStack Storage Service
 * 
 * @author Jeremy Unruh
 */
public class BaseObjectStorageService extends BaseOpenStackService {

    public BaseObjectStorageService() {
        super(ServiceType.OBJECT_STORAGE);
    }
    
    protected boolean isResponseSuccess(HttpResponse res, int status) {
        return res.getStatus() == status;
    }
}
