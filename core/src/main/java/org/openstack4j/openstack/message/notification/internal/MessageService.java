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
package org.openstack4j.openstack.message.notification.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;

import org.openstack4j.common.RestService;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.openstack.message.notification.domain.Message;
import org.openstack4j.openstack.message.notification.domain.MessageIdResponse;
import org.openstack4j.openstack.message.notification.domain.StructuredMessage;
import org.openstack4j.openstack.message.notification.domain.TemplatedMessage;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Notification Message Service
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class MessageService extends BaseNotificationServices implements RestService {

	/**
	 * publish a message to a topic
	 * 
	 * @param topicUrn 		the topic URN (id)
	 * @param subject		the message subject (EMAIL subject)
	 * @param message		the message content
	 * @return	{@linkplain MessageIdResponse} instance
	 */
	public MessageIdResponse publish(String topicUrn, String subject, String message) {
		checkNotNull(!Strings.isNullOrEmpty(topicUrn), "parameter `topicUrn` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(message), "parameter `message` should not be empty");

		Message msg = Message.builder().subject(subject).message(message).build();
		return post(MessageIdResponse.class, uri("/notifications/topics/%s/publish", topicUrn)).entity(msg).execute();
	}

	/**
	 * publish a templated message to a topic 
	 * 
	 * @param topicUrn		the topic URN (id)
	 * @param message		the templated message modal
	 * @return	{@linkplain MessageIdResponse} instance
	 */
	public MessageIdResponse publish(String topicUrn, TemplatedMessage message) {
		checkNotNull(!Strings.isNullOrEmpty(topicUrn), "parameter `topicUrn` should not be null");
		checkNotNull(message, "parameter `message` should not be null");
		checkNotNull(!Strings.isNullOrEmpty(message.getMessageTemplateName()),
				"parameter `message.messageTemplateName` should not be empty");
		return post(MessageIdResponse.class, uri("/notifications/topics/%s/publish", topicUrn)).entity(message).execute();
	}

	/**
	 * publish a structured message to a topic
	 * @param topicUrn		the topic URN (id)
	 * @param message		the structured message modal
	 * @return	{@linkplain MessageIdResponse} instance
	 */
	public MessageIdResponse publish(String topicUrn, StructuredMessage message) {
		checkNotNull(!Strings.isNullOrEmpty(topicUrn), "parameter `topicUrn` should not be null");
		checkNotNull(message, "parameter `message` should not be null");
		checkNotNull(!Strings.isNullOrEmpty(message.getDefaultMessage()),
				"parameter `message.defaultMessage` should not be empty");
		try {
			String subject = message.getSubject();
			String payload = ObjectMapperSingleton.getContext(StructuredMessage.class).writeValueAsString(message);
			
			HashMap<Object, Object> body = Maps.newHashMap();
			body.put("message_structure", payload);
			if(!Strings.isNullOrEmpty(subject)) {
				body.put("subject", subject);
			}
			return post(MessageIdResponse.class, uri("/notifications/topics/%s/publish", topicUrn)).entity(body).execute();
		} catch (JsonProcessingException e) {
			// should not happen
			throw new RuntimeException(e);
		}
	}

}
