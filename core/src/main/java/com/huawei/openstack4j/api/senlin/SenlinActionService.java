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
package com.huawei.openstack4j.api.senlin;

import java.util.List;

import com.huawei.openstack4j.model.senlin.Action;

/**
 * This interface defines all methods for the manipulation of Action
 * 
 * @author lion
 * 
 */
public interface SenlinActionService {
	
	/**
	 * Gets a list of currently existing {@link Action}s.
	 * 
	 * @return the list of {@link Action}s
	 */
	List<? extends Action> list();

	/**
	 * returns details of a {@link Action}.
	 *
	 * @param actionID
	 *            Id of {@link Action}
	 * @return Action
	 */
	Action get(String actionID);
}
