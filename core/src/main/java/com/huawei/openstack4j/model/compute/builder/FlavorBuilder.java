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
package com.huawei.openstack4j.model.compute.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.compute.Flavor;

/**
 * Builder for a Flavor model class
 * 
 * @author Jeremy Unruh
 */
public interface FlavorBuilder extends Builder<FlavorBuilder, Flavor> {

	/**
	 * @see Flavor#getName()
	 */
	FlavorBuilder name(String name);
	
	/**
	 * @see Flavor#getRam()
	 */
	FlavorBuilder ram(int ram);
	
	/**
	 * @see Flavor#getVcpus()
	 */
	FlavorBuilder vcpus(int vcpus);
	
	/**
	 * @see Flavor#getDisk()
	 */
	FlavorBuilder disk(int disk);
	
	/**
	 * @see Flavor#getSwap()
	 */
	FlavorBuilder swap(int swap);
	
	/**
	 * @see Flavor#getRxtxFactor();
	 */
	FlavorBuilder rxtxFactor(float rxtxFactor);

	/**
	 * @see Flavor#isPublic()
	 */
	FlavorBuilder isPublic(boolean isPublic);
		
	/**
	 * @see Flavor#getEphemeral()
	 */
	FlavorBuilder ephemeral(int ephemeral);
	
	/**
	 *@see Flavor#getId() 
	 */
	FlavorBuilder id(String id);
}
