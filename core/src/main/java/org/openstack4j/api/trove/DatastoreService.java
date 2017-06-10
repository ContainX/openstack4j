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
package org.openstack4j.api.trove;

import org.openstack4j.model.trove.Datastore;
import org.openstack4j.model.trove.DatastoreVersion;

import java.util.List;

/**
 * This interface defines all methods for the manipulation of trove datastores
 *
 * @author sumit gandhi
 */

public interface DatastoreService {

    /**
     * Returns list of available datastores
     * @return the list of datastores
     */
    List<? extends Datastore> list();

    /**
     * Gets a datastore specified by ID
     * @param id
     * @return the datastore or null if not found
     */
    Datastore get(String id);

    /**
     * Returns list of all datastore versions
     * @param datasoreId
     * @return list of datastore versions
     */
    List<? extends DatastoreVersion> listDatastoreVersions(String datasoreId);

    /**
     * Get the datastore version specified by ID
     * @param datastoreId
     * @param versionId
     * @return the datastore version or null if not found
     */
    DatastoreVersion getDatastoreVersion(String datastoreId, String versionId);

}
