package org.openstack4j.core.transport.internal;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpExecutorService;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;

/**
 * HttpExecutor is the default implementation for HttpExecutorService which is responsible for interfacing with Jersey and mapping common status codes, requests and responses
 * back to the common API
 * 
 * @author Jeremy Unruh
 */
public class HttpExecutor implements HttpExecutorService {

	private static final HttpExecutor INSTANCE = new HttpExecutor();

	private HttpExecutor() { }

	public static HttpExecutor create() {
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R> HttpResponse execute(HttpRequest<R> request) {
		return execute(request, request.useNonStrictSSLClient());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R> HttpResponse execute(HttpRequest<R> request, boolean useNonStrictSSL) {
		try {
			return invoke(request, useNonStrictSSL);
		}
		catch (ResponseException re) {
			throw re;
		}
		catch (Exception e) {
			e.printStackTrace();
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
	private <R> HttpResponse invoke(HttpRequest<R> request, boolean useNonStrictSSL) throws Exception {
		Client client = ClientFactory.create(useNonStrictSSL);
		WebTarget target = client.target(request.getEndpoint()).path(request.getPath());
		
		if (Boolean.getBoolean(HttpLoggingFilter.class.getName()))
			target.register(new HttpLoggingFilter(Logger.getLogger("os"), 10000));
		
		target = populateQueryParams(target, request);

		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
		populateHeaders(invocation,  request);

		Entity<?> entity = (request.getEntity() == null) ? null :
			Entity.entity(request.getEntity(), request.getContentType());
		try {
			if (entity != null) {
				return HttpResponse.wrap(invocation.method(request.getMethod().name(), entity));
			}
			if(HttpMethod.PUT == request.getMethod() || request.hasJson()) {
				return HttpResponse.wrap(invocation.method(request.getMethod().name(), Entity.entity(request.getJson(), ClientConstants.CONTENT_TYPE_JSON)));
			}
			return HttpResponse.wrap(invocation.method(request.getMethod().name()));
		} catch (ProcessingException pe) {
			throw new ConnectionException(pe.getMessage(), 0, pe);
		} catch (ClientErrorException e) {
			throw HttpResponse.mapException(e.getResponse().getStatusInfo().toString(), e.getResponse().getStatus(), e);
		}
	}

	private <R> WebTarget populateQueryParams(WebTarget target, HttpRequest<R> request) {

		if (!request.hasQueryParams()) return target;

		for(Map.Entry<String, List<Object> > entry : request.getQueryParams().entrySet()) {
			for (Object o : entry.getValue()) {
				target = target.queryParam(entry.getKey(), o);
			}
		}
		return target;
	}

	private <R> void populateHeaders(Invocation.Builder invocation, HttpRequest<R> request) {

		if (!request.hasHeaders()) return;

		for(Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
			invocation.header(h.getKey(), h.getValue());
		}
	}

}
