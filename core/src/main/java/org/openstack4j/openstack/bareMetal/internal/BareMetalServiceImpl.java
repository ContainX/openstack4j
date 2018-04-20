package org.openstack4j.openstack.bareMetal.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.bareMetal.*;

/**
 * Bare Metal (Ironic) Operations API implementation
 *
 * @author zhangliang
 */
public class BareMetalServiceImpl extends BaseBareMetalServices implements BareMetalService {

    @Override
    public ChassisService chassis() {
        return Apis.get(ChassisService.class);
    }

    @Override
    public DriverService drivers() {
        return Apis.get(DriverService.class);
    }

    @Override
    public NodeService nodes() {
        return Apis.get(NodeService.class);
    }

    @Override
    public PortService ports() {
        return Apis.get(PortService.class);
    }
}
