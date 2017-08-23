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

import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.internal.Parser;

/**
 * Takes an HttpResponse as input and returns an ActionResponse as an output
 *
 * @author Jeremy Unruh
 */
public class ResponseToActionResponse implements Function<HttpResponse, ActionResponse> {

    public static final ResponseToActionResponse INSTANCE = new ResponseToActionResponse();

    @Override
    public ActionResponse apply(HttpResponse response) {
       return apply(response, false);
    }

    public ActionResponse apply(HttpResponse response, boolean returnNullIfNotMapped) {
        if (Parser.isContentTypeText(response.getContentType())) {
            return ActionResponse.actionFailed(response.getStatusMessage(), response.getStatus());
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = response.readEntity(Map.class);
        ActionResponse ar = new ParseActionResponseFromJsonMap(response).apply(map);
        if (ar != null)
            return ar;

        if (ar == null && returnNullIfNotMapped)
            return null;

        return ActionResponse.actionFailed(String.format("Status: %d, Reason: %s", response.getStatus(), response.getStatusMessage()), response.getStatus());
    }
}
