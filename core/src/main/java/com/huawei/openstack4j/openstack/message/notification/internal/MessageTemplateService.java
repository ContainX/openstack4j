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
package com.huawei.openstack4j.openstack.message.notification.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplate;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplateCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplate.MessageTemplates;
import com.huawei.openstack4j.openstack.message.notification.options.MessageTemplateListOptions;

/**
 * Notification MessageTemplate Service
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class MessageTemplateService extends BaseNotificationServices implements RestService {

	/**
	 * list message_template by filter options
	 * 
	 * @param options	a modal contains attributes for querying filter options
	 * @return			A list of {@linkplain MessageTemplate} instances
	 */
	public List<? extends MessageTemplate> list(MessageTemplateListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(MessageTemplates.class, uri("/notifications/message_template")).params(params).execute().getList();
	}

	/**
	 * get a message-template by id
	 * 
	 * @param messageTemplateId the id of the message-template to fetch
	 * @return {@linkplain MessageTemplate} instance
	 */
	public MessageTemplate get(String messageTemplateId) {
		checkNotNull(messageTemplateId, "parameter `messageTemplateId` should not be empty");
		return get(MessageTemplate.class, "/notifications/message_template/", messageTemplateId).execute();
	}

	/**
	 * create a new message-template
	 * 
	 * @param create	model contains attributes required by creating a message-template
	 * @return			{@linkplain MessageTemplate} instance (URN) been created
	 */
	public MessageTemplate create(MessageTemplateCreate create) {
		checkNotNull(create, "parameter `create` should not be null");
		checkNotNull(!Strings.isNullOrEmpty(create.getName()), "parameter `create.name` should not be null");
		checkNotNull(!Strings.isNullOrEmpty(create.getContent()), "parameter `create.content` should not be null");
		checkNotNull(create.getProtocol(), "parameter `create.protocol` should not be null");
		return post(MessageTemplate.class, uri("/notifications/message_template")).entity(create).execute();
	}

	/**
	 * update message-template's content
	 * 
	 * @param messageTemplateId		the id of the message-template to be updated		
	 * @param content				updated template content
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest updateContent(String messageTemplateId, String content) {
		checkNotNull(!Strings.isNullOrEmpty(messageTemplateId), "parameter `messageTemplateId` should not be empty");
		checkNotNull(!Strings.isNullOrEmpty(content), "parameter `content` should not be empty");
		MessageTemplate update = MessageTemplate.builder().content(content).build();
		return put(TracableRequest.class, "/notifications/message_template/", messageTemplateId).entity(update)
				.execute();
	}

	/**
	 * delete a message-template
	 * 
	 * @param messageTemplateId the id of the message-template to be deleted
	 * @return {@link TracableRequest} instance
	 */
	public TracableRequest delete(String messageTemplateId) {
		checkNotNull(messageTemplateId, "parameter `messageTemplateId` should not be empty");
		return delete(TracableRequest.class, "/notifications/message_template/", messageTemplateId).execute();
	}

}
