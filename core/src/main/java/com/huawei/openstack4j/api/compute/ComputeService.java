/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.compute;

import java.util.List;

import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSService;
import com.huawei.openstack4j.api.compute.ext.HypervisorService;
import com.huawei.openstack4j.api.compute.ext.MigrationService;
import com.huawei.openstack4j.api.compute.ext.ServicesService;
import com.huawei.openstack4j.api.compute.ext.ZoneService;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.Extension;

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
	 * Server V1 Service API
	 *
	 * @return the server service
	 */
	ServerV1Service serversV1();

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
