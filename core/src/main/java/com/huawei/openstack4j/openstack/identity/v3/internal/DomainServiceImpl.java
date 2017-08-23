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
package com.huawei.openstack4j.openstack.identity.v3.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.List;

import com.huawei.openstack4j.api.identity.v3.DomainService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneDomain;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneDomain.Domains;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class DomainServiceImpl extends BaseOpenStackService implements DomainService {

    @Override
    public Domain create(Domain domain) {
        checkNotNull(domain);
        return post(KeystoneDomain.class, PATH_DOMAINS).entity(domain).execute();
    }

    @Override
    public Domain create(String name, String description, boolean enabled) {
        checkNotNull(name);
        checkNotNull(description);
        checkNotNull(enabled);
        return create(KeystoneDomain.builder().name(name).description(description).enabled(enabled).build());
    }

    @Override
    public Domain update(Domain domain) {
        checkNotNull(domain);
        return patch(KeystoneDomain.class, PATH_DOMAINS, "/", domain.getId()).entity(domain).execute();
    }

    @Override
    public Domain get(String domainId) {
        checkNotNull(domainId);
        return get(KeystoneDomain.class, PATH_DOMAINS, "/", domainId).execute();
    }

    @Override
    public List<? extends Domain> getByName(String domainName) {
        checkNotNull(domainName);
        return get(Domains.class, uri(PATH_DOMAINS)).param("name", domainName).execute().getList();
    }

    @Override
    public ActionResponse delete(String domainId) {
        checkNotNull(domainId);
        return deleteWithResponse(PATH_DOMAINS, "/", domainId).execute();
    }

    @Override
    public List<? extends Domain> list() {
        return get(Domains.class, uri(PATH_DOMAINS)).execute().getList();
    }

}
