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
package com.huawei.openstack4j.common;


/**
 * Model classes decorated with this interface are compatible for write/update operations and can be created via the Builder API.  
 * 
 * @author Jeremy Unruh
 */
public interface Buildable<B> {

	B toBuilder();
	
	/**
	 * Builder used to create/build corresponding Model Entity 
	 * 
	 * @param <T> The Builder providing the creation of M
	 * @param <M> The ouput IModelEntity type
	 * 
	 * @author Jeremy Unruh
	 */
	public interface Builder<T extends Builder<T, M>, M extends Buildable<?>> {

		/**
		 * Creates and return the Model Entity M
		 *
		 * @return M instance
		 */
		M build();
		
		/**
		 * Creates a Builder using the param M as the default values
		 *
		 * @param The Model M
		 * @return Builder
		 */
		 T from(M in);
	}
}
