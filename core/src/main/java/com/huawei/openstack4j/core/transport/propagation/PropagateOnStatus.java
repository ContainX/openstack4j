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
package com.huawei.openstack4j.core.transport.propagation;

import static com.huawei.openstack4j.core.transport.HttpExceptionHandler.*;

import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.core.transport.PropagateResponse;
import com.huawei.openstack4j.core.transport.functions.ResponseToActionResponse;
import com.huawei.openstack4j.model.common.ActionResponse;

/**
 * Propagates an exception based on the specified Status code
 * 
 * @author Jeremy Unruh
 */
public class PropagateOnStatus implements PropagateResponse {

    private final int statusCode;
    
    private PropagateOnStatus(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public static PropagateOnStatus on(int statusCode) {
        return new PropagateOnStatus(statusCode);
    }
    
    @Override
    public void propagate(HttpResponse response) {
        if (response.getStatus() == statusCode) {
            ActionResponse ar = ResponseToActionResponse.INSTANCE.apply(response);
            throw mapException(ar.getFault(), response.getStatus());
        }
    }

}
