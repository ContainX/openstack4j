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
package com.huawei.openstack4j.model.map.reduce;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * A map reduce Plugin 
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface Plugin extends ModelEntity {

	/**
	 * @return the plugin description 
	 */
	String getDescription();

	/**
	 * @return the available plugin versions
	 */
	List<String> getVersions();

	/**
	 * @return the name of the plugin
	 */
	String getName();

	/**
	 * @return the title of the plugin 
	 */
	String getTitle();

        /**
         * @return the list of processes in a specific service (node_processes in map reduce plugin terminology)
         */
        Map<String,List<String>> getServiceProcesses();


	/**
	 * @return the list of required image tags
	 */
        List<String> getRequiredImageTags();

	/**
	 * @return the list of config information (definitions and default values)
	 */
	List<? extends ConfigInfo> getConfigInfos();

}
