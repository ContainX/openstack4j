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
package com.huawei.openstack4j.api.heat;

import java.util.Map;

import com.huawei.openstack4j.model.heat.Template;
import com.huawei.openstack4j.model.heat.TemplateResponse;

/**
 * This Interface contains a non-exhaustive list of methods for the manipulation of Heat Templates
 * @author Matthias Reisser
 *
 */
public interface TemplateService {

	/**
	 * Validates the template
	 * @param template to validate, passed as a {@link Template}
	 * @return TemplateResponse indicating valid or the error condition if not valid
	 */
	TemplateResponse validateTemplate(Template template);
	
	/**
	 * Validates the template
	 * @param template to validate, passed as {@link String} in JSON Format
	 * @return TemplateResponse indicating valid or the error condition if not valid
	 */
	TemplateResponse validateTemplate(String template);
	
	/**
     * Validates the template
     * 
     * @param templateURL the remote template via URL to validate
     * @return TemplateResponse indicating valid or the error condition if not valid
     */
    TemplateResponse validateTemplateByURL(String templateURL);
    
    /**
     * Retrieves the original template in original String form JSON or YAML
     * @param stackName the stack name
     * @param stackId the stack identifier
     * @return the template
     * @throws ResponseException if an error occurs
     */
    String getTemplateAsString(String stackName, String stackId);
    
    /**
     * Retrieves the original template as Map<String,Object>
     * @param stackName the stack name
     * @param stackId the stack identifier
     * @return the template
     * @throws ResponseException if an error occurs
     */
    Map<String, Object> getTemplateAsMap(String stackName, String stackId);
    
    /**
     * Retrieves the original template as Map<String,Object> when you know only the stack name or stack id
     * @param stackNameOrId the stack name or stackId
     * @return the template
     * @throws ResponseException if an error occurs
     */
    Map<String, Object> getTemplateAsMap(String stackNameOrId);
    
    
	
}
