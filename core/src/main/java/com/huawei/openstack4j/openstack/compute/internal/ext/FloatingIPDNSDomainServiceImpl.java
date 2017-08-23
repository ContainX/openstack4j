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

import com.huawei.openstack4j.api.compute.ext.FloatingIPDNSDomainService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.ext.DomainEntry;
import com.huawei.openstack4j.model.compute.ext.DomainEntry.Scope;
import com.huawei.openstack4j.openstack.compute.domain.ext.ExtDomainEntry;
import com.huawei.openstack4j.openstack.compute.domain.ext.ExtDomainEntry.DomainEntries;
import com.huawei.openstack4j.openstack.compute.internal.BaseComputeServices;

/**
 * Service that handles domain names for the floating IP DNS Extension
 * 
 * @author Jeremy Unruh
 */
public class FloatingIPDNSDomainServiceImpl extends BaseComputeServices implements FloatingIPDNSDomainService {

    @Override
    public List<? extends DomainEntry> list() {
        return get(DomainEntries.class, uri("/os-floating-ip-dns")).execute().getList();
    }

    @Override
    public ActionResponse delete(String domainName) {
        checkNotNull(domainName);
        return delete(ActionResponse.class, uri("/os-floating-ip-dns/%s", domainName)).execute();
    }

    @Override
    public DomainEntry createPublic(String domainName, String project) {
        checkNotNull(domainName);
        return put(ExtDomainEntry.class, uri("/os-floating-ip-dns/%s", domainName))
                  .entity(new ExtDomainEntry(Scope.PUBLIC, null, project))
                  .execute();
    }

    @Override
    public DomainEntry createPrivate(String domainName, String availabilityZone) {
        checkNotNull(domainName);
        return put(ExtDomainEntry.class, uri("/os-floating-ip-dns/%s", domainName))
                  .entity(new ExtDomainEntry(Scope.PRIVATE, availabilityZone, null))
                  .execute();
    }

}
