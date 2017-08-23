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
package com.huawei.openstack4j.openstack.identity.v3.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.identity.v3.Region;
import com.huawei.openstack4j.model.identity.v3.builder.RegionBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Region model class for identity v3
 *
 * @see <a href=
 *      "http://developer.openstack.org/api-ref-identity-v3.html#regions-v3">API
 *      reference</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("region")
public class KeystoneRegion implements Region {

    private static final long serialVersionUID = 1L;
    private String id;
    private String description;
    @JsonProperty("parent_region_id")
    private String parentRegionId;

    /**
     * @return the region builder
     */
    public static RegionBuilder builder() {
        return new RegionConcreteBuilder();
    }

    @Override
    public RegionBuilder toBuilder() {
        return new RegionConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getParentRegionId() {
        return parentRegionId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("id", id)
                .add("description", description)
                .add("parentRegionId", parentRegionId)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeystoneRegion that = KeystoneRegion.class.cast(obj);
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.description, that.description)
                && Objects.equal(this.parentRegionId, that.parentRegionId);
    }

    public static class Regions extends ListResult<KeystoneRegion> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("regions")
        private List<KeystoneRegion> list;

        @Override
        public List<KeystoneRegion> value() {
            return list;
        }
    }

    public static class RegionConcreteBuilder implements RegionBuilder {

        KeystoneRegion model;

        RegionConcreteBuilder() {
            this(new KeystoneRegion());
        }

        RegionConcreteBuilder(KeystoneRegion model) {
            this.model = model;
        }

        /**
         * @see KeystoneUser#getId()
         */
        @Override
        public RegionBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public Region build() {
            return model;
        }

        @Override
        public RegionBuilder from(Region in) {
            if (in != null)
                this.model = (KeystoneRegion) in;
            return this;
        }

        @Override
        public RegionBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public RegionBuilder parentRegionId(String parentRegionId) {
            model.parentRegionId = parentRegionId;
            return this;
        }
    }
}
