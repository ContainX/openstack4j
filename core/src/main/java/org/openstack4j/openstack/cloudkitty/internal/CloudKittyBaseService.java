package org.openstack4j.openstack.cloudkitty.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class CloudKittyBaseService extends BaseOpenStackService {

    protected static final String PATH_HASHMAP = "/rating/module_config/hashmap";
    protected static final String PATH_PYSCRIPTS = "/rating/module_config/pyscripts";

    public CloudKittyBaseService() {
        super(ServiceType.RATING, EnforceVersionToURL.instance("/v1"));
    }
}
