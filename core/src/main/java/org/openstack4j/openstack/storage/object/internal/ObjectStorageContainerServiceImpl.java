package org.openstack4j.openstack.storage.object.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.storage.ObjectStorageContainerService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.openstack.storage.object.domain.SwiftContainerImpl;
import org.openstack4j.openstack.storage.object.domain.SwiftHeaders;
import org.openstack4j.openstack.storage.object.functions.MapWithoutMetaPrefixFunction;

/**
 * Provides access to the OpenStack Object Storage (Swift) Container API features.
 * 
 * @author Jeremy Unruh
 */
public class ObjectStorageContainerServiceImpl extends BaseObjectStorageService implements ObjectStorageContainerService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SwiftContainer> list() {
          return toList(get(SwiftContainerImpl[].class).execute());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getMetadata(String name) {
        checkNotNull(name);
        HttpResponse resp = head(Void.class, "/", name).executeWithResponse();
        return MapWithoutMetaPrefixFunction.INSTANCE.apply(resp.headers());
    }

    @Override
    public void create(String name) {
        create(name, null);
    }

    @Override
    public void create(String name, Map<String, String> metadata) {
        Invocation<Void> invocation = put(Void.class, "/", name);
        if (metadata != null)
            applyMetaData(SwiftHeaders.CONTAINER_METADATA_PREFIX, metadata, invocation.getRequest());
        
        invocation.execute();
    }

}
