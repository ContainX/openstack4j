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

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.model.dns.v2.Action;
import org.openstack4j.model.dns.v2.Status;

import java.util.List;
import java.util.Map;

/**
 * A Builder which creates a designate v2 Recordset
 *
 *
 */
public interface RecordsetBuilder extends Builder<RecordsetBuilder, Recordset> {

	/**
	 * @see Recordset#getId()
	 */
	RecordsetBuilder id(String id);

	/**
	 * @see Recordset#getProjectId()
	 */
	RecordsetBuilder projectId(String projectId);

	/**
	 * @see Recordset#getName()
	 */
	RecordsetBuilder name(String name);

	/**
	 * @see Recordset#getTTL() ()
	 */
	RecordsetBuilder ttl(String ttl);

	/**
	 * @see Recordset#getStatus()
	 */
	RecordsetBuilder status(Status status);

	/**
	 * @see Recordset#getAction()
	 */
	RecordsetBuilder action(Action action);

	/**
	 * @see Recordset#getZoneId()
	 */
	RecordsetBuilder zoneId(String zoneId);

	/**
	 * @see Recordset#getZoneName()
	 */
	RecordsetBuilder zoneName(String zoneName);

	/**
	 * @see Recordset#getDescription()
	 */
	RecordsetBuilder description(String description);

	/**
	 * @see Recordset#getType()
	 */
	RecordsetBuilder type(String type);

	/**
	 * @see Recordset#getVersion()
	 */
	RecordsetBuilder version(Integer version);

	/**
	 * @see Recordset#getCreatedAt()
	 */
	RecordsetBuilder createdAt(String createdAt);

	/**
	 * @see Recordset#getUpdatedAt()
	 */
	RecordsetBuilder updatedAt(String updatedAt);

	/**
	 * @see Recordset#getLinks()
	 */
	RecordsetBuilder links(Map<String, String> links);

	/**
	 * @see Recordset#getRecords()
	 */
	RecordsetBuilder records(List<String> records);

}
