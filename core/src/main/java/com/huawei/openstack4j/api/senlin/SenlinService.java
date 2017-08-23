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
package com.huawei.openstack4j.api.senlin;

import com.huawei.openstack4j.common.RestService;

/**
 * This interface containts all available senlinServices
 * @author lion
 *
 */
public interface SenlinService extends RestService {
	
	/**
	 * Service implementation which provides methods for manipulation of policy
	 * 
	 * @return PolicyService
	 */
	SenlinPolicyService policy();

	/**
	 * Service implementation which provides methods for manipulation of action
	 *
	 * @return ActionService
	 */
	SenlinActionService action();

	/**
	 * Service implementation which provides methods for manipulation of buildInfo
	 *
	 * @return BuildInfoService
	 */
	SenlinBuildInfoService buildInfo();

	/**
	 * Service implementation which provides methods for manipulation of cluster
	 *
	 * @return ClusterService
	 */
	SenlinClusterService cluster();

	/**
	 * Service implementation which provides methods for manipulation of clusterPolicy
	 *
	 * @return ClusterPolicyService
	 */
	SenlinClusterPolicyService clusterPolicy();

	/**
	 * Service implementation which provides methods for manipulation of event
	 *
	 * @return EventService
	 */
	SenlinEventService event();

	/**
	 * Service implementation which provides methods for manipulation of node
	 *
	 * @return NodeService
	 */
	SenlinNodeService node();

	/**
	 * Service implementation which provides methods for manipulation of profile
	 *
	 * @return ProfileService
	 */
	SenlinProfileService profile();

	/**
	 * Service implementation which provides methods for manipulation of profileType
	 *
	 * @return ProfileTypeService
	 */
	SenlinProfileTypeService profileType();

	/**
	 * Service implementation which provides methods for manipulation of policyType
	 *
	 * @return PolicyTypeService
	 */
	SenlinPolicyTypeService policyType();

	/**
	 * Service implementation which provides methods for manipulation of receiver
	 *
	 * @return ReceiverService
	 */
	SenlinReceiverService receiver();

	/**
	 * Service implementation which provides methods for manipulation of webHook
	 *
	 * @return WebHookService
	 */
	SenlinWebHookService webHook();

	/**
	 * Service implementation which provides methods for manipulation of Version
	 *
	 * @return Version
	 */
	SenlinVersionService version();

}
