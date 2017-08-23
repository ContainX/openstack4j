/*******************************************************************************
 *  Copyright 2017 HuaWei TLD
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
package com.huawei.openstack4j.model.dns.v2.builder;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.dns.v2.*;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateZone;

/**
 * A Builder which creates a designate v2 Zone
 *
 *
 */
public interface ZoneBuilder extends Builder<ZoneBuilder, Zone> {

	/**
	 * @see Zone#getId()
	 */
	ZoneBuilder id(String id);

	/**
	 * @see Zone#getPoolId()
	 */
	ZoneBuilder poolId(String poolId);

	/**
	 * @see Zone#getProjectId()
	 */
	ZoneBuilder projectId(String projectId);

	/**
	 * @see Zone#getName()
	 */
	ZoneBuilder name(String name);

	/**
	 * @see Zone#getEmail() ()
	 */
	ZoneBuilder email(String email);

	/**
	 * @see Zone#getTTL() ()
	 */
	ZoneBuilder ttl(Integer ttl);

	/**
	 * @see Zone#getSerial()
	 */
	ZoneBuilder serial(String serial);

	/**
	 * @see Zone#getStatus()
	 */
	ZoneBuilder status(Status status);


	/**
	 * @see Zone#getDescription() ()
	 */
	ZoneBuilder description(String description);

	/**
	 * @see Zone#getMasters()
	 */
	ZoneBuilder masters(List<String> masters);

	/**
	 * @see Zone#getType()
	 */
	ZoneBuilder type(ZoneType type);

	/**
	 * @see Zone#getCreatedAt()
	 */
	ZoneBuilder createdAt(String createdAt);

	/**
	 * @see Zone#getUpdateAt()
	 */
	ZoneBuilder updatedAt(String updatedAt);

	/**
	 * @see Zone#getLinks()
	 */
	ZoneBuilder links(Map<String, String> links);

	/**
	 * @see DesignateZone#getRouter()
	 */
	ZoneBuilder router(DesignateZone.Router router);

}
