package org.openstack4j.api;

import java.io.IOException;
import java.io.InputStream;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

/**
 * Base Test class which handles Mocking a Webserver to fullfill and test against JSON response objects
 * from an OpenStack deployment
 * 
 * @author Jeremy Unruh
 */
public abstract class AbstractTest {

	protected enum Service {
		IDENTITY(5000)
		;
		private final int port;
		
		private Service(int port) { this.port = port; }
		
	}
	
	protected MockWebServer server = new MockWebServer();
	
	/**
	 * @return the service the API is using
	 */
	protected abstract Service service();
	
  @BeforeClass
	protected void startServer() {
		try {
   			server.play(service().port);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The path to the expected JSON results
	 *
	 * @param resource the resource
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void respondWith(String resource) throws IOException {
		MockResponse r = new MockResponse();
		InputStream is = getClass().getResourceAsStream(resource);
		r.setBody(is, is.available());
		r.setHeader("Content-Type", "application/json");
		server.enqueue(r);
	}
	
	/**
	 * Responds with negative based response code and no body
	 * @param statusCode the status code to respond with
	 */
	protected void respondWith(int statusCode) {
		MockResponse r = new MockResponse();
		r.setResponseCode(statusCode);
		server.enqueue(r);
	}
	
	protected String authURL(String path) {
		return String.format("http://127.0.0.1:%d%s",service().port, path);
	}
	
	@AfterTest
	protected void afterTest()
	{
		try {
			server.shutdown();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
