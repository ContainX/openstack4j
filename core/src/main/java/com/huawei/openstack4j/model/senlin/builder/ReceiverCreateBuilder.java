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
import com.huawei.openstack4j.model.senlin.ReceiverCreate;

/**
 * This interface describes a builder for {@link ReceiverCreate} objects
 * 
 * @author lion
 */
public interface ReceiverCreateBuilder extends Buildable.Builder<ReceiverCreateBuilder, ReceiverCreate> {

	/**
	 *  Add the action to initiate when the receiver is triggered.
	 *  A valid value should be the name of an action that can be applied on a cluster.
	 *
	 * @param action The action to initiate.
	 * @return ReceiverCreateBuilder
	 */
	ReceiverCreateBuilder action(String action);

	/**
	 *  Add the name, ID, or short ID of the object targeted by the receiver
	 *
	 * @param clusterID The Name, ID, or short ID of the object targeted by the receiver
	 * @return ReceiverCreateBuilder
	 */
	ReceiverCreateBuilder clusterID(String clusterID);

	/**
	 *  Add the name for the receiver
	 *
	 * @param name The name for the receiver
	 * @return ReceiverCreateBuilder
	 */
	ReceiverCreateBuilder name(String name);

	/**
	 *  Add the type of the receiver where the only valid value is webhook currently
	 *
	 * @param type The type of the receiver
	 * @return ReceiverCreateBuilder
	 */
	ReceiverCreateBuilder type(String type);

	/**
	 *  Add the map of key and value pairs to use for action creation.
	 *  Some actions might require certain input parameters
	 *
	 * @param params A map of key and value pairs to use for action creation.
	 * @return ReceiverCreateBuilder
	 */
	ReceiverCreateBuilder params(Map<String, String> params);

}
