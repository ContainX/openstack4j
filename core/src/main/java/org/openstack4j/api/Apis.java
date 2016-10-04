package org.openstack4j.api;

import org.openstack4j.api.barbican.BarbicanService;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.gbp.GbpService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.manila.ShareService;
import org.openstack4j.api.murano.v1.AppCatalogService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.sahara.SaharaService;
import org.openstack4j.api.senlin.SenlinService;
import org.openstack4j.api.trove.TroveService;
import org.openstack4j.api.tacker.TackerService;

import java.util.ServiceLoader;

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
     * @param <T>
     *            the API type
     * @param api
     *            the API implementation
     * @return the API implementation
     */
    public static <T> T get(Class<T> api) {
        return provider.get(api);
    }

    /**
     * Gets the identity v3 services API
     *
     * @return the identity v3 services
     */
    public static org.openstack4j.api.identity.v3.IdentityService getIdentityV3Services() {
        return get(org.openstack4j.api.identity.v3.IdentityService.class);
    }
    
    /**
     * Gets the identity v2 services API
     *
     * @return the identity v2 services
     */
    public static org.openstack4j.api.identity.v2.IdentityService getIdentityV2Services() {
        return get(org.openstack4j.api.identity.v2.IdentityService.class);
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
     * Gets the Tacker services API
     *
     * @return the tacker services
     */
    public static TackerService getTackerServices() {
        return get(TackerService.class);
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
     * Gets the (Glance) Image v2 services API
     * @return the image v2 services
     */
    public static org.openstack4j.api.image.v2.ImageService getImageV2Service() {
        return get(org.openstack4j.api.image.v2.ImageService.class);
    }

    /**
     * Gets the (Heat) Orchestration services API
     * 
     * @return the heat services
     */
    public static HeatService getHeatServices() {
        return get(HeatService.class);
    }

    /**
     * Gets the (Murano) App Catalog services API
     *
     * @return the murano services
     */
    public static AppCatalogService getMuranoServices() {
        return get(AppCatalogService.class);
    }

    /**
     * Gets the (Sahara) Data Processing services API
     * 
     * @return the sahara services
     */
    public static SaharaService getSaharaServices() {
        return get(SaharaService.class);
    }

    /**
     * Gets the (Manila) Shared File Systems services API
     * 
     * @return the share services
     */
    public static ShareService getShareServices() {
        return get(ShareService.class);
    }

	/**
     * Gets the group based policy services API
     * @return the gbp services 
     */
    public static GbpService getGbpServices() {
        return get(GbpService.class);
    }

    /**
     * Gets the trove services API
     * @return the trove services
     */
    public static TroveService getTroveServices(){
        return get(TroveService.class);
    }

	/**
	 * Gets the (Senlin) Orchestration services API
	 * @return the Senlin services
	 */
	public static SenlinService getSenlinServices() {
		return get(SenlinService.class);
	}


    /**
     * Gets the (BarbicanService) Orchestration services API
     * @return the BarbicanService services
     */
    public static BarbicanService getBarbicanServices() {
        return get(BarbicanService.class);
    }

    private static APIProvider initializeProvider() {
        // No need to check for emptiness as there is default implementation registered
        APIProvider p = ServiceLoader.load(APIProvider.class, Apis.class.getClassLoader()).iterator().next();
        p.initialize();
        return p;
    }
}
