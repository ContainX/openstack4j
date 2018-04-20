package org.openstack4j.openstack.bareMetal.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * base class for bareMetal
 *
 * @author zhangliang
 */
public class BaseBareMetalServices extends BaseOpenStackService {

    protected BaseBareMetalServices() {
        super(ServiceType.BAREMETAL, EnforceVersionToURL.instance("/v1"));
    }

}
