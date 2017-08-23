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
package com.huawei.openstack4j.model.senlin;

/**
 * Basic cluster status
 *
 * @author lion
 */
public enum ClusterStatus {
	/** Initialization of the server */
	INIT,
	/** Server is running */
	ACTIVE,
	/** Server is creating */
	CREATING,
	/** Server is updating */
	UPDATING,
	/** Server is resizing */
	RESIZING,
	/** Server is deleting */
	DELETING,
	/** Server is checking */
	CHECKING,
	/** Server is recovering */
	RECOVERING,
	/** Server is critical */
	CRITICAL,
	/** Failed in some conditions,eg.
	 * failed to create*/
	ERROR,
	/** Warning */
	WARNING
}