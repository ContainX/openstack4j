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

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.message.notification.domain.Subscription;
import org.openstack4j.openstack.message.notification.domain.Subscription.Subscriptions;
import org.openstack4j.openstack.message.notification.domain.SubscriptionCreate;
import org.openstack4j.openstack.message.notification.domain.TracableRequest;
import org.testng.util.Strings;

/**
 * Notification Subscription Service
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class SubscriptionService extends BaseNotificationServices implements RestService {

	/**
	 * list subscriptions with pagination, ordered by subscription created time ASC
	 * 
	 * @param limit		pagination limit (optional)
	 * @param offset	pagination offset (optional)
	 * @return			A list of subscription instances
	 */
	public List<? extends Subscription> list(Integer limit, Integer offset) {
		return get(Subscriptions.class, uri("/notifications/subscriptions")).param("limit", limit)
				.param("offset", offset).execute().getList();
	}

	/**
	 * list subscriptions by topic with pagination, ordered by subscription created time ASC
	 * 
	 * @param topicUrn	the topic URN of the subscriptions (required)
	 * @param limit		pagination limit (optional)
	 * @param offset	pagination offset (optional)
	 * @return			A list of subscription instances
	 */
	public List<? extends Subscription> listByTopic(String topicUrn, Integer limit, Integer offset) {
		checkNotNull(topicUrn, "parameter `topicUrn` should not be null");
		return get(Subscriptions.class, uri("/notifications/topics/%s/subscriptions", topicUrn)).param("limit", limit)
				.param("offset", offset).execute().getList();
	}

	/**
	 * subscribe topic
	 * 
	 * @param subscribe model contains attribute required by creating a subscription
	 * @return Subscription instance (include URN and request-id)
	 */
	public Subscription subscribe(SubscriptionCreate subscribe) {
		checkNotNull(subscribe, "parameter `subscribe` should not be null");
		checkNotNull(!Strings.isNullOrEmpty(subscribe.getTopicUrn()),
				"parameter `subscribe.topicUrn` should not be empty");
		checkNotNull(subscribe.getProtocol(), "parameter `subscribe.protocol` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(subscribe.getEndpoint()),
				"parameter `subscribe.endpoint` should not be empty");
		return post(Subscription.class, uri("/notifications/topics/%s/subscriptions", subscribe.getTopicUrn()))
				.entity(subscribe).execute();
	}


	/**
	 * unsubscribe topic
	 * 
	 * @param subscriptionUrn the URN of the subscription
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest unsubscribe(String subscriptionUrn) {
		checkNotNull(subscriptionUrn, "parameter `subscriptionUrn` should not be empty");
		return delete(TracableRequest.class, uri("/notifications/subscriptions/%s", subscriptionUrn)).execute();
	}
	
	/**
	 * confirm from a topic
	 * 
	 * @param subscriptionUrn the URN of the subscription
	 * @return {@link TracableRequest} instance
	public TracableRequest confirm(String subscriptionUrn) {
		checkNotNull(subscriptionUrn, "parameter `subscriptionUrn` should not be empty");
		return delete(TracableRequest.class, uri("/notifications/subscriptions/%s", subscriptionUrn)).execute();
	} */

}
