package org.openstack4j.connectors.okhttp;

import okhttp3.Response;
import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.openstack.internal.OSAuthenticator;
import org.openstack4j.openstack.internal.OSClientSession;


/**
 * HttpExecutor is the default implementation for HttpExecutorService which is responsible for interfacing with OKHttp and mapping common status codes, requests and responses
 * back to the common API
 * 
 * @author Jeremy Unruh
 */
public class HttpExecutorServiceImpl implements HttpExecutorService {

    private static final String NAME = "OKHttp Connector";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpResponse execute(HttpRequest<R> request) {
        try {
            return invoke(request);
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Invokes the given request
     *
     * @param <R> the return type
     * @param request the request to invoke
     * @return the response
     * @throws Exception the exception
     */
    private <R> HttpResponse invoke(HttpRequest<R> request) throws Exception {

        HttpCommand<R> command = HttpCommand.create(request);

        try {
            return invokeRequest(command);
        } catch (Exception pe) {
            throw new ConnectionException(pe.getMessage(), 0, pe);
        }
    }

    private <R> HttpResponse invokeRequest(HttpCommand<R> command) throws Exception {
        Response response = command.execute();
        if (command.getRetries() == 0 && response.code() == 401 && !command.getRequest().getHeaders().containsKey(ClientConstants.HEADER_OS4J_AUTH))
        {
            OSAuthenticator.reAuthenticate();
            command.getRequest().getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, OSClientSession.getCurrent().getTokenId());
            return invokeRequest(command.incrementRetriesAndReturn());
        }
        return HttpResponseImpl.wrap(response);
    }

    @Override
    public String getExecutorDisplayName() {
        return NAME;
    }

}
