/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD
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
import com.huawei.openstack4j.model.dns.v2.*;
import com.huawei.openstack4j.model.dns.v2.builder.RecordsetBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * zone model class for designate/v2 zone
 */
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignateRecordset implements Recordset {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;
    @JsonProperty("zone_id")
    private String zoneId;
    @JsonProperty("zone_name")
    private String zoneName;
    private RecordSetType type;
    private Integer ttl;
    private List<String> records;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("update_at")
    private String updateAt;
    private Status status;
    @JsonProperty("health_check_id")
    private String healthCheckId;
    @JsonProperty("default")
    private String defaultValue;
    @JsonProperty("project_id")
    private String projectId;
    private Map<String,String> links;

    /**
     * @return the zone builder
     */
    public static RecordsetBuilder builder() {
        return new RecordsetConcreteBuilder();
    }

    @Override
    public RecordsetBuilder toBuilder() {
        return new RecordsetConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
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
    public Status getStatus() {
        return status;
    }

    @Override
    public String getZoneId() {
        return zoneId;
    }

    @Override
    public String getZoneName() {
        return zoneName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public RecordSetType getType() {
        return type;
    }

    @Override
    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public Map<String, String> getLinks() {
        return links;
    }

    @Override
    public List<String> getRecords() {
        return records;
    }

    public Integer getTtl() {
        return ttl;
    }

    @Override
    public String getUpdateAt() {
        return updateAt;
    }

    @Override
    public String getHealthCheckId() {
        return healthCheckId;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, projectId, name, ttl, status, zoneId, zoneName, description, type, createdAt, updateAt, links, healthCheckId, defaultValue);
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
        DesignateRecordset that = DesignateRecordset.class.cast(obj);
        return Objects.equal(this.id, that.id)
                && Objects.equal(this.projectId, that.projectId)
                && Objects.equal(this.name, that.name)
                && Objects.equal(this.ttl, that.ttl)
                && Objects.equal(this.status, that.status)
                && Objects.equal(this.zoneId, that.zoneId)
                && Objects.equal(this.zoneName, that.zoneName)
                && Objects.equal(this.description, that.description)
                && Objects.equal(this.type, that.type)
                && Objects.equal(this.createdAt, that.createdAt)
                && Objects.equal(this.links, that.links)
                && Objects.equal(this.healthCheckId, that.healthCheckId)
                && Objects.equal(this.defaultValue, that.defaultValue);
    }

    public static class RecordsetConcreteBuilder implements RecordsetBuilder {

        DesignateRecordset model;

        RecordsetConcreteBuilder() {
            this(new DesignateRecordset());
        }

        RecordsetConcreteBuilder(DesignateRecordset model) {
            this.model = model;
        }

        @Override
        public Recordset build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RecordsetBuilder from(Recordset in) {
            if (in != null)
                this.model = (DesignateRecordset) in;
            return this;
        }

        @Override
        public RecordsetBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public RecordsetBuilder projectId(String projectId) {
            model.projectId = projectId;
            return this;
        }

        @Override
        public RecordsetBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public RecordsetBuilder ttl(int ttl) {
            model.ttl = ttl;
            return this;
        }

        @Override
        public RecordsetBuilder status(Status status) {
            model.status = status;
            return this;
        }

        @Override
        public RecordsetBuilder zoneId(String zoneId) {
            model.zoneId = zoneId;
            return this;
        }

        @Override
        public RecordsetBuilder zoneName(String zoneName) {
            model.zoneName = zoneName;
            return this;
        }

        @Override
        public RecordsetBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public RecordsetBuilder type(RecordSetType type) {
            model.type = type;
            return this;
        }

        @Override
        public RecordsetBuilder createdAt(String createdAt) {
            model.createdAt = createdAt;
            return this;
        }

        @Override
        public RecordsetBuilder updateAt(String updatedAt) {
            model.updateAt = updatedAt;
            return this;
        }

        /**
         * @see DesignateRecordset#getLinks()
         */
        @Override
        public RecordsetBuilder links(Map<String, String> links) {
            model.links = links;
            return this;
        }

        /**
         * @see DesignateRecordset#getRecords()
         */
        @Override
        public RecordsetBuilder records(List<String> records) {
            model.records = records;
            return this;
        }

        @Override
        public RecordsetBuilder defaultValue(String defaultValue) {
            model.defaultValue = defaultValue;
            return this;
        }

        @Override
        public RecordsetBuilder healthCheckId(String healthCheckId) {
            model.healthCheckId = healthCheckId;
            return this;
        }

    }

    public static class Recordsets extends ListResult<DesignateRecordset> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("recordsets")
        protected List<DesignateRecordset> list;

        @Override
        public List<DesignateRecordset> value() {
            return list;
        }
    }
}
