package org.openstack4j.openstack.provider;

import java.util.Map;

import org.openstack4j.api.APIProvider;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.api.compute.ImageService;
import org.openstack4j.api.compute.QuotaSetService;
import org.openstack4j.api.compute.ServerService;
import org.openstack4j.api.exceptions.ApiNotFoundException;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.identity.RoleService;
import org.openstack4j.api.identity.ServiceManagerService;
import org.openstack4j.api.identity.TenantService;
import org.openstack4j.api.identity.UserService;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.networking.PortService;
import org.openstack4j.api.networking.RouterService;
import org.openstack4j.api.networking.SubnetService;
import org.openstack4j.openstack.compute.internal.ComputeServiceImpl;
import org.openstack4j.openstack.compute.internal.FlavorServiceImpl;
import org.openstack4j.openstack.compute.internal.ImageServiceImpl;
import org.openstack4j.openstack.compute.internal.QuotaSetServiceImpl;
import org.openstack4j.openstack.compute.internal.ServerServiceImpl;
import org.openstack4j.openstack.identity.internal.IdentityServiceImpl;
import org.openstack4j.openstack.identity.internal.RoleServiceImpl;
import org.openstack4j.openstack.identity.internal.ServiceManagerServiceImpl;
import org.openstack4j.openstack.identity.internal.TenantServiceImpl;
import org.openstack4j.openstack.identity.internal.UserServiceImpl;
import org.openstack4j.openstack.networking.internal.NetworkServiceImpl;
import org.openstack4j.openstack.networking.internal.NetworkingServiceImpl;
import org.openstack4j.openstack.networking.internal.PortServiceImpl;
import org.openstack4j.openstack.networking.internal.RouterServiceImpl;
import org.openstack4j.openstack.networking.internal.SubnetServiceImpl;

import com.google.common.collect.Maps;

/**
 * Simple API Provider which keeps internally Maps interface implementations as singletons
 * 
 * @author Jeremy Unruh
 */
public class DefaultAPIProvider implements APIProvider {

	private static final Map<Class<?>, Class<?>> bindings = Maps.newHashMap();
	private static final Map<Class<?>, Object> instances = Maps.newConcurrentMap();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		bind(IdentityService.class, IdentityServiceImpl.class);
		bind(TenantService.class, TenantServiceImpl.class);
		bind(UserService.class, UserServiceImpl.class);
		bind(RoleService.class, RoleServiceImpl.class);
		bind(ServiceManagerService.class, ServiceManagerServiceImpl.class);
		bind(ComputeService.class, ComputeServiceImpl.class);
		bind(FlavorService.class, FlavorServiceImpl.class);
		bind(ImageService.class, ImageServiceImpl.class);
		bind(ServerService.class, ServerServiceImpl.class);
		bind(QuotaSetService.class, QuotaSetServiceImpl.class);
		bind(NetworkingService.class, NetworkingServiceImpl.class);
		bind(NetworkService.class, NetworkServiceImpl.class);
		bind(SubnetService.class, SubnetServiceImpl.class);
		bind(PortService.class, PortServiceImpl.class);
		bind(RouterService.class, RouterServiceImpl.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> api) {
		if (instances.containsKey(api))
			return (T) instances.get(api);
		
		if (bindings.containsKey(api)) {
			try {
				T impl = (T) bindings.get(api).newInstance();
				instances.put(api, impl);
				return impl;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ApiNotFoundException("API Not found for: " + api.getName(), e);
			}
		}
		throw new ApiNotFoundException("API Not found for: " + api.getName());
	}

	private void bind(Class<?> api, Class<?> impl)
	{
		bindings.put(api, impl);
	}
}
