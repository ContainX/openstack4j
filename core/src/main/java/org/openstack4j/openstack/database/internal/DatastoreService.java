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
package org.openstack4j.openstack.database.internal;

import java.util.List;

import org.openstack4j.openstack.database.constants.DatastoreType;
import org.openstack4j.openstack.database.domain.DatastoreVersion;
import org.openstack4j.openstack.database.domain.DatastoreVersion.Versions;

import com.google.common.base.Preconditions;

/**
 * The implementation of manipulation of {@link DatastoreDetail}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:41:17
 */
public class DatastoreService extends BaseDatabaseServices {

	/**
	 * Returns list of all datastore versions
	 * @param datastore	datastore type
	 * @return list of {@link DatastoreVersion} instances
	 */
	public List<DatastoreVersion> listDatastoreVersions(DatastoreType datastore) {
		Preconditions.checkArgument(datastore != null, "parameter `datastore` should not be null");
		return get(Versions.class, uri("/datastores/%s/versions", datastore.name())).execute().getList();
	}

}
