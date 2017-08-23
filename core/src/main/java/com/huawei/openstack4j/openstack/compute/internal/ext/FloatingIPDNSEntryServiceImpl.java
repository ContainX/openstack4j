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
package com.huawei.openstack4j.openstack.compute.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSEntryService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.ext.DNSEntry;
import com.huawei.openstack4j.model.compute.ext.DNSRecordType;
import com.huawei.openstack4j.openstack.compute.domain.ext.ExtDNSEntry;
import com.huawei.openstack4j.openstack.compute.domain.ext.ExtDNSEntry.DNSEntries;
import com.huawei.openstack4j.openstack.compute.internal.BaseComputeServices;

/**
 * A Service which handles DNS Entries for the Floating IP DNS Extension
 * 
 * @author Jeremy Unruh
 */
public class FloatingIPDNSEntryServiceImpl extends BaseComputeServices implements FloatingIPDNSEntryService {

    @Override
    public List<? extends DNSEntry> listByIP(String domain, String ip) {
        checkNotNull(ip, "ip");
        return listByName(domain, ip);
    }

    @Override
    public List<? extends DNSEntry> listByName(String domain, String name) {
        checkNotNull(domain, "domain");
        checkNotNull(name, "name");
        
        return get(DNSEntries.class, uri("/os-floating-ip-dns/%s/entries/%s", domain, name)).execute().getList();
    }

    @Override
    public DNSEntry create(String domain, String name, String ip, DNSRecordType type) {
        checkNotNull(domain, "domain");
        checkNotNull(name, "name");
        checkNotNull(ip, "ip");
        checkNotNull(type, "type");

        return put(ExtDNSEntry.class, uri("/os-floating-ip-dns/%s/entries/%s", domain, name))
                .entity(new ExtDNSEntry(ip, type))
                .execute();
    }

    @Override
    public DNSEntry modifyIP(String domain, String name, String ip) {
        checkNotNull(domain, "domain");
        checkNotNull(name, "name");
        checkNotNull(ip, "ip");

        return put(ExtDNSEntry.class, uri("/os-floating-ip-dns/%s/entries/%s", domain, name))
                   .entity(new ExtDNSEntry(ip, DNSRecordType.A))
                   .execute();
    }

    @Override
    public ActionResponse delete(String domain, String name) {
        checkNotNull(domain, "domain");
        checkNotNull(name, "name");

        return delete(ActionResponse.class, uri("/os-floating-ip-dns/%s/entries/%s", domain, name)).execute();
    }

}
