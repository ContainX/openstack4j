/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.api.exceptions;

import com.google.common.base.MoreObjects;

/**
 * Captures Server based Errors (Return Codes between 400 - 499)
 *
 * @author Jeremy Unruh
 */
public class ClientResponseException extends ResponseException {

	private static final long serialVersionUID = 1L;

	private StatusCode code;

	public ClientResponseException(String message, int status, Throwable cause) {
		super(message, status, cause);
		code = StatusCode.fromCode(status);
	}

	public ClientResponseException(String message, int status) {
		super(message, status);
		code = StatusCode.fromCode(status);
	}


	/**
	 * @return the status code mapping for the current {@link #getStatus()}
	 */
	public StatusCode getStatusCode() {
		return code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("message", getMessage()).add("status", getStatus()).add("status-code", code)
				     .toString();
	}
}
