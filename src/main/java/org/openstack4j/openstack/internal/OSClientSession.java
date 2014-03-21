package org.openstack4j.openstack.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.openstack4j.api.Apis;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.EndpointTokenProvider;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.Access.Service;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.model.identity.Token;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.identity.domain.KeystoneEndpoint;
import org.openstack4j.openstack.identity.functions.ServiceToServiceType;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * A client which has been identified.  Any calls spawned from this session will automatically utilize the original authentication that was
 * successfully validated and authorized
 * 
 * @author Jeremy Unruh
 */
public class OSClientSession implements OSClient, EndpointTokenProvider {
	
	private static final ThreadLocal<OSClientSession> sessions = new ThreadLocal<OSClientSession>();
	
	Map<ServiceType, Endpoint> endpoints = Maps.newHashMap();
	KeystoneAccess access;
	Set<ServiceType> supports;
	String endpoint;
	String publicHostIP;
	
	private OSClientSession(KeystoneAccess access, String endpoint)
	{
		this.access = access;
		this.endpoint = endpoint;
		sessions.set(this);
	}
	
	public static OSClientSession createSession(KeystoneAccess access, String endpoint) {
		return new OSClientSession(access, endpoint);
	}
	
	public static OSClientSession getCurrent() {
		return sessions.get();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<ServiceType> getSupportedServices() {
		System.out.println(access.getServiceCatalog());
		if (supports == null)
			supports = Sets.immutableEnumSet(Iterables.transform(access.getServiceCatalog(), new ServiceToServiceType()));
		return supports;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCompute() {
		return supports.contains(ServiceType.COMPUTE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsIdentity() {
		return supports.contains(ServiceType.IDENTITY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsNetwork() {
		return supports.contains(ServiceType.NETWORK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return access.getToken();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint() {
		return endpoint;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint(ServiceType service) {
		if (service == null)
			return getEndpoint();
		
		if (endpoints.containsKey(service))
		{
			return getEndpointURL(endpoints.get(service));
		}
		
		for (Service sc : access.getServiceCatalog()) {
			if (service.getServiceName().equals(sc.getName()))
			{
				if (sc.getServiceType() == ServiceType.NETWORK)
				{
					KeystoneEndpoint.builder().from(sc.getEndpoints().get(0)).type(sc.getServiceType().name());
					endpoints.put(service, sc.getEndpoints().get(0));
				}
				else
					endpoints.put(service, sc.getEndpoints().get(0));
				return getEndpointURL(sc.getEndpoints().get(0));
			}
		}
		
		return endpoint;
	}
	
	/**
	 * Gets the endpoint url.
	 *
	 * @param endpoint the endpoint
	 * @return the endpoint url
	 */
	private String getEndpointURL(Endpoint endpoint) {
		if (endpoint.getAdminURL() != null)
		{
			if (getPublicIP() != null && !getPublicIP().equals(endpoint.getAdminURL().getHost()))
			{
				return endpoint.getAdminURL().toString().replaceAll(endpoint.getAdminURL().getHost(), getPublicIP());
			}
			return endpoint.getAdminURL().toString();
		}
		return endpoint.getPublicURL().toString();
	}

	private String getPublicIP() {
		if (publicHostIP == null) {
			try {
				publicHostIP = new URI(endpoint).getHost();
			}
			catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return publicHostIP;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTokenId() {
		return getToken().getId();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IdentityService identity() {
		return Apis.getIdentityServices();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComputeService compute() {
		return Apis.getComputeServices();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NetworkingService networking() {
		return Apis.getNetworkingServices();
	}

}
