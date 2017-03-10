package org.openstack4j.openstack.storage.block.internal;

import java.util.List;

import org.openstack4j.api.storage.ext.BlockStroageServicesService;
import org.openstack4j.model.storage.block.ext.Service;
import org.openstack4j.openstack.storage.block.domain.ext.ExtService.Services;


/**
 * Block Storage Services service provides CRUD capabilities for Cinder service(s).
 *
 * @author Taemin
 */
public class BlockStorageServicesServiceImpl extends BaseBlockStorageServices implements BlockStroageServicesService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Service> list() {
        return get(Services.class, uri("/os-services")).execute().getList();
    }

}