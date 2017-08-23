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

import lombok.Getter;
import lombok.ToString;

/**
 * A model represent Consume Confirm Response Structure
 *
 * @author QianBiao.NG
 * @date   2017-07-21 16:37:17
 */
@Getter
@ToString
public class ConsumeConfirmResponse implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;


	/**
	 * Indicates the number of messages that are successfully confirmed. 
	 * The value N indicates that the first N messages are successfully confirmed.
	 */
	Integer success;
	
	
	/**
	 * Indicates the number of messages that failed to be confirmed. 
	 * The value N indicates that the last N messages failed to be confirmed.
	 */
	Integer fail;


}
