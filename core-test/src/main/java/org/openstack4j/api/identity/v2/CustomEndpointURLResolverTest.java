package org.openstack4j.api.identity.v2;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.identity.EndpointURLResolver;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.identity.URLResolverParams;
import org.openstack4j.openstack.internal.OSClientSession.OSClientSessionV2;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This test will validate that by default, the endpoint used is the one
 * provided in the token. If custom logic is provided by the
 * Config.withEndpointURLResolver, this logic should be used instead.
 * 
 * @author bcornelis
 */
@Test(suiteName = "Identity/Keystone_V2")
public class CustomEndpointURLResolverTest extends AbstractTest {

	private EndpointURLResolver mockEndpointUrlResolver;

	@Override
	protected Service service() {
		return Service.IDENTITY;
	}

	/**
	 * This test verifies the default logic to retrieve the endpoint URL is
	 * still working. Internally it will generate the endpoint included in the
	 * security token.
	 * 
	 * @throws IOException
	 */
	public void defaultImplementation_Test() throws IOException {
		// create a default session containing the default Config object
		final OSClientSessionV2 clientV2 = getDefaultSession();

		// use default logic to generate the identity endpoint url
		final String result = clientV2.getEndpoint(ServiceType.IDENTITY);

		// and make sure it's the default URL
		assertEquals(result, "http://127.0.0.1:5000/v2.0", "the message");
	}

	/**
	 * This test validates the custom url endpoint resolver is used when it has
	 * been configured in the Config class.
	 * 
	 * @throws IOException
	 */
	public void customImplementation_Test() throws IOException {
		// create the default session
		final OSClientSessionV2 customConfigSession = getCustomConfigSession();

		// retrieve the default endpoint
		final String result = customConfigSession.getEndpoint(ServiceType.IDENTITY);

		// and make sure it's the default URL
		assertEquals(result, "customUrl", "the message");
	}

	@BeforeTest
	public void setup() {
		mockEndpointUrlResolver = mock(EndpointURLResolver.class);
		when(mockEndpointUrlResolver.findURLV2(any(URLResolverParams.class))).thenReturn("customUrl");
	}

	/**
	 * Creates a V3 client using the default testing methods.
	 * 
	 * @return
	 * @throws IOException
	 */
	private OSClientSessionV2 getDefaultSession() throws IOException {
		respondWith(JSON_ACCESS);
		return (OSClientSessionV2) osv2();
	}

	/**
	 * Creates a custom V3 client based on the default logic, but customizing
	 * the Config by explicitely specifying the endpoint URL to use
	 * 
	 * @return
	 * @throws IOException
	 */
	private OSClientSessionV2 getCustomConfigSession() throws IOException {
		final OSClientSessionV2 origSession = getDefaultSession();
		return OSClientSessionV2.createSession(origSession.getAccess(), origSession.getPerspective(),
				origSession.getProvider(), Config.newConfig().withEndpointURLResolver(mockEndpointUrlResolver));
	}
}
