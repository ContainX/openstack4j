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
package com.huawei.openstack4j.model.compute;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * An OpenStack Quota-Set
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSet extends ModelEntity {

	/**
	 * @return the identifier
	 */
	String getId();

	/**
	 * @return Metadata items permitted
	 */
	int getMetadataItems();

	/**
	 * @return Injected file maximum length
	 */
	int getInjectedFileContentBytes();

	/**
	 * @return Number of inject-able files
	 */
	int getInjectedFiles();

	/**
	 * @return the number of gigabytes allowed
	 */
	int getGigabytes();

	/**
	 * @return Quantity of instanceable RAM (MBytes)
	 */
	int getRam();

	/**
	 * @return Number of floating IP
	 */
	int getFloatingIps();

	/**
	 * @return Number of permitted instances
	 */
	int getInstances();

	/**
	 * @return Number of permitted volumes
	 */
	int getVolumes();

	/**
	 * @return Number of instanceable cores
	 */
	int getCores();

	/**
	 * @return Number of security groups permitted
	 */
	int getSecurityGroups();

	/**
	 * @return Number of rules per security group permitted
	 */
	int getSecurityGroupRules();

	/**
	 * @return Injected file path name maximum length
	 */
	int getInjectedFilePathBytes();

	/**
	 * @return  Number of keypairs
	 */
	int getKeyPairs();

}