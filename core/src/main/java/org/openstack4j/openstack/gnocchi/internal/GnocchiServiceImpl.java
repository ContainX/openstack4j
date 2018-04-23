package org.openstack4j.openstack.gnocchi.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.gnocchi.GnocchiService;
import org.openstack4j.api.gnocchi.ResourceService;

/**
 * gnocchi Operation API implementation
 *
 * @author zhangliang
 */
public class GnocchiServiceImpl implements GnocchiService {
    @Override
    public ResourceService resource() {
        return Apis.get(ResourceService.class);
    }
}
