/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package org.openstack4j.model.loadbalance;

import java.util.Date;

import org.openstack4j.model.ModelEntity;

public interface Certificate extends ModelEntity {
	
	/**
	 * @return certificate id
	 */
	String getId();

	/**
	 * @return certificate name
	 */
	String getName();

	/**
	 * @return description
	 */
	String getDescription();

	/**
	 * @return certificate
	 */
	String getCertificate();

	/**
	 * @return private key
	 */
	String getPrivateKey();

	/**
	 * @return create time
	 */
	Date getCreateTime();
	
	/**
	 * @return update time
	 */
	Date getUpdateTime();
}
