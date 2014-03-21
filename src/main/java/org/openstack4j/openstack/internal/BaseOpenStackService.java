package org.openstack4j.openstack.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpRequest.RequestBuilder;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.model.ModelEntity;

import com.google.common.base.Joiner;

public class BaseOpenStackService {

	ServiceType serviceType = ServiceType.IDENTITY;
			
	protected BaseOpenStackService() {
	}
	
	protected BaseOpenStackService(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
	protected <R> Invocation<R> get(Class<R> returnType, String... path) {
		return builder(returnType, path, HttpMethod.GET);
	}
	
	protected <R> Invocation<R> post(Class<R> returnType, String... path) {
		return builder(returnType, path, HttpMethod.POST);
	}
	
	protected <R> Invocation<R> put(Class<R> returnType, String... path) {
		return builder(returnType, path, HttpMethod.PUT);
	}
	
	protected <R> Invocation<R> delete(Class<R> returnType, String... path) {
		return builder(returnType, path, HttpMethod.DELETE);
	}
	
	protected <R> Invocation<R> request(HttpMethod method, Class<R> returnType, String path) {
		return builder(returnType, path, method);
	}

	protected String uri(String path, Object...params) {
		if (params.length == 0) return path;
		return String.format(path, params);
	}
	
	private <R> Invocation<R> builder(Class<R> returnType, String[] path, HttpMethod method) {
		return builder(returnType, Joiner.on("").join(path), method);
	}
	
	private <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
		RequestBuilder<R> req = HttpRequest.builder(returnType).endpointTokenProvider(OSClientSession.getCurrent()).method(method).path(path);
		return new Invocation<R>(req, serviceType);
	}
	
	protected static class Invocation<R> {
		RequestBuilder<R>  req;
		
		protected Invocation(RequestBuilder<R>  req, ServiceType serviceType) {
			this.req = req;
			req.serviceType(serviceType);
		}
		
		public Invocation<R> param(String name, Object value) {
			req.queryParam(name, value);
			return this;
		}
		
		public Invocation<R> serviceType(ServiceType serviceType) {
			req.serviceType(serviceType);
			return this;
		}
		
		public Invocation<R> entity(ModelEntity entity) {
			req.entity(entity);
			return this;
		}
		
		public Invocation<R> json(String json) {
			req.json(json);
			return this;
		}
		
		public R execute() {
			HttpRequest<R> request = req.build();
			return HttpExecutor.create().execute(request).getEntity(request.getReturnType());
	  }
		
		public HttpResponse executeWithResponse() {
			return HttpExecutor.create().execute(req.build());
		}
		
	}
	
}
