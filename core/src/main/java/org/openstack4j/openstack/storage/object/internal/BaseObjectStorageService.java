package org.openstack4j.openstack.storage.object.internal;

import java.util.Map;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.openstack.internal.BaseOpenStackService;
import org.openstack4j.openstack.storage.object.domain.MetaHeaderRequestWrapper;
import org.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;

/**
 * Base OpenStack Storage Service
 * 
 * @author Jeremy Unruh
 */
public class BaseObjectStorageService extends BaseOpenStackService {

    public BaseObjectStorageService() {
        super(ServiceType.OBJECT_STORAGE);
    }
    
    protected <R> void applyMetaData(String prefix, Map<String, String> metadata, HttpRequest<R> req) {
        MetaHeaderRequestWrapper<R> wrapper = MetaHeaderRequestWrapper.of(prefix, metadata, req);
        MetadataToHeadersFunction.<R>create().apply(wrapper);
    }
}
