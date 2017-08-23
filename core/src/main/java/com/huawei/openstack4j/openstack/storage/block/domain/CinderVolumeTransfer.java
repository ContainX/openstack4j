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
package com.huawei.openstack4j.openstack.storage.block.domain;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.common.Link;
import com.huawei.openstack4j.model.storage.block.VolumeTransfer;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Represents a Volume Transfer Entity which is used for creating a volume transfer
 *
 * @author Jeremy Unruh
 */
@JsonRootName("transfer")
public class CinderVolumeTransfer implements VolumeTransfer {

    private static final long serialVersionUID = 126124717679358069L;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty("auth_key")
    private String authKey;
    @JsonProperty("volume_id")
    private String volumeId;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty
    private List<GenericLink> links;

    public static CinderVolumeTransfer create(String volumeId, String name) {
        CinderVolumeTransfer r = new CinderVolumeTransfer();
        r.volumeId = volumeId;
        r.name = name;
        return r;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAuthKey() {
        return authKey;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getVolumeId() {
        return volumeId;
    }

    @Override
    public List<? extends Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return toStringHelper(this).omitNullValues()
                .add("id", id).add("name", name).add("authKey", authKey)
                .add("volumeId", volumeId).add("createdAt", createdAt).add("links", links)
                .toString();
    }

    public static class VolumeTransferList extends ListResult<CinderVolumeTransfer> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("transfers")
        private List<CinderVolumeTransfer> results;

        @Override
        protected List<CinderVolumeTransfer> value() {
            return results;
        }

    }
}
