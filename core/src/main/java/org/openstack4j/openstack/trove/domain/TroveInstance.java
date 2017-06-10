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

import java.util.Date;
import java.util.List;

import org.openstack4j.model.trove.Flavor;
import org.openstack4j.model.trove.Instance;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Model implementation for Database instance
 *
 * @author Shital Patil
 */
@JsonRootName("instance")
public class TroveInstance implements Instance {

    private static final long serialVersionUID = 1L;

    private Date created;

    private TroveInstanceFlavor flavor;

    private String hostname;

    private List<String> ip;

    private String id;

    private String name;

    private String status;

    private Date updated;

    private Volume volume;

    private InstanceDatastore datastore;

    public class Volume {

        private String type;

        private Integer size;

        /**
         *
         * @return
         *         The type
         */

        public String getType() {
            return type;
        }

        /**
         *
         * @return
         *         The size
         */

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Volume{");
            sb.append("type='").append(type).append('\'');
            sb.append(", size=").append(size);
            sb.append('}');
            return sb.toString();
        }
    }

    public class InstanceDatastore {

        private String type;

        private String version;

        public String getType() { return type; }

        public String getVersion() { return version; }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("InstanceDatastore{");
            sb.append("type='").append(type).append('\'');
            sb.append(", version='").append(version).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public int getVolumeSize() {
        return getVolume().getSize();
    }

    @Override
    public String getVolumeType() {
        return getVolume().getType();
    }

    @Override
    public Flavor getFlavor() {
        return flavor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public List<String> getIp() { return ip; }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    public Volume getVolume() {
        return volume;
    }

    public InstanceDatastore getDatastore() { return datastore; }

    @Override
    public String getDatastoreType() {
        return getDatastore() != null
                ? getDatastore().getType()
                : null;
    }

    @Override
    public String getDatastoreVersion() {
        return getDatastore() != null
                ? getDatastore().getVersion()
                : null;
    }

    public static class DBInstances extends ListResult<TroveInstance> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("instances")
        private List<TroveInstance> instances;

        @Override
        protected List<TroveInstance> value() {
            return instances;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TroveInstance{");
        sb.append("created=").append(created);
        sb.append(", flavor=").append(flavor);
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", updated=").append(updated);
        sb.append(", volume=").append(volume);
        sb.append(", datastore=").append(datastore);
        sb.append('}');
        return sb.toString();
    }
}
