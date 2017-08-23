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
package com.huawei.openstack4j.model.identity;

import com.huawei.openstack4j.model.identity.AuthVersion;

/**
 * An entity which holds enough information in store to re-authenticate at any given time during a session.  This is a generic mapping which provides the common 
 * information needed for authentication.  Version dependent attributes can be found via the {@link #unwrap()} call returning the real typed object   
 * 
 * @author Jeremy Unruh
 */
public interface AuthStore {

	/**
	 * @return the version of this authentication store type
	 */
	AuthVersion getVersion();
	
	/**
	 * @return the username used to authenticate
	 */
	String getUsername();
	
	/**
	 * @return the password used to authenticate
	 */
	String getPassword();
	
	/**
	 * If this is a {@link AuthVersion#V2} then this is the tenantId.  If {@link AuthVersion#V3} then this maps to the projectId
	 * 
	 * @return the tenantId (V2) or projectId for V3
	 */
	String getId();
	
	/**
     * If this is a {@link AuthVersion#V2} then this is the tenant name.  If {@link AuthVersion#V3} then this maps to the project name
     * 
     * @return the tenant name (V2) or project name for V3
     */
	String getName();
	
}
