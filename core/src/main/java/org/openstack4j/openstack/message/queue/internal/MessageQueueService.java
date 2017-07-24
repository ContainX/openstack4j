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

import org.openstack4j.api.Apis;
import org.openstack4j.common.RestService;

/**
 * MessageQueue Service Entry Point
 *
 * @author QianBiao.NG
 * @date   2017-07-17 09:35:34
 */
public class MessageQueueService extends BaseMessageQueueServices implements RestService {

	/**
	 * DMS Queue Service 
	 * 
	 * @return {@link QueueService} instance
	 */
	public QueueService queue() {
		return Apis.get(QueueService.class);
	}
	
	/**
	 * DMS Consumer Group Service 
	 * 
	 * @return {@link ConsumerGroupService} instance
	 */
	public ConsumerGroupService consumerGroups() {
		return Apis.get(ConsumerGroupService.class);
	}
	
	/**
	 * DMS Message Service 
	 * 
	 * @return {@link QueueMessageService} instance
	 */
	public QueueMessageService messages() {
		return Apis.get(QueueMessageService.class);
	}
	
	/**
	 * DMS Quota Service 
	 * 
	 * @return {@link MessageQueueQuotaService} instance
	 */
	public MessageQueueQuotaService quotas() {
		return Apis.get(MessageQueueQuotaService.class);
	}


}
