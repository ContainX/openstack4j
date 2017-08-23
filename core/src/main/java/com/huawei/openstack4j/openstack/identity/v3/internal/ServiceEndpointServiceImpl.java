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
package com.huawei.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.net.URL;
import java.util.List;

import com.huawei.openstack4j.api.identity.v3.ServiceEndpointService;
import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Endpoint;
import com.huawei.openstack4j.model.identity.v3.Service;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneEndpoint;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneService;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneEndpoint.Endpoints;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneService.Services;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
/**
 *
 * implementation of v3 service manager service
 *
 */
public class ServiceEndpointServiceImpl extends BaseOpenStackService implements ServiceEndpointService {

    @Override
    public Service create(Service service) {
        checkNotNull(service);
        return post(KeystoneService.class, uri(PATH_SERVICES)).entity(service).execute();
    }

    @Override
    public Service create(String type, String name, String description, boolean enabled) {
        checkNotNull(type);
        checkNotNull(name);
        checkNotNull(description);
        checkNotNull(enabled);
        return create(KeystoneService.builder().type(type).name(name).description(description).enabled(enabled).build());
    }

    @Override
    public Service get(String serviceId) {
        checkNotNull(serviceId);
        return get(KeystoneService.class, PATH_SERVICES, "/", serviceId).execute();
    }

    @Override
    public Service update(Service service) {
        checkNotNull(service);
        return patch(KeystoneService.class, PATH_SERVICES, "/", service.getId()).entity(service).execute();
    }

    @Override
    public ActionResponse delete(String serviceId) {
        checkNotNull(serviceId);
        return deleteWithResponse(PATH_SERVICES, "/", serviceId).execute();
    }

    @Override
    public List<? extends Service> list() {
        return get(Services.class, uri(PATH_SERVICES)).execute().getList();
    }

    @Override
    public List<? extends Endpoint> listEndpoints() {
        return get(Endpoints.class, uri(PATH_ENDPOINTS)).execute().getList();
    }

    @Override
    public Endpoint createEndpoint(Endpoint endpoint) {
        checkNotNull(endpoint);
        return post(KeystoneEndpoint.class, uri(PATH_ENDPOINTS)).entity(endpoint).execute();
    }

    @Override
    public Endpoint createEndpoint(String name, URL url, Facing iface, String regionId, String serviceId, boolean enabled) {
        checkNotNull(name);
        checkNotNull(url);
        checkNotNull(iface);
        checkNotNull(regionId);
        checkNotNull(serviceId);
        checkNotNull(enabled);
        return createEndpoint(KeystoneEndpoint.builder().name(name).url(url).iface(iface).regionId(regionId).serviceId(serviceId).enabled(enabled).build());
    }

    @Override
    public Endpoint getEndpoint(String endpointId) {
        checkNotNull(endpointId);
        return get(KeystoneEndpoint.class, PATH_ENDPOINTS, "/", endpointId).execute();
    }

    @Override
    public Endpoint updateEndpoint(Endpoint endpoint) {
        checkNotNull(endpoint);
        return patch(KeystoneEndpoint.class, PATH_ENDPOINTS, "/", endpoint.getId()).entity(endpoint).execute();
    }

    @Override
    public ActionResponse deleteEndpoint(String endpointId) {
        checkNotNull(endpointId);
        return deleteWithResponse(PATH_ENDPOINTS, "/", endpointId).execute();
    }

}
