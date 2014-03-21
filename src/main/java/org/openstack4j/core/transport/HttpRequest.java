package org.openstack4j.core.transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.EndpointTokenProvider;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.ModelEntity;

import com.google.common.collect.Maps;

/**
 * A Request Delegate which aids in building the request that is compatible with the OpenStack Rest API. The request is used to encoding as well as keeping reference to 
 * the return type
 * 
 * @param <R> the entity return type
 * @author Jeremy Unruh
 */
public class HttpRequest<R> {


	String endpoint;
	String path;
	Class<R> returnType;
	ModelEntity entity;
	String contentType = ClientConstants.CONTENT_TYPE_JSON;
	HttpMethod method = HttpMethod.GET;
	String json;
	
	public HttpRequest() { }

	/**
	 * Creates a new HttpRequest
	 *
	 * @param endpoint the endpoint URI
	 * @param path the path which will be appened to the endpoint URI
	 * @param method the method the method type to invoke
	 * @param entity the entity (used for posts)
	 * @param returnType the expected return type
	 */
	public HttpRequest(String endpoint, String path, HttpMethod method, ModelEntity entity, Class<R> returnType) {
		this.endpoint = endpoint;
		this.path = path;
		this.method = method;
		this.entity = entity;
	}

	/**
	 * A build for creating HttpRequest objects
	 *
	 * @return the request builder
	 */
	public static RequestBuilder<Void> builder() {
		return new RequestBuilder<Void>(Void.class);
	}

	/**
	 * A build for creating HttpRequest objects
	 *
	 * @param <R> the expected return type
	 * @param returnType the return type
	 * @return the request builder
	 */
	public static <R> RequestBuilder<R> builder(Class<R> returnType) {
		return new RequestBuilder<R>(returnType);
	}

	/**
	 * @return the method this request will use
	 */
	public HttpMethod getMethod() {
		return method;
	}
	
	/**
	 * @return the content type for the request
	 */
	public String getContentType() {
		return contentType;
	}

	private Map<String, List<Object>> queryParams;
	private Map<String, List<Object>> headers = new HashMap<String, List<Object>>();


	/**
	 * @return the endpoint URI
	 */
	public String getEndpoint() {
		return endpoint;
	}
	
	/**
	 * @return the http path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * If JSON is explicitly set vs an entity then this method will return a JSON String otherwise Empty
	 * @return JSON String form or Empty
	 */
	public String getJson() {
		return (json == null) ? "" : json;
	}
	
	/**
	 * @return true, if a JSON Object has been set
	 */
	public boolean hasJson() {
		return (json != null);
	}
	
	/**
	 * @return the return type expected after invocation
	 */
	public Class<R> getReturnType() {
		return returnType;
	}
	
	/**
	 * @return the entity to post
	 */
	public ModelEntity getEntity() {
		return entity;
	}
	
	/**
	 * @return true, if query params have been added
	 */
	public boolean hasQueryParams() {
		return queryParams != null && !queryParams.isEmpty();
	}

	/**
	 * @return the request query params
	 */
	public Map<String, List<Object>> getQueryParams() {
		return queryParams;
	}

	/**
	 * @return the headers to apply
	 */
	public Map<String, List<Object>> getHeaders() {
		return headers;
	}

	/**
	 * @return true, if headers have been added
	 */
	public boolean hasHeaders() {
		return !headers.isEmpty();
	}

	public static final class RequestBuilder<R> {

		HttpRequest<R> request;
		EndpointTokenProvider provider;
		ServiceType service;
		
		public RequestBuilder(Class<R> returnType) {
			request = new HttpRequest<R>();
			request.returnType = returnType;
		}

		/**
		 * @see HttpRequest#getEndpoint()
		 */
		public RequestBuilder<R> endpoint(String endpoint) {
			request.endpoint = endpoint;
			return this;
		}

		/**
		 * @see HttpRequest#getPath()
		 */
		public RequestBuilder<R> path(String path) {
			request.path = path;
			return this;
		}

		/**
		 * @see HttpRequest#getMethod()
		 */
		public RequestBuilder<R> method(HttpMethod method) {
			request.method = method;
			return this;
		}

		/**
		 * Flags the request method as PUT
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodPut() {
			request.method = HttpMethod.PUT;
			return this;
		}

		/**
		 * Flags the request method as GET
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodGet() {
			request.method = HttpMethod.GET;
			return this;
		}

		/**
		 * Flags the request method as DELETE
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodDelete() {
			request.method = HttpMethod.DELETE;
			return this;
		}

		/**
		 * Flags the request method as POST
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodPost() {
			request.method = HttpMethod.POST;
			return this;
		}

		/**
		 * @see HttpRequest#getEntity()
		 */
		public RequestBuilder<R> entity(ModelEntity entity) {
			request.entity = entity;
			return this;
		}
		
		/**
		 * The endpoint Service Type
		 *
		 * @param service the service type
		 * @return the request builder
		 */
		public RequestBuilder<R> serviceType(ServiceType service) {
			this.service = service;
			return this;
		}

		/**
		 * Adds a Key/Value based Query Param
		 *
		 * @param key the key
		 * @param value the value
		 * @return the request builder
		 */
		public RequestBuilder<R> queryParam(String key, Object value) {
			if (request.queryParams == null)
				request.queryParams = Maps.newHashMap();

			if (request.queryParams.containsKey(key)) {
				List<Object> values = request.queryParams.get(key);
				values.add(value);
			} else {
				List<Object> values = new ArrayList<Object>();
				values.add(value);
				request.queryParams.put(key, values);
			}
			return this;
		}

		/**
		 * A Provider which will return the current Authorization Token
		 *
		 * @param provider the provider
		 * @return the request builder
		 */
		public RequestBuilder<R> endpointTokenProvider(EndpointTokenProvider provider) {
			this.provider = provider;
			return this;
		}
		
		/**
		 * AdHoc JSON object to Post/Put
		 *
		 * @param json the JSON object in String form
		 * @return the request builder
		 */
		public RequestBuilder<R> json(String json) {
			request.json = json;
			return this;
		}

		/**
		 * Builds the HttpRequest
		 *
		 * @return HttpRequest
		 */
		public HttpRequest<R> build() {
			if (provider != null)
			{
				request.endpoint = provider.getEndpoint(service);
				request.getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, Arrays.asList((Object)provider.getTokenId()));
			}
			return request;
		}
	}
}
