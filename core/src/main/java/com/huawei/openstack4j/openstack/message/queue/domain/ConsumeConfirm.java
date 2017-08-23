/*******************************************************************************
 * 	Copyright 2017 HuaWei tld and OTC                                
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
package com.huawei.openstack4j.openstack.message.queue.domain;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.message.queue.constant.ConsumeStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent a Queue Message Consume Confirm 
 *
 * @author QianBiao.NG
 * @date   2017-07-21 16:37:17
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeConfirm implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;


	/**
	 * Indicates the message handler
	 */
	String handler;
	
	
	/**
	 * Message consumption status
	 */
	ConsumeStatus status;


}
