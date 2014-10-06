package org.openstack4j.core.transport.internal;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.openstack4j.api.exceptions.ConnectorNotFoundException;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;

/**
 * HttpExecutor is a delegate to the underline connector associated to OpenStack4j.
 * 
 * @author Jeremy Unruh
 */
public class HttpExecutor  {

    private static final HttpExecutor INSTANCE = new HttpExecutor();
    private HttpExecutorService service;

    private HttpExecutor() { 
        initService();
    }

    private void initService() {
        Iterator<HttpExecutorService> it = ServiceLoader.load(HttpExecutorService.class).iterator();
        if (!it.hasNext())
            throw new ConnectorNotFoundException("No OpenStack4j connector found in classpath");
        service = it.next();
    }

    public static HttpExecutor create() {
        return INSTANCE;
    }
    
    public String getExecutorName() {
        return service.getExecutorDisplayName();
    }

    /**
     * Delegate to {@link HttpExecutorService#execute(HttpRequest)} 
     */
    public <R> HttpResponse execute(HttpRequest<R> request) {
        return service.execute(request);
    }
}
