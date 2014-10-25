package org.openstack4j.openstack.storage.object.internal;

import org.openstack4j.api.storage.ObjectStorageAccountService;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.openstack.internal.BaseOpenStackService;
import org.openstack4j.openstack.storage.object.functions.ParseAccountFunction;

/**
 * The Object Storage Account based services
 * 
 * @author Jeremy Unruh
 */
public class ObjectStorageAccountServiceImpl extends BaseOpenStackService implements ObjectStorageAccountService {

    public ObjectStorageAccountServiceImpl() {
        super(ServiceType.OBJECT_STORAGE);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SwiftAccount get() {
        return ParseAccountFunction.INSTANCE.apply(head(Void.class, "").executeWithResponse());
    }

}
