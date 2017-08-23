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

 

/**
 * The Storage builders
 */
public interface StorageBuilders {

    /**
     * The builder which creates a BlockQuotaSet
     *
     * @return the block quota-set builder
     */
    public BlockQuotaSetBuilder blockQuotaSet();

    /**
     * The builder to create a Block Volume
     *
     * @return the volume builder
     */
    public VolumeBuilder volume();

    /**
     * The builder to create a Block Volume Snapshot
     *
     * @return the snapshot builder
     */
    public VolumeSnapshotBuilder volumeSnapshot();
    
    /**
     * The builder to create a volume backup
     * @return the backup creation builder
     */
    public VolumeBackupCreateBuilder volumeBackupCreate();

}
