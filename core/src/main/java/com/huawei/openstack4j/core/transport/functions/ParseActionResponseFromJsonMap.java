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
package com.huawei.openstack4j.core.transport.functions;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Strings;

import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.common.ActionResponse;

/**
 * Attempts to Parse a JSON Map created from an error response and map the message to an ActionResponse.  
 * 
 * @author Jeremy Unruh
 */
public class ParseActionResponseFromJsonMap implements Function<Map<String, Object>, ActionResponse> {

	private static final String KEY_MESSAGE = "message";
	private static final String NEUTRON_ERROR = "NeutronError";

	/** For 'computeFault' Error Message Propagation.. */
	private static final String COMPUTE_FAULT = "computeFault";
	/** For 'TackerError' Error Message Propagation.. */
	private static final String TACKER_ERROR = "TackerError";
	/** For HuaWei CloudEye Error Message Propagation.. */
	private static final String CLOUDEYE_ERROR = "details";
	/** For HuaWei SAHARA Error Message Propagation.. */
	private static final String SAHARA_ERROR = "error_message";
	private static final String KEY_MANAGEMENT_ERROR_MSG = "error_msg";
	private static final String TROVE_ERROR_CODE = "errCode";
	private static final String TROVE_ERROR_MSG = "externalMessage";

	private static final String[] KEY_CODE_LIST = { TROVE_ERROR_CODE, "code", "errorCode", "error_code", "Code",  };
	private static final String[] KEY_MESSAGE_LIST = { TROVE_ERROR_MSG, KEY_MESSAGE, NEUTRON_ERROR, COMPUTE_FAULT, TACKER_ERROR,
			CLOUDEYE_ERROR, SAHARA_ERROR, KEY_MANAGEMENT_ERROR_MSG, "Message",  };
	private HttpResponse response;

	public ParseActionResponseFromJsonMap(HttpResponse response) {
		this.response = response;
	}

	/**
	 * Parses the JSON Map for an Error message.  An OpenStack error response typically is a Map of Map containing a single key
	 * which is "error", "badRequest", etc which contains a value of another Map containing the underlying message
	 * 
	 * @param map the JSON Map 
	 * @return ActionResponse or null if the map could not be parsed
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ActionResponse apply(Map<String, Object> map) {
		if (map == null || map.isEmpty())
			return null;

		for (String key : map.keySet()) {
			if (Map.class.isAssignableFrom(map.get(key).getClass())) {
				Map<String, Object> inner = (Map<String, Object>) map.get(key);

				// detect HuaWei OTC error code
				String errorCode = "";
				for (String codeKey : KEY_CODE_LIST) {
					if (inner.containsKey(codeKey)) {
						errorCode = "[" + String.valueOf(inner.get(codeKey)) + "] ";
						break;
					}
				}

				String msg = "";
				for (String messageKey : KEY_MESSAGE_LIST) {
					if (inner.containsKey(messageKey)) {
						msg = errorCode + String.valueOf(inner.get(messageKey));
						break;
					}
				}

				if (!Strings.isNullOrEmpty(msg)) {
					return ActionResponse.actionFailed(msg, response.getStatus());
				}
			}
		}

		// detect HuaWei OTC error code
		String errorCode = "";
		for (String codeKey : KEY_CODE_LIST) {
			if (map.containsKey(codeKey)) {
				errorCode = "[" + String.valueOf(map.get(codeKey)) + "] ";
				break;
			}
		}

		String message = "";
		for (String messageKey : KEY_MESSAGE_LIST) {
			if (map.containsKey(messageKey)) {
				message = errorCode + String.valueOf(map.get(messageKey));
				break;
			}
		}

		if (!Strings.isNullOrEmpty(message.toString())) {
			return ActionResponse.actionFailed(message.toString(), response.getStatus());
		}

		return null;
	}

}
