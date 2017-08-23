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
package com.huawei.openstack4j.openstack.internal;

import com.google.common.base.Function;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.model.common.ActionResponse;

/**
 * Base class for OpenStack services which use micro-versions.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public abstract class MicroVersionedOpenStackService extends BaseOpenStackService {
    private final MicroVersion microVersion;

    protected MicroVersionedOpenStackService(MicroVersion microVersion) {
        this.microVersion = microVersion;
    }

    protected MicroVersionedOpenStackService(ServiceType serviceType, MicroVersion microVersion) {
        super(serviceType);
        this.microVersion = microVersion;
    }

    protected MicroVersionedOpenStackService(ServiceType serviceType, MicroVersion microVersion,
                                             Function<String, String> endpointFunc) {
        super(serviceType, endpointFunc);
        this.microVersion = microVersion;
    }

    private MicroVersion getMicroVersion() {
        return microVersion;
    }

    protected abstract String getApiVersionHeader();

    @Override
    protected <R> Invocation<R> get(Class<R> returnType, String... path) {
        return super.get(returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<R> post(Class<R> returnType, String... path) {
        return super.post(returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<R> put(Class<R> returnType, String... path) {
        return super.put(returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<R> patch(Class<R> returnType, String... path) {
        return super.patch(returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<R> delete(Class<R> returnType, String... path) {
        return super.delete(returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<ActionResponse> deleteWithResponse(String... path) {
        return super.deleteWithResponse(path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<R> head(Class<R> returnType, String... path) {
        return super.head(returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }

    @Override
    protected <R> Invocation<R> request(HttpMethod method, Class<R> returnType, String path) {
        return super.request(method, returnType, path).header(getApiVersionHeader(), getMicroVersion().toString());
    }
}
