package org.openstack4j.openstack.image.v2;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseImageServices extends BaseOpenStackService {

    protected BaseImageServices() {
        super(ServiceType.IMAGE, EnforceVersionToURL.instance("/v2", true));
    }
}
