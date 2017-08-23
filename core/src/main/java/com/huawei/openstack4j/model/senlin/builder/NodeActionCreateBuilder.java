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
import com.huawei.openstack4j.model.senlin.NodeActionCreate;

/**
 * This interface describes a builder for {@link NodeActionCreate} objects
 * 
 * @author lion
 */
public interface NodeActionCreateBuilder extends Buildable.Builder<NodeActionCreateBuilder, NodeActionCreate> {

	/**
	 *  Check the health status of a node
	 *
	 * @param check check info
	 * @return NodeActionCreateBuilder
	 */
	NodeActionCreateBuilder check(Map<String, String> check);

	/**
	 *  Recover a node from its current unhealthy status
	 *
	 * @param recover the operation of node
	 * @return NodeActionCreateBuilder
	 */
	NodeActionCreateBuilder recover(Map<String, String> recover);

}
