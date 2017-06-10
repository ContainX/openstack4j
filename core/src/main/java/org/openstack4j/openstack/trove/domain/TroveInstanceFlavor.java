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
import org.openstack4j.model.trove.Flavor;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Model implementation for Database instance flavor
 *
 * @author sumit gandhi
 */

@JsonRootName("flavor")
public class TroveInstanceFlavor implements Flavor {

    private static final long serialVersionUID = 1L;
    private String name;
    private String id;
    @JsonProperty("str_id")
    private String strId;

    private int ram;
    private int vcpus;
    private int disk;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getStrId() {
        return strId;
    }

    public int getRam() {
        return ram;
    }

    public int getVcpus() {
        return vcpus;
    }

    public int getDisk() {
        return disk;
    }

    public static class Flavors extends ListResult<TroveInstanceFlavor> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("flavors")
        private List<TroveInstanceFlavor> troveInstanceFlavorList;

        @Override
        protected List<TroveInstanceFlavor> value() {
            return troveInstanceFlavorList;
        }

    }

}
