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
package com.huawei.openstack4j.api.heat;

import com.huawei.openstack4j.common.RestService;

/**
 * This interface containts all available HeatServices
 * @author Matthias Reisser
 *
 */
public interface HeatService extends RestService {
	
	/**
	 * Service implementation which provides methods for manipulation of stacks
	 * 
	 * @return StackService
	 */
	StackService stacks();
	
	/**
	 * Service implementation which provides methods for validating Templates
	 * 
	 * @return TemplateService
	 */
	TemplateService templates();
	
	/**
	 * Service implementation which provides methods for Events
	 * 
	 * @return EventsService
	 */
	EventsService events();

	/**
	 * Service implementation which provides methods for Resources
	 * 
	 * @return ResourcesService
	 */
	ResourcesService resources();
	
	/**
	 * Service implementation which provides methods for Software Configurations
	 * 
	 * @return SoftwareConfigService
	 */
	SoftwareConfigService softwareConfig();
}
