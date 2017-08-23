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

/**
 * Base OpenStackj Exception 
 * 
 * @author Jeremy Unruh
 */
public class OS4JException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OS4JException(String message) {
        super(message);
    }
    
    public OS4JException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public OS4JException(Throwable cause) {
        super(cause);
    }
}
