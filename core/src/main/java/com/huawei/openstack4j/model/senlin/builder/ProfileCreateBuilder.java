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
package com.huawei.openstack4j.model.senlin.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.senlin.ProfileCreate;

/**
 * This interface describes a builder for {@link ProfileCreate} objects
 * 
 * @author lion
 */
public interface ProfileCreateBuilder extends Buildable.Builder<ProfileCreateBuilder, ProfileCreate> {

	/**
	 *  Add the name for the profile.
	 *
	 * @param name The name for the profile.
	 * @return ProfileCreateBuilder
	 */
	ProfileCreateBuilder name(String name);

	/**
	 *  Add detailed specification based on the chosen profile type.
	 *
	 * @param spec The detailed specification based on the chosen profile type.
	 * @return ProfileCreateBuilder
	 */
	ProfileCreateBuilder spec(Map<String, Object> spec);

	/**
	 *  Add a list of key and value pairs to associate with the profile.
	 *
	 * @param metadata The list of key and value pairs to associate with the profile.
	 * @return ProfileCreateBuilder
	 */
	ProfileCreateBuilder metadata(Map<String, Map> metadata);


}
