package org.openstack4j.connectors.httpclient5;

import org.apache.hc.client5.http.methods.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.openstack.internal.OSAuthenticator;
import org.openstack4j.openstack.internal.OSClientSession;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

/**
 * HttpExecutor is the default implementation for HttpExecutorService which is responsible for interfacing with HttpClient and mapping common status codes, requests and responses
 * back to the common API
 *
 * @author Jeremy Unruh
 * @author Gleb Schukin
 */
public class HttpExecutorServiceImpl implements HttpExecutorService {

    private static final String NAME = "Apache HttpClient5 Connector";
    private static final Logger LOG = LoggerFactory.getLogger(HttpExecutorServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpResponse execute(HttpRequest<R> request) {
        try {
            return invoke(request);
        } catch (ResponseException re) {
            throw re;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
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
        } catch (ResponseException re) {
            throw re;
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage(), 0, e);
        }
    }

    private <R> HttpResponse invokeRequest(HttpCommand<R> command) throws Exception {
        CloseableHttpResponse response = command.execute();

        if (command.getRetries() == 0 && response.getCode() == HttpStatus.SC_UNAUTHORIZED && !command.getRequest().getHeaders().containsKey(ClientConstants.HEADER_OS4J_AUTH)) {
            try {
                OSAuthenticator.reAuthenticate();
                command.getRequest().getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, OSClientSession.getCurrent().getTokenId());
                return invokeRequest(command.incrementRetriesAndReturn());
            } finally {
                response.close();
            }
        }

        return HttpResponseImpl.wrap(response);
    }

    @Override
    public String getExecutorDisplayName() {
        return NAME;
    }

}
