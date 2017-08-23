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
package com.huawei.openstack4j.model.identity.v2;

/**
 * A version 2 token which is used during authentication allowing follow up calls to only supply the assigned token within the header avoiding re-authentication
 * 
 * @author Jeremy Unruh
 * @see http://docs.openstack.org/api/openstack-identity-service/2.0/content/POST_admin-authenticate_v2.0_tokens_Token_Operations.html
 */
public interface TokenV2 extends Token {

	/**
	 * @return the tenant associated with the original authentication request
	 */
	Tenant getTenant();
	
}
