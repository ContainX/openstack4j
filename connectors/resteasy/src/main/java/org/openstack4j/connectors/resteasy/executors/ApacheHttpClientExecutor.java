package org.openstack4j.connectors.resteasy.executors;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.openstack4j.core.transport.Config;

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
        
        return new ApacheHttpClientExecutor(client);
    }
    
}
