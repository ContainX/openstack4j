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
package com.huawei.openstack4j.connectors.resteasy.executors;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

import com.huawei.openstack4j.core.transport.Config;

/**
 * Default Apache HttpClient based Executor
 * 
 * @author Jeremy Unruh
 */
public class ApacheHttpClientExecutor extends ApacheHttpClient4Executor {

    ApacheHttpClientExecutor(HttpClient client) {
        super(client);
    }
    
    public static ApacheHttpClientExecutor create(Config config) {
        
        HttpParams params = new BasicHttpParams();
        if (config.getReadTimeout() > 0)
            HttpConnectionParams.setSoTimeout(params, config.getReadTimeout());
        
        if (config.getConnectTimeout() > 0)
            HttpConnectionParams.setConnectionTimeout(params, config.getConnectTimeout());
        
        HttpClient client = new DefaultHttpClient(params);
        
        if (config.getProxy() != null) {
            try {
                URL url = new URL(config.getProxy().getHost());
                HttpHost proxy = new HttpHost(url.getHost(), config.getProxy().getPort(), url.getProtocol());
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        
        return new ApacheHttpClientExecutor(client);
    }
    
}
