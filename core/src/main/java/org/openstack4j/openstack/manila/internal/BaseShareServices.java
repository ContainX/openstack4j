package org.openstack4j.openstack.manila.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseShareServices extends BaseOpenStackService {
    protected BaseShareServices() {
        super(ServiceType.SHARE);
    }
}
