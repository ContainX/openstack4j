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
package com.huawei.openstack4j.openstack.compute.domain.ext;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.ext.DomainEntry;
import com.huawei.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

/**
 * A Floating IP DNS Extension - Domain Entry
 *
 * @author Jeremy Unruh
 */
@JsonRootName("domain_entry")
public class ExtDomainEntry implements DomainEntry {

    private static final long serialVersionUID = 1L;

    @JsonProperty("availability_zone")
    private String availabilityZone;
    @JsonProperty
    private String domain;
    @JsonProperty
    private String project;
    @JsonProperty
    private Scope scope;

    public ExtDomainEntry() {
    }

    public ExtDomainEntry(Scope scope, String availabilityZone, String project) {
        this.scope = scope;
        this.availabilityZone = availabilityZone;
        this.project = project;
    }

    @Override
    public String getAvailabilityZone() {
        return availabilityZone;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                  .add("availabilityZone", availabilityZone).add("domain", domain)
                  .add("project", project).add("scope", scope)
                  .toString();
    }

    public static class DomainEntries extends ListResult<ExtDomainEntry> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("domain_entries")
        private List<ExtDomainEntry> results;

        @Override
        protected List<ExtDomainEntry> value() {
            return results;
        }

    }

}
