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
package com.huawei.openstack4j.model.heat.builder;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.heat.Template;


/**
 * This interface describes a builder for {@link Template} objects 
 * @author Matthias Reisser
 *
 */
public interface TemplateBuilder extends Buildable.Builder<TemplateBuilder, Template>{
	
	/**
	 * Sets the template in JSON format. This value takes precedence over the template URL if both are supplied.
	 * 
	 * @param template template in JSON format
	 * @return modified TemplateBuilder
	 */
	TemplateBuilder templateJson(String template);
	
	
	/**
	 * The URL of the template to instantiate. This value is ignored if the template is supplied inline
	 * 
	 * @param templateURL the template URL
	 * @return TemplateBuilder
	 */
	TemplateBuilder templateURL(String templateURL);

}
