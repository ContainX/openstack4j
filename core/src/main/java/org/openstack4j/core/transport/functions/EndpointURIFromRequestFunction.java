package org.openstack4j.core.transport.functions;

import static org.openstack4j.core.transport.ClientConstants.URI_SEP;

import org.openstack4j.core.transport.HttpRequest;

import com.google.common.base.Function;

/**
 * Builds a URI comprising of Endpoint and Path from a HttpRequest object
 * 
 * @author Jeremy Unruh
 */
public class EndpointURIFromRequestFunction implements Function<HttpRequest<?>, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public String apply(HttpRequest<?> request) {
        if (request.getEndpoint().endsWith(URI_SEP) || request.getPath().startsWith(URI_SEP))
            return request.getEndpoint() + request.getPath();
        
        return request.getEndpoint() + URI_SEP + request.getPath();
    }

}
