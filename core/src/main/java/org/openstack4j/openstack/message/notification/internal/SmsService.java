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

import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.message.notification.domain.MessageIdResponse;

import com.google.common.base.Strings;

import com.beust.jcommander.internal.Maps;

/**
 * Notification SMS Service 
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class SmsService extends BaseNotificationServices implements RestService {

	/**
	 * send a SMS
	 * 
	 * @param receiver			phone number	(required)
	 * @param message			message content (required)
	 * @param smsSignId			SMS sign ID 	(optional)
	 * @return	{@link MessageIdResponse} instance
 	 */
	public MessageIdResponse send(String receiver, String message, String smsSignId) {
		checkNotNull(!Strings.isNullOrEmpty(receiver), "parameter `receiver` should not be null");
		checkNotNull(!Strings.isNullOrEmpty(message), "parameter `message` should not be null");
		if (!receiver.startsWith("+")) {
			receiver = "+" + receiver;
		}

		Map<String, Object> body = Maps.newHashMap();
		body.put("endpoint", receiver);
		body.put("message", message);
		if (!Strings.isNullOrEmpty(smsSignId)) {
			body.put("sign_id", smsSignId);
		}
		return post(MessageIdResponse.class, uri("/notifications/sms")).entity(body).execute();
	}

}
