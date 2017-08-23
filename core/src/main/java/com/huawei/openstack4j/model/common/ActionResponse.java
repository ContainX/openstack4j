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
package com.huawei.openstack4j.model.common;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * A response that is returned when an Action is performed against the server.  If {@link #isSuccess()} is true then the fault will always be null. The fault
 * will indicate why the action has failed.  Any other transport/communication errors will be thrown as with any other API calls.
 *
 * @author Jeremy Unruh
 */
public class ActionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	String message;
	int code;

	private ActionResponse(int code) {
	    this.code = code;
	}
	private ActionResponse(String message, int code) {
	    this(code);
		this.message = message;
	}

	public static ActionResponse actionSuccess() {
		return new ActionResponse(200);
	}

	public static ActionResponse actionFailed(String message, int code) {
		return new ActionResponse(message, code);
	}

	/**
	 * Returns the underlying error code (status code)
	 *
	 * @return the error code
	 */
	public int getCode() {
	    return code;
	}

	/**
	 * @return true if the action was successful
	 */
	public boolean isSuccess() {
		return message == null;
	}

	/**
	 * @return the fault if the action was unsuccessful otherwise null
	 */
	public String getFault() {
		return message;
	}
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("success", message == null).add("fault", message).add("code", code).toString();
	}

}
