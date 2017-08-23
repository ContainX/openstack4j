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

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.ext.DNSEntry;
import com.huawei.openstack4j.model.compute.ext.DNSRecordType;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * A Floating IP DNS Extension - DNS Entry
 *
 * @author Jeremy Unruh
 */
@JsonRootName("dns_entry")
public class ExtDNSEntry implements DNSEntry {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;
    @JsonProperty
    private String domain;
    @JsonProperty("ip")
    private String ipAddress;
    @JsonProperty
    private String name;
    @JsonProperty
    private DNSRecordType type;

    /* Only used during create / modify operations */
    @JsonProperty("dns_type")
    private DNSRecordType createType;

    public ExtDNSEntry() {
    }

    public ExtDNSEntry(String ipAddress, DNSRecordType type) {
        this.ipAddress = ipAddress;
        this.createType = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DNSRecordType getType() {
        return type;
    }

    @Override
    public String toString() {
        return toStringHelper(this).omitNullValues()
                 .add("id", id).add("domain", domain).add("ip", ipAddress)
                 .add("name", name).add("type", type)
                 .toString();
    }

    public static class DNSEntries extends ListResult<ExtDNSEntry> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("dns_entries")
        private List<ExtDNSEntry> result;

        @Override
        protected List<ExtDNSEntry> value() {
            return result;
        }

    }

}
