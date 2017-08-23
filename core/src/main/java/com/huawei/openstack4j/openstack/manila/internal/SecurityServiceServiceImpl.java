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
package com.huawei.openstack4j.openstack.manila.internal;

import java.util.List;

import com.huawei.openstack4j.api.manila.SecurityServiceService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.SecurityService;
import com.huawei.openstack4j.model.manila.SecurityServiceCreate;
import com.huawei.openstack4j.model.manila.SecurityServiceUpdateOptions;
import com.huawei.openstack4j.model.manila.builder.SecurityServiceCreateBuilder;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.manila.domain.ManilaSecurityService;
import com.huawei.openstack4j.openstack.manila.domain.ManilaSecurityServiceCreate;
import com.huawei.openstack4j.openstack.manila.domain.ManilaSecurityServiceUpdate;

import static com.google.common.base.Preconditions.checkNotNull;

public class SecurityServiceServiceImpl extends BaseShareServices implements SecurityServiceService {
    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityService create(SecurityServiceCreate securityServiceCreate) {
        checkNotNull(securityServiceCreate);
        return post(ManilaSecurityService.class, uri("/security-services"))
                .entity(securityServiceCreate)
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecurityService> list() {
        return list(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecurityService> listDetails() {
        return list(true);
    }

    private List<? extends SecurityService> list(boolean detail) {
        return get(ManilaSecurityService.SecurityServices.class, uri("/security-services" + (detail ? "/detail" : "")))
                .execute()
                .getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityService get(String securityServiceId) {
        checkNotNull(securityServiceId);
        return get(ManilaSecurityService.class, uri("/security-services/%s", securityServiceId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityService update(String securityServiceId, SecurityServiceUpdateOptions securityServiceUpdateOptions) {
        checkNotNull(securityServiceId);
        checkNotNull(securityServiceUpdateOptions);

        return put(ManilaSecurityService.class, uri("/security-services/%s", securityServiceId))
                .entity(ManilaSecurityServiceUpdate.fromOptions(securityServiceUpdateOptions))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String securityServiceId) {
        checkNotNull(securityServiceId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/security-services/%s", securityServiceId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityServiceCreateBuilder securityServiceCreateBuilder() {
        return ManilaSecurityServiceCreate.builder();
    }
}
