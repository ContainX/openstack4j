package org.openstack4j.api;

import java.util.ServiceLoader;

import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.networking.NetworkingService;

/**
 * Provides access to the Major APIs and Buildables
 * 
 * @author Jeremy Unruh
 */
public class Apis {

	private static final APIProvider provider = initializeProvider();

	/**
	 * Gets the API implementation based on Type
	 *
	 * @param <T> the API type
	 * @param api the API implementation
	 * @return the API implementation
	 */
	public static <T> T get(Class<T> api) {
		return provider.get(api);
	}
	
	/**
	 * Gets the identity services API
	 *
	 * @return the identity services
	 */
	public static IdentityService getIdentityServices() {
		return get(IdentityService.class);
	}
	
	/**
	 * Gets the compute services API
	 *
	 * @return the compute services
	 */
	public static ComputeService getComputeServices() {
		return get(ComputeService.class);
	}
	
	/**
	 * Gets the Network services API
	 *
	 * @return the network services
	 */
	public static NetworkingService getNetworkingServices() {
		return get(NetworkingService.class);
	}
	
	/**
	 * Gets the (Glance) Image services API
	 * 
	 * @return the image services
	 */
	public static ImageService getImageService() {
		return get(ImageService.class);
	}
	
	/**
	 * Gets the (Heat) Orchestration services API
	 * @return the heat services
	 */
	public static HeatService getHeatServices() {
		return get(HeatService.class);
	}
	
	private static APIProvider initializeProvider() {
		APIProvider p = ServiceLoader.load(APIProvider.class).iterator().next();
		p.initialize();
		return p;
	}
}
