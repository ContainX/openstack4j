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
package org.openstack4j.openstack.trove.internal;

import org.openstack4j.api.trove.DatastoreService;
import org.openstack4j.model.trove.Datastore;
import org.openstack4j.model.trove.DatastoreVersion;
import org.openstack4j.openstack.trove.domain.TroveDatastore;
import org.openstack4j.openstack.trove.domain.TroveDatastore.Datastores;
import org.openstack4j.openstack.trove.domain.TroveDatastoreVersion;
import org.openstack4j.openstack.trove.domain.TroveDatastoreVersion.Versions;

import java.util.List;

/**
 * Datastore API Implementation
 *
 * @author sumit gandhi
 */
public class DBDatastoreServiceImpl extends BaseTroveServices implements DatastoreService {

    @Override
    public List<? extends Datastore> list() {
        return get(Datastores.class, uri("/datastores")).execute().getList();
    }

    @Override
    public Datastore get(String id) {
        return get(TroveDatastore.class, uri("/datastores/%s", id)).execute();
    }

    @Override
    public List<? extends DatastoreVersion> listDatastoreVersions(String datasoreId) {
        return get(Versions.class, uri("/datastores/%s/versions",datasoreId)).execute().getList();
    }

    @Override
    public DatastoreVersion getDatastoreVersion(String datastoreId, String versionId) {
        return get(TroveDatastoreVersion.class, uri("/datastores/%s/versions/%s",datastoreId,versionId)).execute();
    }

}
