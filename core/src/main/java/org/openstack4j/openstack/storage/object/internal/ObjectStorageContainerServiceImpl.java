package org.openstack4j.openstack.storage.object.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.CONTENT_TYPE_DIRECTORY;
import static org.openstack4j.core.transport.ClientConstants.URI_SEP;
import static org.openstack4j.model.storage.object.SwiftHeaders.CONTAINER_METADATA_PREFIX;
import static org.openstack4j.model.storage.object.SwiftHeaders.CONTAINER_REMOVE_METADATA_PREFIX;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.Apis;
import org.openstack4j.api.exceptions.ContainerNotEmptyException;
import org.openstack4j.api.storage.ObjectStorageContainerService;
import org.openstack4j.api.storage.ObjectStorageObjectService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.model.storage.object.options.ContainerListOptions;
import org.openstack4j.model.storage.object.options.CreateUpdateContainerOptions;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.openstack.storage.object.domain.SwiftContainerImpl;
import org.openstack4j.openstack.storage.object.functions.MapWithoutMetaPrefixFunction;
import org.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;

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
    public List<? extends SwiftContainer> list(ContainerListOptions options) {
        if (options == null)
            return list();
        
        return toList(get(SwiftContainerImpl[].class).params(options.getOptions()).execute());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void create(String name) {
        create(name, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(String name, CreateUpdateContainerOptions options) {
        checkNotNull(name);
        
        put(Void.class, URI_SEP, name).headers(options != null ? options.getOptions() : null).execute();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createPath(String containerName, String path) {
        checkNotNull(containerName);
        checkNotNull(path);
        Payload<?> pl = Payloads.create(new ByteArrayInputStream(new byte[]{}));
        Apis.get(ObjectStorageObjectService.class).put(containerName, path, pl, 
                        ObjectPutOptions.create().contentType(CONTENT_TYPE_DIRECTORY));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String name, CreateUpdateContainerOptions options) {
        checkNotNull(name);
        
        if (options != null)
            post(Void.class, URI_SEP, name).headers(options.getOptions()).execute();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String name) {
        checkNotNull(name);
        HttpResponse resp = delete(Void.class, URI_SEP, name).executeWithResponse();
        if (resp.getStatus() == 409)
            throw new ContainerNotEmptyException(String.format("Container %s is not empty", name), 409);
        
        return (resp.getStatus() == 404 || resp.getStatus() == 204);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getMetadata(String name) {
        checkNotNull(name);
        HttpResponse resp = head(Void.class, URI_SEP, name).executeWithResponse();
        return MapWithoutMetaPrefixFunction.INSTANCE.apply(resp.headers());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateMetadata(String name, Map<String, String> metadata) {
        return invokeMetadata(name, CONTAINER_METADATA_PREFIX, metadata);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteMetadata(String name, Map<String, String> metadata) {
        return invokeMetadata(name, CONTAINER_REMOVE_METADATA_PREFIX, metadata);
    }
    
    private boolean invokeMetadata(String name, String prefix, Map<String, String> metadata) {
        checkNotNull(name);
        checkNotNull(metadata);
        
        return isResponseSuccess(post(Void.class, URI_SEP, name)
                                    .headers(MetadataToHeadersFunction.create(prefix).apply(metadata))
                                    .executeWithResponse(), 204);
    }
}
