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
package org.openstack4j.openstack.trove.domain;

import java.util.List;

import org.openstack4j.model.trove.Datastore;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Model implementation for Datastore
 *
 * @author sumit gandhi
 */

@JsonRootName("datastore")
public class TroveDatastore implements Datastore {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String default_version;
    @JsonProperty("versions")
    private List<TroveDatastoreVersion> troveDatastoreVersionList;

    public String getDefault_version() {
        return default_version;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<TroveDatastoreVersion> getTroveDatastoreVersionList() {
        return troveDatastoreVersionList;
    }

    public static class Datastores extends ListResult<TroveDatastore> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("datastores")
        private List<TroveDatastore> troveDatastoreList;

        @Override
        protected List<TroveDatastore> value() {
            return troveDatastoreList;
        }

    }

}
