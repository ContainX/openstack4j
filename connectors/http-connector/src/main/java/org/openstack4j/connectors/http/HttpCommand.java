package org.openstack4j.connectors.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

import com.google.common.io.ByteStreams;
import com.google.common.net.MediaType;

/**
 * HttpCommand is responsible for executing the actual request driven by the
 * HttpExecutor.
 *
 * @param <R>
 */
public final class HttpCommand<R> {

	private static final Logger LOG = LoggerFactory.getLogger(HttpCommand.class);

	private HttpRequest<R> request;
	private URL connectionUrl;
	private HttpURLConnection connection;
	private int retries;

	private HttpCommand(HttpRequest<R> request) {

		this.request = request;
	}

	/**
	 * Creates a new HttpCommand from the given request
	 *
	 * @param request the request
	 * @return the command
	 */
	public static <R> HttpCommand<R> create(HttpRequest<R> request) {
		HttpCommand<R> command = new HttpCommand<R>(request);
		command.initialize();
		return command;
	}

	private void initialize() {
		try {

			populateQueryParams();
			populateHeaders();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}

	}

	/**
	 * Executes the command and returns the Response
	 *
	 * @return the response
	 * @throws Exception
	 */
	public HttpResponse execute() throws Exception {
		byte[] requestBody = null;

		if (request.getEntity() != null) {
			if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) {
				requestBody = ByteStreams.toByteArray((InputStream)request.getEntity());
			}
			else
			{
				String content = ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer().writeValueAsString(request.getEntity());
				requestBody = content.getBytes();
			}
		} else if (request.hasJson()) {
			requestBody = request.getJson().getBytes();
		}

		try {
			connection.setRequestMethod(request.getMethod().name());
			if (requestBody != null) {
				connection.setDoOutput(true);
				BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
				out.write(requestBody);
				out.flush();
			}
			byte[] data = null;

			int status = connection.getResponseCode();
			if (status >= 200 && status < 300) {
				data = ByteStreams.toByteArray(connection.getInputStream());
			}
			return HttpResponseImpl.wrap(connection.getHeaderFields(),
					status, connection.getResponseMessage(),
					data);

		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			connection.disconnect();
		}

	}

	/**
	 * @return true if a request entity has been set
	 */
	public boolean hasEntity() {
		return request.getEntity() != null;
	}

	/**
	 * @return current retry execution count for this command
	 */
	public int getRetries() {
		return retries;
	}

	/**
	 * @return incremement's the retry count and returns self
	 */
	public HttpCommand<R> incrementRetriesAndReturn() {
		initialize();
		retries++;
		return this;
	}

	public HttpRequest<R> getRequest() {
		return request;
	}

	private void populateQueryParams() throws MalformedURLException {

		StringBuilder url = new StringBuilder();
		url.append(new EndpointURIFromRequestFunction().apply(request));

		if (!request.hasQueryParams()) {
			connectionUrl = new URL(url.toString());
			return;
		}

		url.append("?");

		for (Map.Entry<String, List<Object>> entry : request.getQueryParams().entrySet()) {
			for (Object o : entry.getValue()) {
				try {
					url.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(o), "UTF-8"));
					url.append("&");
				} catch (UnsupportedEncodingException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		if (url.charAt(url.length() - 1) == '&') {
			url.deleteCharAt(url.length() - 1);
		}
		connectionUrl = new URL(url.toString());
	}

	private void populateHeaders() throws IOException {

		connection = (HttpURLConnection) connectionUrl.openConnection();
		connection.setRequestProperty("Content-Type", request.getContentType());
		connection.setRequestProperty("Accept", MediaType.JSON_UTF_8.toString());

		if (!request.hasHeaders()) {
			return;
		}
		for (Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
			connection.setRequestProperty(h.getKey(), String.valueOf(h.getValue()));
		}

	}
}
