/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD
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
	 * @see Recordset#getTtl() ()
	 */
	RecordsetBuilder ttl(int ttl);

	/**
	 * @see Recordset#getStatus()
	 */
	RecordsetBuilder status(Status status);

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
	RecordsetBuilder type(RecordSetType type);

	/**
	 * @see Recordset#getCreatedAt()
	 */
	RecordsetBuilder createdAt(String createdAt);

	/**
	 * @see Recordset#getUpdateAt()
	 */
	RecordsetBuilder updateAt(String updateAt);

	/**
	 * @see Recordset#getLinks()
	 */
	RecordsetBuilder links(Map<String, String> links);

	/**
	 * @see Recordset#getRecords()
	 */
	RecordsetBuilder records(List<String> records);

	/**
	 * @see Recordset#getDefaultValue()
	 */
	RecordsetBuilder defaultValue(String defaultValue);

	/**
	 * @see Recordset#getHealthCheckId() ()
	 */
	RecordsetBuilder healthCheckId(String healthCheckId);

}
