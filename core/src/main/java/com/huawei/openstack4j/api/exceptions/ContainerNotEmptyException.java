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
 * An exception that is thrown when an Object Storage Container is attempted to be deleted that is not empty
 * 
 * @author Jeremy Unruh
 */
public class ContainerNotEmptyException extends ResponseException {

    private static final long serialVersionUID = 1L;

    public ContainerNotEmptyException(String message, int status, Throwable cause) {
        super(message, status, cause);
    }

    public ContainerNotEmptyException(String message, int status) {
        super(message, status);
    }
}
