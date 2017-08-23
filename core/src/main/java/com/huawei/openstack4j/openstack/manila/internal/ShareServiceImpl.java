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

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.manila.*;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Extension;
import com.huawei.openstack4j.model.manila.*;
import com.huawei.openstack4j.openstack.common.ExtensionValue;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.manila.domain.ManilaAvailabilityZone;
import com.huawei.openstack4j.openstack.manila.domain.ManilaLimits;
import com.huawei.openstack4j.openstack.manila.domain.ManilaService;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShare;
import com.huawei.openstack4j.openstack.manila.domain.actions.ServiceAction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Shared File Systems API (Manila)
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ShareServiceImpl extends BaseShareServices implements ShareService {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Extension> listExtensions() {
        return get(ExtensionValue.Extensions.class, uri("/extensions")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Limits limits() {
        return get(ManilaLimits.class, uri("/limits")).execute();
    }

    /**
     * {@inheritDoc}
     */
    public SharesService shares() {
        return Apis.get(SharesService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityServiceService securityServices() {
        return Apis.get(SecurityServiceService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareSnapshotService shareSnapshots() {
        return Apis.get(ShareSnapshotService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareNetworkService shareNetworks() {
        return Apis.get(ShareNetworkService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareServerService shareServers() {
        return Apis.get(ShareServerService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareInstanceService shareInstances() {
        return Apis.get(ShareInstanceService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareTypeService shareTypes() {
        return Apis.get(ShareTypeService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerStatsService schedulerStats() {
        return Apis.get(SchedulerStatsService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Service> services() {
        return get(ManilaService.Services.class, uri("/os-services")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ManilaService.ServiceStatus enableService(String binary, String host) {
        checkNotNull(binary);
        checkNotNull(host);

        return put(ManilaService.ServiceStatus.class, uri("/os-services/enable"))
                .entity(ServiceAction.enable(binary, host))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ManilaService.ServiceStatus disableService(String binary, String host) {
        checkNotNull(binary);
        checkNotNull(host);

        return put(ManilaService.ServiceStatus.class, uri("/os-services/disable"))
                .entity(ServiceAction.disable(binary, host))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends AvailabilityZone> availabilityZones() {
        return get(ManilaAvailabilityZone.AvailabilityZones.class, uri("/os-availability-zone")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Share manageShare(ShareManage shareManage) {
        checkNotNull(shareManage);

        return post(ManilaShare.class, uri("/os-share-manage"))
                .entity(shareManage)
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse unmanageShare(String shareId) {
        checkNotNull(shareId);

        return ToActionResponseFunction.INSTANCE.apply(
                post(Void.class, uri("/os-share-unmanage/%s/unmanage", shareId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaSetService quotaSets() {
        return Apis.get(QuotaSetService.class);
    }
}
