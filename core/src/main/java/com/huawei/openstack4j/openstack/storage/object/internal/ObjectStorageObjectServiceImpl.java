/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.storage.object.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.HttpEntityHandler.*;
import static com.huawei.openstack4j.model.storage.object.SwiftHeaders.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.api.storage.ObjectStorageObjectService;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.DLPayload;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.common.payloads.FilePayload;
import com.huawei.openstack4j.model.storage.block.options.DownloadOptions;
import com.huawei.openstack4j.model.storage.object.SwiftObject;
import com.huawei.openstack4j.model.storage.object.options.ObjectDeleteOptions;
import com.huawei.openstack4j.model.storage.object.options.ObjectListOptions;
import com.huawei.openstack4j.model.storage.object.options.ObjectLocation;
import com.huawei.openstack4j.model.storage.object.options.ObjectPutOptions;
import com.huawei.openstack4j.openstack.common.DLPayloadEntity;
import com.huawei.openstack4j.openstack.common.functions.HeaderNameValuesToHeaderMap;
import com.huawei.openstack4j.openstack.storage.object.domain.SwiftObjectImpl;
import com.huawei.openstack4j.openstack.storage.object.domain.SwiftObjectImpl.SwiftObjects;
import com.huawei.openstack4j.openstack.storage.object.functions.ApplyContainerToObjectFunction;
import com.huawei.openstack4j.openstack.storage.object.functions.MapWithoutMetaPrefixFunction;
import com.huawei.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;
import com.huawei.openstack4j.openstack.storage.object.functions.ParseObjectFunction;

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
        List<SwiftObjectImpl> objs = get(SwiftObjects.class, uri("/%s", containerName)).param("format", "json").execute();
        
        if (objs == null) {
            return Collections.emptyList();
        }
        
        return Lists.transform(objs, ApplyContainerToObjectFunction.create(containerName));
    }

    @Override
    public List<? extends SwiftObject> list(String containerName, ObjectListOptions options) {
        if (options == null)
            return list(containerName);
        
        checkNotNull(containerName);
        
        List<SwiftObjectImpl> objs = get(SwiftObjects.class, uri("/%s", containerName)).param("format", "json").params(options.getOptions()).execute();
        if (objs == null) {
            return Collections.emptyList();
        }
        return Lists.transform(objs, ApplyContainerToObjectFunction.create(containerName));
                
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SwiftObject get(ObjectLocation location) {
        checkNotNull(location);

        HttpResponse resp = head(Void.class, location.getURI()).executeWithResponse();
        try
        {
            if (resp.getStatus() == 404)
                return null;
            
            return ParseObjectFunction.create(location).apply(resp);
        }
        finally {
            closeQuietly(resp);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SwiftObject get(String containerName, String name) {
        return get(ObjectLocation.create(containerName, name));
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
        checkNotNull(options);

        if (payload != null && FilePayload.class.isAssignableFrom(payload.getClass()) && name == null)
            name = FilePayload.class.cast(payload).getRaw().getName();
        else
            checkNotNull(name);
        
        
        if (options.getPath() != null && name.indexOf('/') == -1)
            name = options.getPath() + "/" + name;
        
        HttpResponse resp = put(Void.class, uri("/%s/%s", containerName, name))
                              .entity(payload)
                              .headers(options.getOptions())
                              .contentType(options.getContentType())
                              .paramLists(options.getQueryParams())
                              .executeWithResponse();
        try
        {
            return resp.header(ETAG);
        }
        finally {
           closeQuietly(resp);
        }
    }

    @Override
    public ActionResponse delete(String containerName, String name) {
        checkNotNull(containerName);
        checkNotNull(name);
        
       return delete(ObjectLocation.create(containerName, name));
    }

    @Override
    public ActionResponse delete(ObjectLocation location) {
        return delete(location, ObjectDeleteOptions.NONE);
    }

    @Override
    public ActionResponse delete(ObjectLocation location, ObjectDeleteOptions options) {
        checkNotNull(location);
        checkNotNull(options);
        return delete(ActionResponse.class, location.getURI())
            .paramLists(options.getQueryParams())
            .execute();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String copy(ObjectLocation source, ObjectLocation dest) {
        checkNotNull(source);
        checkNotNull(dest);

        HttpResponse resp = put(Void.class, dest.getURI())
                                .header(X_COPY_FROM, source.getURI())
                                .header(CONTENT_LENGTH, 0)
                                .executeWithResponse();
        try
        {
            return resp.header(ETAG);
        }
        finally {
            closeQuietly(resp);
        }
    }

    @Override
    public Map<String, String> getMetadata(ObjectLocation location) {
        checkNotNull(location);

        HttpResponse resp = head(Void.class, location.getURI()).executeWithResponse();
        try
        {
            return MapWithoutMetaPrefixFunction.INSTANCE.apply(resp.headers());
        }
        finally {
            closeQuietly(resp);
        }
    }

    @Override
    public Map<String, String> getMetadata(String containerName, String name) {
        checkNotNull(containerName);
        checkNotNull(name);
        return getMetadata(ObjectLocation.create(containerName, name));
    }

    @Override
    public boolean updateMetadata(ObjectLocation location, Map<String, String> metadata) {
        checkNotNull(location);
        checkNotNull(metadata);

        //the successfull response state of updateMetadata is 202 instead of 204
        //I test it by curl and this api
        return isResponseSuccess(post(Void.class, location.getURI())
                  .headers(MetadataToHeadersFunction.create(OBJECT_METADATA_PREFIX).apply(metadata))
                  .executeWithResponse(), 202);
    }

    @Override
    public DLPayload download(String containerName, String name) {
        return download(ObjectLocation.create(containerName, name), DownloadOptions.create());
    }

    @Override
    public DLPayload download(String containerName, String name, DownloadOptions options) {
        checkNotNull(containerName);
        checkNotNull(name);
        checkNotNull(options);
        
        return download(ObjectLocation.create(containerName, name), options);
    }

    @Override
    public DLPayload download(ObjectLocation location, DownloadOptions options) {
        checkNotNull(location);
        checkNotNull(options);
        
        return DLPayloadEntity.create(
                  get(Void.class, location.getURI())
                    .headers(HeaderNameValuesToHeaderMap.INSTANCE.apply(options.getHeaders()))
                    .executeWithResponse()
               );
    }
}
