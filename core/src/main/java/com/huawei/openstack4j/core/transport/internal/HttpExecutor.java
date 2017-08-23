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
package com.huawei.openstack4j.core.transport.internal;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.exceptions.ConnectorNotFoundException;
import com.huawei.openstack4j.core.transport.HttpExecutorService;
import com.huawei.openstack4j.core.transport.HttpRequest;
import com.huawei.openstack4j.core.transport.HttpResponse;

/**
 * HttpExecutor is a delegate to the underline connector associated to OpenStack4j.
 *
 * @author Jeremy Unruh
 */
public class HttpExecutor  {

    private static final Logger LOG = LoggerFactory.getLogger(HttpExecutor.class);
    private static final HttpExecutor INSTANCE = new HttpExecutor();
    private HttpExecutorService service;

    private HttpExecutor() {}

    private HttpExecutorService service() {
        if (service != null) return service;

        Iterator<HttpExecutorService> it = ServiceLoader.load(HttpExecutorService.class, getClass().getClassLoader()).iterator();
        if (!it.hasNext())
        {
            LOG.error("No OpenStack4j connector found in classpath");
            throw new ConnectorNotFoundException("No OpenStack4j connector found in classpath");
        }
        return service = it.next();
    }

    public static HttpExecutor create() {
        return INSTANCE;
    }

    public String getExecutorName() {
        return service().getExecutorDisplayName();
    }

    /**
     * Delegate to {@link HttpExecutorService#execute(HttpRequest)}
     */
    public <R> HttpResponse execute(HttpRequest<R> request) {
        LOG.debug("Executing Request: {} -> {}", request.getEndpoint(), request.getPath());
        return service().execute(request);
    }
}
