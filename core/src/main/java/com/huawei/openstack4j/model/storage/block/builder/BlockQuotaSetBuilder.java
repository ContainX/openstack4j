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
package com.huawei.openstack4j.model.storage.block.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.storage.block.BlockQuotaSet;

/**
 * Builder for a QuotaSet model class.
 * 
 * @author Jeremy Unruh
 */
public interface BlockQuotaSetBuilder extends Builder<BlockQuotaSetBuilder, BlockQuotaSet> {

    /**
     * Volumes Quota for Block Storage
     * 
     * @param volumes
     * @return volumes consumed in the Block Storage.
     */
    BlockQuotaSetBuilder volumes(int volumes);

    /**
     * Snapshots present in Block Storage
     *
     * @param snapshots
     * @return snapshots present in the Block Storage.
     */
    BlockQuotaSetBuilder snapshots(int snapshots);

    /**
     * Space consumed in gigabytes for Block Storage
     * @param gigabytes
     * @return space consumed in the Block Storage.
     */
    BlockQuotaSetBuilder gigabytes(int gigabytes);
}
