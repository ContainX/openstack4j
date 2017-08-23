/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD                               
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
package com.huawei.openstack4j.openstack.key.management.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DEK implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * the key identifier used to generate the DEK
	 */
	@JsonProperty("key_id")
	String keyId;
	
	/**
	 * The plain-text of a DEK is expressed in HEX format, 
	 * and two characters indicate one byte.
	 */
	@JsonProperty("plain_text")
	String plainText;
	
	/**
	 * The ciphertext of a DEK is expressed in HEX format, 
	 * and two characters indicate one byte.
	 */
	@JsonProperty("cipher_text")
	String cipherText;


}
