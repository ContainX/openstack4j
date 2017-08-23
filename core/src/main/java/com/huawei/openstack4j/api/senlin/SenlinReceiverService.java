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

import java.util.List;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.senlin.Receiver;
import com.huawei.openstack4j.model.senlin.ReceiverCreate;

/**
 * This interface defines all methods for the manipulation of Receiver
 * 
 * @author lion
 * 
 */
public interface SenlinReceiverService {
	
	/**
	 * Gets a list of currently existing {@link Receiver}s.
	 * 
	 * @return the list of {@link Receiver}s
	 */
	List<? extends Receiver> list();

	/**
	 * <code>POST /v1/receivers</code><br \>
	 *
	 * Creates a new {@link Receiver} out of a {@link ReceiverCreate} object
	 *
	 * @param newReceiver
	 *            {@link ReceiverCreate} object out of which Receiver is to be created
	 * @return new {@link Receiver} as returned from the server
	 */
	Receiver create(ReceiverCreate newReceiver);

	/**
	 * returns details of a {@link Receiver}.
	 *
	 * @param receiverID
	 *            Id of {@link Receiver}
	 * @return
	 */
	Receiver get(String receiverID);

	/**
	 * Deletes the specified {@link Receiver} from the server.
	 *
	 * @param receiverID
	 *            Id of {@link Receiver}
	 * @return the action response
	 */
	ActionResponse delete(String receiverID);
}
