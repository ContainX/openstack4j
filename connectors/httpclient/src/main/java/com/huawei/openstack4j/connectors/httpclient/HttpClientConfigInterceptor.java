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
package com.huawei.openstack4j.connectors.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

import com.huawei.openstack4j.core.transport.Config;

/**
 * Allows for interception during the creation of a new HttpClient.  To register a custom singleton interceptor you must invoke
 * {@link HttpClientFactory#registerInterceptor(HttpClientConfigInterceptor)}
 * 
 * @author Jeremy Unruh
 */
public interface HttpClientConfigInterceptor {

    /**
     * This method is invoked prior to the HttpClientBuilder build is called allowing any overrides or custom configuration. 
     * 
     * @param client the http client builder
     * @param requestConfig the request config builder
     * @param config the openstack4j config
     */
    void onClientCreate(HttpClientBuilder client, RequestConfig.Builder requestConfig, Config config);
}
