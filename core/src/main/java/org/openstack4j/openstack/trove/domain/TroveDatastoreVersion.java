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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.trove.DatastoreVersion;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Model implementation for Datastore version
 *
 * @author sumit gandhi
 */

@JsonRootName("version")
public class TroveDatastoreVersion implements DatastoreVersion {

    private String name;
    private String id;
    @JsonProperty("datastore")
    private String datastoreId;
    @JsonProperty("active")
    private int isActive;
    private String image;
    @JsonProperty("packages")
    private String packageName;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDatastoreId() {
        return datastoreId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getImage() {
        return image;
    }

    public String getPackageName() {
        return packageName;
    }

    public static class Versions extends ListResult<TroveDatastoreVersion> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("versions")
        private List<TroveDatastoreVersion> troveDatastoreVersionList;

        @Override
        protected List<TroveDatastoreVersion> value() {
            return troveDatastoreVersionList;
        }

    }
}
