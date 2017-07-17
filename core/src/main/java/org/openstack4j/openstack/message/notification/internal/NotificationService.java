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

import org.openstack4j.api.Apis;
import org.openstack4j.common.RestService;

/**
 * Notification Service Entry Point
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class NotificationService extends BaseNotificationServices implements RestService {

	/**
	 * SMN Topic Service 
	 * 
	 * @return SMN Topic Service instance
	 */
	public TopicService topics() {
		return Apis.get(TopicService.class);
	}

	/**
	 * SMN Subscription Service 
	 * 
	 * @return SMN Subscription Service instance
	 */
	public SubscriptionService subscriptions() {
		return Apis.get(SubscriptionService.class);
	}

	/**
	 * SMN Message Template Service 
	 * 
	 * @return SMN Message Template Service instance
	 */
	public MessageTemplateService templates() {
		return Apis.get(MessageTemplateService.class);
	}

	/**
	 * SMN SMS Service 
	 * 
	 * @return SMN SMS Service instance
	 */
	public SmsService sms() {
		return Apis.get(SmsService.class);
	}

}
