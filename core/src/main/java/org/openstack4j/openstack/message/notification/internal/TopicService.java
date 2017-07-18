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
import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.message.notification.constant.TopicAttributeName;
import org.openstack4j.openstack.message.notification.domain.Topic;
import org.openstack4j.openstack.message.notification.domain.Topic.Topics;
import org.openstack4j.openstack.message.notification.domain.TopicAttributes;
import org.openstack4j.openstack.message.notification.domain.TopicAttributes.TopicAttributesUnwapper;
import org.openstack4j.openstack.message.notification.domain.TracableRequest;

import com.google.common.collect.Maps;

/**
 * Notification Service Entry Point
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class TopicService extends BaseNotificationServices implements RestService {

	/**
	 * list topics with pagination
	 * 
	 * @param limit		pagination limit (optional)
	 * @param offset	pagination offset (optional)
	 * @return			A list of topic instances
	 */
	public List<? extends Topic> list(Integer limit, Integer offset) {
		return get(Topics.class, uri("/notifications/topics")).param("limit", limit).param("offset", offset).execute()
				.getList();
	}

	/**
	 * get a topic by URN 
	 * 
	 * @param topicUrn 	topic urn
	 * @return topic details
	 */
	public Topic get(String topicUrn) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		return get(Topic.class, "/notifications/topics/", topicUrn).execute();
	}

	/**
	 * create a new topic
	 * 
	 * @param name			topic-name
	 * @param displayName	topic-display-name
	 * @return				topic instance (URN) been created
	 */
	public Topic create(String name, String displayName) {
		checkNotNull(name, "parameter `name` should not be empty");
		Topic create = Topic.builder().name(name).displayName(displayName).build();
		return post(Topic.class, uri("/notifications/topics")).entity(create).execute();
	}

	/**
	 * update topic's display name
	 * 
	 * @param topicUrn		the URN of the topic		
	 * @param displayName	display-name to be updated
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest updateDisplayName(String topicUrn, String displayName) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		checkNotNull(displayName, "parameter `displayName` should not be empty");
		Topic update = Topic.builder().displayName(displayName).build();
		return put(TracableRequest.class, "/notifications/topics/", topicUrn).entity(update).execute();
	}

	/**
	 * delete a topic
	 * 
	 * @param topicUrn the URN of the topic to be deleted
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest delete(String topicUrn) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		return delete(TracableRequest.class, uri("/notifications/topics/%s", topicUrn)).execute();
	}

	/**
	 * list topic attributes
	 * 
	 * @param topicUrn the topic (URN) attributes to fetch 
	 * @return TopicAttributes instance
	 */
	public TopicAttributes getTopicAttributes(String topicUrn) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		return get(TopicAttributesUnwapper.class, "/notifications/topics/", topicUrn, "/attributes").execute()
				.getTopicAttributes();
	}

	/**
	 * get topic attribute
	 * 
	 * @param topicUrn the topic (URN) attribute to fetch 
	 * @param attributeName the attribute-name of the topic attributes 
	 * @return TopicAttributes instance
	 */
	public String getTopicAttribute(String topicUrn, TopicAttributeName attributeName) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		checkNotNull(attributeName, "parameter `attributeName` should not be empty");
		TopicAttributesUnwapper unwrapper = get(TopicAttributesUnwapper.class, "/notifications/topics/", topicUrn,
				"/attributes").param("attributes_name", attributeName.value()).execute();
		TopicAttributes attributes = unwrapper.getTopicAttributes();
		switch (attributeName) {
		case AccessPolicy:
			return attributes.getAccessPolicy();
		case Introduction:
			return attributes.getIntroduction();
		case SMSSignId:
			return attributes.getSmsSignId();
		default:
			return null;
		}
	}

	/**
	 * update topic attribute
	 * 
	 * @param topicUrn the topic (URN) attribute to update 
	 * @param attributeName the attribute name of the topic attributes 
	 * @param attributeValue the attribute value to update
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest updateTopicAttribute(String topicUrn, TopicAttributeName attributeName,
			String attributeValue) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		checkNotNull(attributeName, "parameter `attributeName` should not be empty");
		checkNotNull(attributeValue, "parameter `attributeValue` should not be empty");

		HashMap<Object, Object> body = Maps.newHashMap();
		body.put("value", attributeValue);
		return put(TracableRequest.class,
				uri("/notifications/topics/%s/attributes/%s", topicUrn, attributeName.value())).entity(body).execute();
	}

	/**
	 * 
	 * delete a topic attribute
	 * 
	 * @param topicUrn the topic (URN) attribute to delete 
	 * @param attributeName the attribute name of the topic attributes 
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest deleteTopicAttribute(String topicUrn, TopicAttributeName attributeName) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		checkNotNull(attributeName, "parameter `attributeName` should not be empty");

		return delete(TracableRequest.class,
				uri("/notifications/topics/%s/attributes/%s", topicUrn, attributeName.value())).execute();
	}

	/**
	 * 
	 * delete all topic attributes
	 * 
	 * @param topicUrn the topic (URN) attribute to delete 
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest deleteTopicAttributes(String topicUrn) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be empty");
		return delete(TracableRequest.class, uri("/notifications/topics/%s/attributes", topicUrn)).execute();
	}

}
