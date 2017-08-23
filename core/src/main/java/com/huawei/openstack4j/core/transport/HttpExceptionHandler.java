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
package com.huawei.openstack4j.core.transport;

import com.huawei.openstack4j.api.exceptions.AuthenticationException;
import com.huawei.openstack4j.api.exceptions.ClientResponseException;
import com.huawei.openstack4j.api.exceptions.ResponseException;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;

/**
 * Exception Handles for common Http messages and status codes
 * 
 * @author Jeremy Unruh
 */
public class HttpExceptionHandler {

    /**
     * Maps an Exception based on the underlying status code
     *
     * @param message the message
     * @param status the status
     * @return the response exception
     */
    public static ResponseException mapException(String message, int status) {
        return mapException(message, status, null);
    }

    /**
     * Maps an Exception based on the underlying status code
     *
     * @param message the message
     * @param status the status
     * @param cause the cause
     * @return the response exception
     */
    public static ResponseException mapException(String message, int status, Throwable cause) {
        if (status == 401)
            return new AuthenticationException(message, status, cause);
        if (status >= 400 && status < 499)
            return new ClientResponseException(message, status, cause);
        if (status >= 500 && status < 600)
            return new ServerResponseException(message, status, cause);

        return new ResponseException(message, status, cause);
    }
    
}
