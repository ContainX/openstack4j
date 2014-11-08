package org.openstack4j.openstack.storage.object.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.model.storage.object.SwiftHeaders.ETAG;

import java.util.List;

import org.openstack4j.api.storage.ObjectStorageObjectService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.payloads.FilePayload;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.ObjectListOptions;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.openstack.storage.object.domain.SwiftObjectImpl;
import org.openstack4j.openstack.storage.object.domain.SwiftObjectImpl.SwiftObjects;
import org.openstack4j.openstack.storage.object.functions.ApplyContainerToObjectFunction;

import com.google.common.collect.Lists;

/**
 * A service responsible for maintaining directory and file objects within containers for
 * an Object Service within OpenStack
 * 
 * @author Jeremy Unruh
 */
public class ObjectStorageObjectServiceImpl extends BaseObjectStorageService implements ObjectStorageObjectService {

    @Override
    public List<? extends SwiftObject> list(String containerName) {
        checkNotNull(containerName);
        return get(SwiftObjects.class, uri("/%s", containerName)).execute();
    }

    @Override
    public List<? extends SwiftObject> list(String containerName, ObjectListOptions options) {
        if (options == null)
            return list(containerName);
        
        checkNotNull(containerName);
        
        List<SwiftObjectImpl> objs = get(SwiftObjects.class, uri("/%s", containerName)).params(options.getOptions()).execute();
        return Lists.transform(objs, ApplyContainerToObjectFunction.create(containerName));
                
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String put(String containerName, String name, Payload<?> payload) {
        return put(containerName, name, payload, ObjectPutOptions.NONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String put(String containerName, String name, Payload<?> payload, ObjectPutOptions options) {
        checkNotNull(containerName);
        checkNotNull(payload);
        checkNotNull(options);

        if (FilePayload.class.isAssignableFrom(payload.getClass()) && name == null)
            name = FilePayload.class.cast(payload).getRaw().getName();
        else
            checkNotNull(name);
        
        
        if (options.getPath() != null && name.indexOf('/') == -1)
            name = options.getPath() + "/" + name;
        
        HttpResponse resp = put(Void.class, uri("/%s/%s", containerName, name))
                              .entity(payload)
                              .headers(options.getOptions())
                              .contentType(options.getContentType())
                              .executeWithResponse();
        return resp.header(ETAG);
    }

    @Override
    public void delete(String containerName, String name) {
        checkNotNull(containerName);
        checkNotNull(name);
        
        delete(Void.class, uri("/%s/%s", containerName, name)).execute();
    }
}
