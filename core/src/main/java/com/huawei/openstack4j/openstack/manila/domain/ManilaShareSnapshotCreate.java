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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.manila.ShareSnapshotCreate;
import com.huawei.openstack4j.model.manila.builder.ShareSnapshotCreateBuilder;

/**
 * Object used to create new share snapshots.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("snapshot")
public class ManilaShareSnapshotCreate implements ShareSnapshotCreate {
    @JsonProperty("share_id")
    private String shareId;
    private Boolean force;
    private String name;
    @JsonProperty("display_name")
    private String displayName;
    private String description;
    @JsonProperty("display_description")
    private String displayDescription;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShareId() {
        return shareId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getForce() {
        return force;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayDescription() {
        return displayDescription;
    }

    public static ShareSnapshotCreateBuilder builder() {
        return new ShareSnapshotCreateConcreteBuilder();
    }
    @Override
    public ShareSnapshotCreateBuilder toBuilder() {
        return new ShareSnapshotCreateConcreteBuilder(this);
    }
    
    public static class ShareSnapshotCreateConcreteBuilder implements ShareSnapshotCreateBuilder {
        ManilaShareSnapshotCreate shareSnapshotCreate;

        ShareSnapshotCreateConcreteBuilder() {
            this(new ManilaShareSnapshotCreate());
        }

        ShareSnapshotCreateConcreteBuilder(ManilaShareSnapshotCreate shareSnapshotCreate) {
            this.shareSnapshotCreate = shareSnapshotCreate;
        }

        @Override
        public ShareSnapshotCreateBuilder shareId(String shareId) {
            shareSnapshotCreate.shareId = shareId;
            return this;
        }

        @Override
        public ShareSnapshotCreateBuilder force(boolean force) {
            shareSnapshotCreate.force = force;
            return this;
        }

        @Override
        public ShareSnapshotCreateBuilder name(String name) {
            shareSnapshotCreate.name = name;
            return this;
        }

        @Override
        public ShareSnapshotCreateBuilder displayName(String displayName) {
            shareSnapshotCreate.displayName = displayName;
            return this;
        }

        @Override
        public ShareSnapshotCreateBuilder description(String description) {
            shareSnapshotCreate.description = description;
            return this;
        }

        @Override
        public ShareSnapshotCreateBuilder displayDescription(String displayDescription) {
            shareSnapshotCreate.displayDescription = displayDescription;
            return this;
        }

        @Override
        public ShareSnapshotCreate build() {
            return shareSnapshotCreate;
        }

        @Override
        public ShareSnapshotCreateBuilder from(ShareSnapshotCreate in) {
            shareSnapshotCreate = (ManilaShareSnapshotCreate) in;
            return this;
        }
    }
}
