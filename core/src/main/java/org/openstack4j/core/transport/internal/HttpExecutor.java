package org.openstack4j.core.transport.internal;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.openstack4j.api.exceptions.ConnectorNotFoundException;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

/**
 * HttpExecutor is a delegate to the underline connector associated to OpenStack4j.
 * 
 * @author Jeremy Unruh
 */
public class HttpExecutor  {

    private static final HttpExecutor INSTANCE = new HttpExecutor();
    private static final Logger LOG = LoggerFactory.getLogger(HttpExecutor.class);
    private HttpExecutorService service;

    private HttpExecutor() { 
        initService();
    }

    private void initService() {
        Iterator<HttpExecutorService> it = ServiceLoader.load(HttpExecutorService.class).iterator();
        if (!it.hasNext())
        {
            LOG.error("No OpenStack4j connector found in classpath");
            throw new ConnectorNotFoundException("No OpenStack4j connector found in classpath");
        }
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
        LOG.debug("Executing Request: %s -> %s", request.getEndpoint(), request.getPath());
        return service.execute(request);
    }
}
