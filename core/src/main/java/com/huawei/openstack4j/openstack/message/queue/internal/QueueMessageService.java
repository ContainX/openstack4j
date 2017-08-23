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
package com.huawei.openstack4j.openstack.message.queue.internal;

import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.message.queue.constant.ConsumeStatus;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeConfirm;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeConfirmResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.Queue;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessage;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessageWithHandler;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessage.QueueMessages;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessageWithHandler.QueueMessageWithHandlers;

/**
 * <h3>[DMS] Message Service </h3>
 * message queue message operations 
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class QueueMessageService extends BaseMessageQueueServices implements RestService {

	/**
	 * produce a message to a {@link Queue},
	 * 
	 * <li> The load of messages sent each time cannot exceed 512 KB. </li>
	 * <li> The retention period of each message is 72 hours. </li>
	 * 
	 * @param queueId		the ID of the Queue
	 * @param message		the message to produce
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse produce(String queueId, QueueMessage message) {
		checkNotNull(!Strings.isNullOrEmpty(queueId), "parameter `queueId` should not be empty");
		checkNotNull(message, "parameter `message` should not be null");
		checkNotNull(message.getBody(), "parameter `message.body` should not be null");

		return produce(queueId, Lists.newArrayList(message));
	}

	/**
	 * produce a list of message to a {@link Queue},
	 * 
	 * <li> The load of messages sent each time cannot exceed 512 KB. </li>
	 * <li> The retention period of each message is 72 hours. </li>
	 * 
	 * @param queueId		the ID of the Queue
	 * @param messages		the messages to produce
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse produce(String queueId, List<QueueMessage> messages) {
		checkNotNull(!Strings.isNullOrEmpty(queueId), "parameter `queueId` should not be empty");
		checkArgument(messages != null && messages.size() > 0, "parameter `message` should not be empty");
		for (int i = 0; i < messages.size(); i++) {
			QueueMessage message = messages.get(i);
			checkNotNull(message, String.format("the item at position %d of message is invalid", i));
			checkNotNull(message.getBody(), String.format("the item at position %d of message is invalid", i));
		}

		QueueMessages body = new QueueMessages(messages);
		return postWithResponse(uri("/queues/%s/messages", queueId)).entity(body).execute();
	}

	/**
	 * 
	 * consume messages of a Queue
	 * 
	 * @param queueId				the ID of the Queue
	 * @param consumerGroupId		the consumer group ID which is consuming the message
	 * @param maxMessages 			Indicates the number of consumable messages that can be obtained per time. (Value range: 1-10)
	 * @param timeWait				Indicates the amount of time that the API call can wait for a message to arrive 
	 * 								in the empty queue before returning an empty response. (Value range: 1–60s Default value: 3s)
	 * @return  a list of {@link QueueMessage}
	 */
	public List<QueueMessageWithHandler> consume(String queueId, String consumerGroupId, Integer maxMessages,
			Integer timeWait) {
		checkNotNull(!Strings.isNullOrEmpty(queueId), "parameter `queueId` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(consumerGroupId), "parameter `consumerGroupId` should not be empty");
		return get(QueueMessageWithHandlers.class, uri("/queues/%s/groups/%s/messages", queueId, consumerGroupId))
				.param("max_msgs", maxMessages).param("time_wait", timeWait).execute().getList();
	}

	/**
	 * 
	    This API is used to confirm the consumption of specified messages.
	    
	    <br/>
	    
		While a message is being consumed, it remains in the queue and cannot be consumed again
		within 30s since the consumption started. If the consumption is not confirmed within this
		period, message can be consumed again.
		
		<br/>
		
		If a message is successfully confirmed by a consumer group, the message cannot be reconsumed by the same group. However, message can still be consumed by other consumer
		groups. It is retained in the queue for 72 hours (unless the queue is deleted) and will be
		deleted after this period.
		
		<br/>
		
		After a batch of messages is consumed, consumers must submit their consumption
		confirmations by strictly following the message consumption sequence. DMS sequentially
		checks whether messages are successfully consumed. If DMS finds that a message is not
		confirmed as a consumed message or fails to be consumed, DMS stops checking but directly
		determines that all the subsequent messages fail to be consumed. Therefore, when a consumer
		fails to consume a message (in a batch of messages), you are advised to stop the consumer
		from consuming the rest messages, and directly submit consumption confirmations of the
		successfully consumed messages to DMS.
	
	 * @param queueId					the ID of the Queue
	 * @param consumerGroupId			the consumer group ID which is consuming the message
	 * @param consumeResult				A Map indicates the result of consuming. Key is message handler and value is consume status
	 * @return
	 */
	public ConsumeConfirmResponse confirmConsuming(String queueId, String consumerGroupId,
			Map<String, ConsumeStatus> consumeResult) {
		checkNotNull(!Strings.isNullOrEmpty(queueId), "parameter `queueId` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(consumerGroupId), "parameter `consumerGroupId` should not be empty");
		checkArgument(consumeResult != null && consumeResult.size() > 0,
				"parameter `consumeResult` should not be empty");

		List<ConsumeConfirm> confirms = new ArrayList<>();
		for (Entry<String, ConsumeStatus> entry : consumeResult.entrySet()) {
			String handler = entry.getKey();
			ConsumeStatus status = entry.getValue();
			checkNotNull(!Strings.isNullOrEmpty(handler), "parameter `consumeResult` contains illegal key");
			checkNotNull(status, "parameter `consumeResult` contains illegal value");
			ConsumeConfirm confirm = ConsumeConfirm.builder().handler(handler).status(status).build();
			confirms.add(confirm);
		}

		// build body
		HashMap<String, Object> body = Maps.newHashMap();
		body.put("message", confirms);
		return post(ConsumeConfirmResponse.class, uri("/queues/%s/groups/%s/ack", queueId, consumerGroupId)).entity(body)
				.execute();
	}

}
