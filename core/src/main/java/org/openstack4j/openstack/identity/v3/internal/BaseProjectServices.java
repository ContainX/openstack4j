package org.openstack4j.openstack.identity.v3.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Base Project Operations Implementation is responsible for insuring the proper endpoint is used for all extending operation APIs
 *
 * @author chenyan
 */
public class BaseProjectServices extends BaseOpenStackService{
    protected BaseProjectServices() {
        super(ServiceType.IDENTITY, EnforceVersionToURL.instance("/v3"));
    }
}
