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
package com.huawei.openstack4j.model.manila;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Set of quotas for shares.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface QuotaSet extends ModelEntity {
    /**
     * @return the UUID of the tenant for which you manage quotas
     */
    String getId();

    /**
     * @return the number of gigabytes allowed for each tenant
     */
    Integer getGigabytes();

    /**
     * @return the number of snapshots allowed for each tenant
     */
    Integer getSnapshots();

    /**
     * @return the number of shares allowed for each tenant
     */
    Integer getShares();

    /**
     * @return the number of gigabytes for the snapshots allowed for each tenant
     */
    Integer getSnapshotGigabytes();

    /**
     * @return the number of share networks allowed for each tenant
     */
    Integer getShareNetworks();
}
