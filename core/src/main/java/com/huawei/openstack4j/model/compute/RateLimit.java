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
package com.huawei.openstack4j.model.compute;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Rate limits are specified in terms of both a human-readable wild-card URI and a machine-processable regular expression. 
 * The human-readable limit is intended for displaying in graphical user interfaces. The machine-processable form is 
 * intended to be used directly by client applications.
 * 
 * @author Jeremy Unruh
 */
public interface RateLimit extends ModelEntity {

	/**
	 * @return the regular expression used for this rate limit
	 */
	String getRegex();

	/**
	 * @return the URI associated with the rate limit
	 */
	String getUri();
	
	List<? extends LimitEntry> getLimit();
	
	public interface LimitEntry {
		
		/**
		 * @return the nextAvailable date/time
		 */
		Date getNextAvailable();

		/**
		 * @return the unit of time for limiting
		 */
		String getUnit();

		/**
		 * @return the verb (action type)
		 */
		String getVerb();

		/**
		 * @return the remaining limits
		 */
		int getRemaining();

		/**
		 * @return the available limit slots
		 */
		int getAvailable();

		/**
		 * @return the value for the last limit
		 */
		int getValue();
	}
	
}
