package org.openstack4j.openstack.bareMetal.internal;

import org.openstack4j.api.bareMetal.DriverService;
import org.openstack4j.model.bareMetal.Driver;
import org.openstack4j.openstack.bareMetal.domain.BareMetalDriver;

import java.util.List;
import java.util.Map;

/**
 * Driver Operation API implementation
 *
 * @author zhangliang
 */
public class DriverServiceImpl extends BaseBareMetalServices implements DriverService {
    @Override
    public List<? extends Driver> list() {
        return get(BareMetalDriver.Drivers.class, uri("/drivers")).execute().getList();
    }

    @Override
    public Map<String, String> getProperties(String driverName) {
        return get(Map.class, uri("/drivers/%s/properties", driverName)).execute();
    }
}
