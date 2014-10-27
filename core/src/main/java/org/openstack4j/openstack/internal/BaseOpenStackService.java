package org.openstack4j.openstack.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpRequest.RequestBuilder;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.Payload;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class BaseOpenStackService {

    private static final Pattern MESSAGE_PATTERN = Pattern.compile(".*message\\\":\\s\\\"([^\"]+)\\\".*");

    
	ServiceType serviceType = ServiceType.IDENTITY;
	Function<String, String> endpointFunc;
	
	protected BaseOpenStackService() {
	}
	
	protected BaseOpenStackService(ServiceType serviceType) {
		this(serviceType, null);
	}
	
	protected BaseOpenStackService(ServiceType serviceType, Function<String, String> endpointFunc) {
		this.serviceType = serviceType;
		this.endpointFunc = endpointFunc;
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
	
	protected <R> Invocation<R> head(Class<R> returnType, String... path) {
		return builder(returnType, path, HttpMethod.HEAD);
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
	    OSClientSession ses = OSClientSession.getCurrent();
		RequestBuilder<R> req = HttpRequest.builder(returnType).endpointTokenProvider(ses).config(ses.getConfig()).method(method).path(path);
		return new Invocation<R>(req, serviceType, endpointFunc);
	}
	
	protected static class Invocation<R> {
		RequestBuilder<R>  req;
		
		protected Invocation(RequestBuilder<R>  req, ServiceType serviceType, Function<String, String> endpointFunc) {
			this.req = req;
			req.serviceType(serviceType);
			req.endpointFunction(endpointFunc);
		}
		
		public HttpRequest<R> getRequest() {
		    return req.build();
		}
		
		public Invocation<R> param(String name, Object value) {
			req.queryParam(name, value);
			return this;
		}
		
		public Invocation<R> params(Map<String, Object> params) {
		    if (params != null) {
		        for (String name : params.keySet())
		            req.queryParam(name, params.get(name));
		    }
            return this;
        }
		
		public Invocation<R> param(boolean condition, String name, Object value) {
			if (condition)
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
		
		public Invocation<R> entity(Payload<?> entity) {
			req.entity(entity);
			req.contentType(ClientConstants.CONTENT_TYPE_OCTECT_STREAM);
			return this;
		}
		
		public Invocation<R> contentType(String contentType) {
			req.contentType(contentType);
			return this;
		}
		
		public Invocation<R> json(String json) {
			req.json(json);
			return this;
		}
		
		public Invocation<R> headers(Map<String, Object> headers) {
			if (headers != null)
				req.headers(headers);
			return this;
		}
		
		public Invocation<R> header(String name, Object value) {
		  req.header(name, value);
			return this;
		}
		
		public R execute() {
			return execute(null);
	  }
		
		public R execute(Function<HttpResponse, R> parser) {
			HttpRequest<R> request = req.build();
			return HttpExecutor.create().execute(request).getEntity(request.getReturnType(), parser);
	  }
		
		public HttpResponse executeWithResponse() {
			return HttpExecutor.create().execute(req.build());
		}
		
	}
	
	protected <T> List<T> toList(T[] arr) {
	    if (arr == null)
	        return Collections.emptyList();
	    return Arrays.asList(arr);
	}
	
	/**
	 * If a JSON responses contains message then we will attempt to parse the error.  If not the original JSON string is returned 
	 * @param json the json string
	 * @return the processed message
	 */
	protected String attemptToExtractMessageFromJson(String json) {
	    if (json != null && json.contains("message")) {
            Matcher m = MESSAGE_PATTERN.matcher(json);
            if (m.matches())
                return m.group(1);
	    }
	    return json;
	}
	
}
