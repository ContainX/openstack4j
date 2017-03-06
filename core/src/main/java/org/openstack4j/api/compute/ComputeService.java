package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.api.compute.ext.FloatingIPDNSService;
import org.openstack4j.api.compute.ext.HypervisorService;
import org.openstack4j.api.compute.ext.MigrationService;
import org.openstack4j.api.compute.ext.ServicesService;
import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Extension;

/**
 * Compute (Nova) Operations API
 * 
 * @author Jeremy Unruh
 */
public interface ComputeService extends RestService {

	/**
	 * Flavor Service API
	 *
	 * @return the flavor service
	 */
	FlavorService flavors();

	/**
	 * Image Service API
	 *
	 * @return the image service
	 */
	ComputeImageService images();
	
	/**
	 * Hypervisor Service Extension API 
	 * 
	 * @return the hypervisor service
	 */
	HypervisorService hypervisors();
	
	/**
	 * ZoneService Extension API 
	 * 
	 * @return the zones service
	 */
	ZoneService zones();

	/**
	 * Server Service API
	 *
	 * @return the server service
	 */
	ServerService servers();

	/**
	 * Quota-Set Service API
	 *
	 * @return the quota set service
	 */
	QuotaSetService quotaSets();

	/**
	 * Compute Os-Host API
	 *
	 * @return the compute os-host service
	 */
	HostService host();

	/**
	 * Floating IP Service API
	 *
	 * @return the floating-ip service
	 */
	ComputeFloatingIPService floatingIps();
	
	/**
	 * Security Groups Extension API
	 * 
	 * @return the security groups service
	 */
	ComputeSecurityGroupService securityGroups();
	
	/**
	 * Keypair Management Service
	 * 
	 * @return the keypair service
	 */
	KeypairService keypairs();

	/**
	 * Administrators only - provides in-progress migrations for a region or cell
	 * 
	 * @return the migration service
	 */
	MigrationService migrations();
	
	/**
	 * @return a list of Extensions that have been added against the Compute service
	 */
	List<? extends Extension> listExtensions();
	
	/**
	 * ServerGroup Management Service
	 * 
	 * @return ServerGroup service
	 */
	ServerGroupService serverGroups();
	
	/**
	 * Service that manages the extension 'os-floating-ip-dns'
	 * 
	 * @return the floating IP DNS Service
	 */
	FloatingIPDNSService floatingIPDNS();
	/**
	 * Host Aggregates Management Service
	 */
	HostAggregateService hostAggregates();
	
	/**
	 * Server Tag Management Service
	 * @return ServerTag Service
	 */
	ServerTagService serverTags();
	
    /**
     *  Compute services service
     *  
     * @return ServicesService
     */
    ServicesService services();
}
