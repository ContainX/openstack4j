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
package org.openstack4j.model.dns.v2.builder;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.dns.v2.Action;
import org.openstack4j.model.dns.v2.Status;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.model.dns.v2.ZoneType;

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
	 * @see Zone#getAction()
	 */
	ZoneBuilder action(Action action);

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
	 * @see Zone#getTransferedAt()
	 */
	ZoneBuilder transferredAt(String transferredAt);

	/**
	 * @see Zone#getVersion()
	 */
	ZoneBuilder version(Integer version);

	/**
	 * @see Zone#getCreatedAt()
	 */
	ZoneBuilder createdAt(String createdAt);

	/**
	 * @see Zone#getUpdatedAt()
	 */
	ZoneBuilder updatedAt(String updatedAt);

	/**
	 * @see Zone#getLinks()
	 */
	ZoneBuilder links(Map<String, String> links);

}
