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
package com.huawei.openstack4j.api.manila;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Extension;
import com.huawei.openstack4j.model.manila.*;
import com.huawei.openstack4j.openstack.manila.domain.ManilaService;

/**
 * Shared File Systems API (Manila)
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareService extends RestService {
    /**
     * @return a list of available Shared File Systems API extensions
     */
    List<? extends Extension> listExtensions();

    /**
     * @return the resource limitations allowed for the tenant
     */
    Limits limits();

    /**
     * @return service which provides methods to manage shares
     */
    SharesService shares();

    /**
     * @return service which provides methods to manage security services
     */
    SecurityServiceService securityServices();

    /**
     * @return service which provides methods to manage share snapshots
     */
    ShareSnapshotService shareSnapshots();

    /**
     * @return service which provides methods to manage share networks
     */
    ShareNetworkService shareNetworks();

    /**
     * @return service which provides methods to manage share servers
     */
    ShareServerService shareServers();

    /**
     * @return service which provides methods to manage share instances
     */
    ShareInstanceService shareInstances();

    /**
     * @return service which provides methods to manage share types
     */
    ShareTypeService shareTypes();

    /**
     * @return service which provides methods to query scheduler stats
     */
    SchedulerStatsService schedulerStats();

    /**
     * Lists all services.
     *
     * @return a list of all services
     */
    List<? extends Service> services();

    /**
     * Enables a service.
     *
     * @param binary the name of the service binary that you want to enable
     * @param host the host name of the service that you want to enable
     * @return the status of the enabled service
     */
    ManilaService.ServiceStatus enableService(String binary, String host);

    /**
     * Disables a service.
     *
     * @param binary the name of the service binary that you want to disable
     * @param host the host name of the service that you want to disable
     * @return the status of the disabled service
     */
    ManilaService.ServiceStatus disableService(String binary, String host);

    /**
     * Lists all availability zones.
     *
     * @return a list of all availability zones
     */
    List<? extends AvailabilityZone> availabilityZones();

    /**
     * Configures Shared File Systems to manage a share.
     *
     * @param shareManage the share to manage
     * @return the managed share
     */
    Share manageShare(ShareManage shareManage);

    /**
     * Configures Shared File Systems to stop managing a share.
     *
     * @param shareId the share ID
     * @return the action response
     */
    ActionResponse unmanageShare(String shareId);

    /**
     * @return service which provides methods to manage quota sets
     */
    QuotaSetService quotaSets();
}
