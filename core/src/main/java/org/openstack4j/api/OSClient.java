package org.openstack4j.api;

import java.util.Set;

import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.storage.BlockStorageService;
import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.api.telemetry.TelemetryService;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.Access;
import org.openstack4j.model.identity.Token;

/**
 * A client which has been identified.  Any calls spawned from this session will automatically utilize the original authentication that was
 * successfully validated and authorized
 * 
 * @author Jeremy Unruh
 */
public interface OSClient {
	
    /**
     * Specifies the region that should be used for further invocations with this client.  If the region is invalid or doesn't exists
     * execution errors will occur when invoking API calls and a {@link RegionEndpointNotFoundException} will be
     * thrown
     * 
     * @param region the region to use
     * @return OSClient for method chaining
     */
    OSClient useRegion(String region);
    
    /**
     * Removes the current region making all calls no longer resolving to region (if originally set otherwise no-op)
     * 
     * @return OSClient for method chaining
     */
    OSClient removeRegion();
    
	/**
	 * Gets the supported services.  A set of ServiceTypes will be returned identifying the OpenStack services installed and supported
	 *
	 * @return the supported services
	 */
	Set<ServiceType> getSupportedServices();
	
	/**
	 * Determines if the Compute (Nova) service is supported
	 *
	 * @return true, if supports compute
	 */
	boolean supportsCompute();
	
	/**
	 * Determines if the Identity (Keystone) service is supported
	 *
	 * @return true, if supports identity
	 */
	boolean supportsIdentity();
	
	/**
	 * Determines if the Network (Neutron) service is supported
	 *
	 * @return true, if supports network
	 */
	boolean supportsNetwork();
	
	/**
	 * Determines if the Image (Glance) service is supported
	 *
	 * @return true, if supports image
	 */
	boolean supportsImage();
	
	/**
	 * Determines if the Orchestration (Heat) service is supported
	 * 
	 * @return true if supports Heat
	 */
	boolean supportsHeat();
	
	/**
     * Determines if the Block Storage (Cinder) service is supported
     * 
     * @return true if supports Block Storage
     */
    boolean supportsBlockStorage();
    
    /**
     * Determines if the Object Storage (Swift) service is supported
     * 
     * @return true if supports Object Storage
     */
    boolean supportsObjectStorage();

	/**
	 * Gets the token that was assigned during authorization
	 *
	 * @return the authentication token
	 */
	Token getToken();
	
	/**
	 * Gets the current endpoint of the Identity service
	 *
	 * @return the endpoint
	 */
	String getEndpoint();
	
	/**
	 * Returns the Identity Service API
	 *
	 * @return the identity service
	 */
	IdentityService identity();
	
	/**
	 * Returns the Compute Service API
	 *
	 * @return the compute service
	 */
	ComputeService compute();
	
	/**
	 * Returns the Networking Service API
	 * 
	 * @return the networking service
	 */
	NetworkingService networking();
	
	/**
	 * Returns the Block Storage Service API
	 * 
	 * @return the block storage service
	 */
	BlockStorageService blockStorage();
	
	/**
     * Returns the Object Storage Service API
     * 
     * @return the object storage service
     */
    ObjectStorageService objectStorage();
	
	/**
	 * Returns the Image Service API
	 * 
	 * @return the image service
	 */
	ImageService images();
	
	/**
	 * Returns the Telemetry Service API
	 * 
	 * @return the telemetry service
	 */
	TelemetryService telemetry();
	
	/**
	 * @return the authorized access entity which contains the authorized token, user details and service catalog
	 */
	Access getAccess();
	
	/**
	 * Returns the Heat Service API
	 * 
	 * @return the Heat service
	 */
	HeatService heat();
}
