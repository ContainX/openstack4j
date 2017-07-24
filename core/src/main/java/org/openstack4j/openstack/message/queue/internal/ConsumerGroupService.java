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
import org.openstack4j.openstack.message.queue.domain.ConsumerGroup;
import org.openstack4j.openstack.message.queue.domain.ConsumerGroup.ConsumerGroups;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * <h3>[DMS] Consumer Group Service </h3>
 *  consumer group operations 
 *  
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class ConsumerGroupService extends BaseMessageQueueServices implements RestService {

	/**
	 * Create a consumer group for a queue
	 * 
	 * @param queueId				the Queue identifier
	 * @param consumerGroupName		the name of the consumer group to be created
	 * @return {@link ConsumerGroup} instance
	 */
	public ConsumerGroup create(String queueId, String consumerGroupName) {
		checkNotNull(!Strings.isNullOrEmpty(queueId), "parameter `queueId` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(consumerGroupName), "parameter `consumerGroupName` should not be empty");
		List<ConsumerGroup> create = create(queueId, Lists.newArrayList(consumerGroupName));
		if (create != null && create.size() == 1) {
			return create.get(0);
		}
		// should not happen?
		return null;
	}

	/**
	 * Create consumer groups for a queue
	 * 
	 * @param queueId					the Queue identifier
	 * @param consumerGroupNames		names of the consumer group to be created
	 * @return a list of {@link ConsumerGroup} instances
	 */
	public List<ConsumerGroup> create(String queueId, List<String> consumerGroupNames) {
		checkNotNull(!Strings.isNullOrEmpty(queueId), "parameter `queueId` should not be empty");

		boolean isGroupNamesValid = consumerGroupNames != null && consumerGroupNames.size() > 0
				&& consumerGroupNames.size() <= 3;
		checkState(isGroupNamesValid, "parameter `consumerGroupNames` should be a list with 1-3 items");
		return post(ConsumerGroups.class, uri("/queues/%s/groups", queueId)).execute().getList();
	}

	/**
	 * list consumer groups of a queue
	 * @param queueId	the Queue identifier
	 * @return			A list of {@link ConsumerGroup} instances
	 */
	public List<ConsumerGroup> list(String queueId) {
		return get(ConsumerGroups.class, uri("/queues/%s/groups", queueId)).execute().getList();
	}

	/**
	 * delete a consumer group of a queue
	 * 
	 * @param queueId 			the ID of the queue 
	 * @param consumerGroupId 	the ID of the consumer-group to be deleted
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse delete(String queueId, String consumerGroupId) {
		checkNotNull(queueId, "parameter `queueId` should not be empty");
		checkNotNull(queueId, "parameter `consumerGroupId` should not be empty");
		return delete(ActionResponse.class, uri("/queues/%s/groups/%s", queueId, consumerGroupId)).execute();
	}

}
