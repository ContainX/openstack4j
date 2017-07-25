/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package org.openstack4j.openstack.message.queue.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.message.queue.domain.Queue;
import org.openstack4j.openstack.message.queue.domain.Queue.Queues;

/**
 * <h3>[DMS] Queue Service </h3>
 *  queue operations 
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class QueueService extends BaseMessageQueueServices implements RestService {

	/**
	 * create a new queue
	 * 
	 * @param name			queue-name
	 * @param description	queue-display-name
	 * @return				{@link Queue} instance been created
	 */
	public Queue create(String name, String description) {
		checkNotNull(name, "parameter `name` should not be empty");
		Queue create = Queue.builder().name(name).description(description).build();
		return post(Queue.class, uri("/queues")).entity(create).execute();
	}

	/**
	 * list queues
	 * 
	 * @return			A list of {@link Queue} instances
	 */
	public List<? extends Queue> list() {
		return get(Queues.class, uri("/queues")).execute().getList();
	}

	/**
	 * get a queue by ID
	 * 
	 * @param queueId 		queue id
	 * @return {@link Queue} instance
	 */
	public Queue get(String queueId) {
		checkNotNull(queueId, "parameter `queueId` should not be empty");
		return get(Queue.class, "/queues/", queueId).execute();
	}


	/**
	 * delete a queue
	 * 
	 * @param queueId the ID of the queue to be deleted
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse delete(String queueId) {
		checkNotNull(queueId, "parameter `queueId` should not be empty");
		return delete(ActionResponse.class, uri("/queues/%s", queueId)).execute();
	}

}
