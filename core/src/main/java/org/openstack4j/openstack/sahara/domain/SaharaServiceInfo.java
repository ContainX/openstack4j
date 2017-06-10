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
package org.openstack4j.openstack.sahara.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.openstack4j.model.sahara.ServiceInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * For mapping JSON response to java objects
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class SaharaServiceInfo extends HashMap<String,String> implements ServiceInfo {

	public static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
	@Override
	public String get(String name) {
		return super.get(name);
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public Map<String,String> getInfos() {
		return this;
	}

}
