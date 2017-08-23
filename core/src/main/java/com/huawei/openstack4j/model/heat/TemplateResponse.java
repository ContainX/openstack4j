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
package com.huawei.openstack4j.model.heat;

import com.google.common.base.MoreObjects;

/**
 * Response returned during Template validation
 *
 * @author Jeremy Unruh
 */
public final class TemplateResponse {

    private String message;

    private TemplateResponse() { }

    private TemplateResponse(String message) {
        this.message = message;
    }

    public static TemplateResponse success() {
        return new TemplateResponse();
    }

    public static TemplateResponse fail(String message) {
        return new TemplateResponse(message);
    }

    /**
     * True if the template validation was successful
     *
     * @return true if successful
     */
    public boolean isValid() {
        return message == null;
    }

    /**
     * The Error message that occurred during validation.
     *
     * @return the error message, will be null if {@link #isValid()} is true
     */
    public String getMessage() {
        return message;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("valid", message == null).add("message", message).toString();
    }
}
