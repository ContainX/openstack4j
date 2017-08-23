/*******************************************************************************
 *  Copyright 2017 HuaWei TLD
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
package com.huawei.openstack4j.openstack.dns.v2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.dns.v2.Status;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.huawei.openstack4j.model.dns.v2.builder.ZoneBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * zone model class for designate/v2 zone
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignateZone implements Zone {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;
    private String email;
    @JsonProperty("zone_type")
    private ZoneType type;
    private Integer ttl;
    private String serial;
    private Status status;
    @JsonProperty("record_num")
    private Integer recordsAmount;
    @JsonProperty("pool_id")
    private String poolId;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("update_at")
    private String updateAt;
    private Map<String, String> links;
    private List<String> masters;
    private Router router;
    private List<Router> routers;

    /**
     * @return the zone builder
     */
    public static ZoneBuilder builder() {
        return new ZoneConcreteBuilder();
    }

    @Override
    public ZoneBuilder toBuilder() {
        return new ZoneConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPoolId() {
        return poolId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Integer getTTL() {
        return ttl;
    }

    @Override
    public String getSerial() {
        return serial;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getMasters() {
        return masters;
    }

    @Override
    public ZoneType getType() {
        return type;
    }

    @Override
    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getUpdateAt() {
        return updateAt;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    @Override
    public Integer getRecordsAmount() {
        return recordsAmount;
    }

    public Router getRouter() {
        return router;
    }

    public List<Router> getRouters() {
        return routers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, poolId, projectId, name, email, ttl, serial, status, description, masters, type, createdAt, updateAt);
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
        DesignateZone that = DesignateZone.class.cast(obj);
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.poolId, that.poolId)
                && Objects.equal(this.projectId, that.projectId)
                && Objects.equal(this.name, that.name)
                && Objects.equal(this.email, that.email)
                && Objects.equal(this.ttl, that.ttl)
                && Objects.equal(this.serial, that.serial)
                && Objects.equal(this.status, that.status)
                && Objects.equal(this.description, that.description)
                && Objects.equal(this.masters, that.masters)
                && Objects.equal(this.type, that.type)
                && Objects.equal(this.createdAt, that.createdAt)
                && Objects.equal(this.updateAt, that.updateAt)
                && Objects.equal(this.recordsAmount, that.recordsAmount)
                && Objects.equal(this.links, that.links);
    }

    public static class ZoneConcreteBuilder implements ZoneBuilder {

        DesignateZone model;

        ZoneConcreteBuilder() {
            this(new DesignateZone());
        }

        ZoneConcreteBuilder(DesignateZone model) {
            this.model = model;
        }

        @Override
        public Zone build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ZoneBuilder from(Zone in) {
            if (in != null)
                this.model = (DesignateZone) in;
            return this;
        }

        /**
         * @see DesignateZone#getId()
         */
        @Override
        public ZoneBuilder id(String id) {
            model.id = id;
            return this;
        }

        /**
         * @see DesignateZone#getPoolId()
         */
        @Override
        public ZoneBuilder poolId(String poolId) {
            model.poolId = poolId;
            return this;
        }

        /**
         * @see DesignateZone#getProjectId()
         */
        @Override
        public ZoneBuilder projectId(String projectId) {
            model.projectId = projectId;
            return this;
        }

        /**
         * @see DesignateZone#getName()
         */
        @Override
        public ZoneBuilder name(String name) {
            model.name = name;
            return this;
        }

        /**
         * @see DesignateZone#getEmail()
         */
        @Override
        public ZoneBuilder email(String email) {
            model.email = email;
            return this;
        }

        /**
         * @see DesignateZone#getTTL()
         */
        @Override
        public ZoneBuilder ttl(Integer ttl) {
            model.ttl = ttl;
            return this;
        }

        /**
         * @see DesignateZone#getSerial()
         */
        @Override
        public ZoneBuilder serial(String serial) {
            model.serial = serial;
            return this;
        }

        /**
         * @see DesignateZone#getStatus()
         */
        @Override
        public ZoneBuilder status(Status status) {
            model.status = status;
            return this;
        }

        /**
         * @see DesignateZone#getDescription()()
         */
        @Override
        public ZoneBuilder description(String description) {
            model.description = description;
            return this;
        }

        /**
         * @see DesignateZone#getMasters()
         */
        @Override
        public ZoneBuilder masters(List<String> masters) {
            model.masters = masters;
            return this;
        }

        /**
         * @see DesignateZone#getType()
         */
        @Override
        public ZoneBuilder type(ZoneType type) {
            model.type = type;
            return this;
        }

        /**
         * @see DesignateZone#getCreatedAt()
         */
        @Override
        public ZoneBuilder createdAt(String createdAt) {
            model.createdAt = createdAt;
            return this;
        }

        /**
         * @see DesignateZone#getUpdateAt()
         */
        @Override
        public ZoneBuilder updatedAt(String updatedAt) {
            model.updateAt = updatedAt;
            return this;
        }

        /**
         * @see DesignateZone#getLinks()
         */
        @Override
        public ZoneBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        public ZoneBuilder router(Router router) {
            model.router = router;
            return this;
        }
    }

    public static class Zones extends ListResult<DesignateZone> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("zones")
        protected List<DesignateZone> list;

        @Override
        public List<DesignateZone> value() {
            return list;
        }
    }


    @ToString
    public static class Router implements ModelEntity {
        @JsonProperty("router_id")
        protected String id;
        @JsonProperty("router_region")
        protected String region;
        protected Status status;

        public Router(String id, String region, Status status) {
            this.id = id;
            this.region = region;
            this.status = status;
        }

        public Router() {
        }

        public String getId() {
            return id;
        }

        public String getRegion() {
            return region;
        }

        public Status getStatus() {
            return status;
        }
    }

}
