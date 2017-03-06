package org.openstack4j.openstack.compute.internal;

import java.util.List;

import org.openstack4j.api.compute.ext.ServicesService;
import org.openstack4j.model.compute.ext.Service;
import org.openstack4j.openstack.compute.domain.ext.ExtService.Services;

/**
 * Compute Services service provides CRUD capabilities for nova service(s).
 *
 * @author Stephan Latour
 */
public class ServicesServiceImpl extends BaseComputeServices implements ServicesService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Service> list() {
        return get(Services.class, uri("/os-services")).execute().getList();
    }

}