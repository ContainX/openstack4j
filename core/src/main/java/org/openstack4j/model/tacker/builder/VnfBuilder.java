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

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.tacker.Vnf;
import org.openstack4j.openstack.tacker.domain.VnfAttributes;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
public interface VnfBuilder extends Builder<VnfBuilder, Vnf> {

	/**
	 * @param tenantId : Owner of the Vnf. Only an administrative user can specify a tenant ID other than its own.
	 * @return VnfBuilder
	 */
	VnfBuilder tenantId(String tenantId);
	
	/**
	 * @param name : Human readable name for the Vnf (255 characters limit). Does not have to be unique.
	 * @return VnfBuilder
	 */
	VnfBuilder name(String name);

	/**
	 * @param description : Human readable description for the Vnf (1024 characters limit).
	 * @return VnfBuilder
	 */
	VnfBuilder description(String description);
	
	/**
	 * @param attributes
	 * @return VnfBuilder
	 */
	VnfBuilder attributes(VnfAttributes attributes);
	
	/** 
	 * @param vnfdId
	 * @return VnfBuilder
	 */
	VnfBuilder vnfdId(String vnfdId);
	
	/** 
	 * @param vimId
	 * @return VnfBuilder
	 */
	VnfBuilder vimId(String vimId);
	
}
