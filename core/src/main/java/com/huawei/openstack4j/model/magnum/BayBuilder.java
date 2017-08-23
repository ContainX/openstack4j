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
package com.huawei.openstack4j.model.magnum;

import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.openstack.common.GenericLink;

public interface BayBuilder extends Builder<BayBuilder, Bay> {
	/**
	 * @see Bay#getStatus
	 */
	BayBuilder status(String status);

	/**
	 * @see Bay#getUuid
	 */
	BayBuilder uuid(String uuid);

	/**
	 * @see Bay#getLinks
	 */
	BayBuilder links(List<GenericLink> links);

	/**
	 * @see Bay#getStackId
	 */
	BayBuilder stackId(String stackId);

	/**
	 * @see Bay#getCreatedAt
	 */
	BayBuilder createdAt(String createdAt);

	/**
	 * @see Bay#getApiAddress
	 */
	BayBuilder apiAddress(String apiAddress);

	/**
	 * @see Bay#getDiscoveryUrl
	 */
	BayBuilder discoveryUrl(String discoveryUrl);

	/**
	 * @see Bay#getUpdatedAt
	 */
	BayBuilder updatedAt(String updatedAt);

	/**
	 * @see Bay#getMasterCount
	 */
	BayBuilder masterCount(Integer masterCount);

	/**
	 * @see Bay#getCoeVersion
	 */
	BayBuilder coeVersion(String coeVersion);

	/**
	 * @see Bay#getBaymodelId
	 */
	BayBuilder baymodelId(String baymodelId);

	/**
	 * @see Bay#getMasterAddresses
	 */
	BayBuilder masterAddresses(List<String> masterAddresses);

	/**
	 * @see Bay#getNodeCount
	 */
	BayBuilder nodeCount(Integer nodeCount);

	/**
	 * @see Bay#getNodeAddresses
	 */
	BayBuilder nodeAddresses(List<String> nodeAddresses);

	/**
	 * @see Bay#getStatusReason
	 */
	BayBuilder statusReason(String statusReason);

	/**
	 * @see Bay#getBayCreateTimeout
	 */
	BayBuilder bayCreateTimeout(String bayCreateTimeout);

	/**
	 * @see Bay#getName
	 */
	BayBuilder name(String name);

}
