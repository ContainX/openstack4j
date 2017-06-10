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
package org.openstack4j.model.tacker.builder;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.tacker.Vnfd;
import org.openstack4j.openstack.tacker.domain.VnfdAttributes;
import org.openstack4j.openstack.tacker.domain.VnfdServiceTypes;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
public interface VnfdBuilder extends Builder<VnfdBuilder, Vnfd> {

	/**
	 * @param tenantId : Owner of the Vnfd. Only an administrative user can specify a tenant ID other than its own.
	 * @return VnfdBuilder
	 */
	VnfdBuilder tenantId(String tenantId);
	
	/**
	 * @param name : Human readable name for the Vnfd (255 characters limit). Does not have to be unique.
	 * @return VnfdBuilder
	 */
	VnfdBuilder name(String name);

	/**
	 * @param description : Human readable description for the Vnfd (1024 characters limit).
	 * @return VnfdBuilder
	 */
	VnfdBuilder description(String description);
	
	/**
	 * @param managementDriver
	 * @return VnfdBuilder
	 */
	VnfdBuilder managementDriver(String managementDriver);
	
	/**
	 * @param infrastructureDriver
	 * @return VnfdBuilder
	 */
	VnfdBuilder infrastructureDriver(String infrastructureDriver);
	
	/**
	 * @param attributes
	 * @return VnfdBuilder
	 */
	VnfdBuilder attributes(VnfdAttributes attributes);
	
	/**
	 * @param serviceTypes
	 * @return VnfdBuilder
	 */
	VnfdBuilder serviceTypes(List<VnfdServiceTypes> serviceTypes);
	
}
