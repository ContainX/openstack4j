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
package org.openstack4j.openstack.storage.block.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.storage.AsyncVolumeBackupJobService;
import org.openstack4j.api.storage.AsyncVolumeBackupService;
import org.openstack4j.api.storage.BlockQuotaSetService;
import org.openstack4j.api.storage.BlockStorageService;
import org.openstack4j.api.storage.BlockVolumeBackupPolicyService;
import org.openstack4j.api.storage.BlockVolumeBackupService;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.api.storage.BlockVolumeSnapshotService;
import org.openstack4j.api.storage.CinderZoneService;
import org.openstack4j.api.storage.SchedulerStatsGetPoolService;
import org.openstack4j.api.storage.ext.BlockStorageServiceService;
import org.openstack4j.model.storage.block.BlockLimits;
import org.openstack4j.openstack.storage.block.domain.CinderBlockLimits;

/**
 * Block Storage (Cinder) Service Operation implementation
 * 
 * @author Jeremy Unruh
 */
public class BlockStorageServiceImpl extends BaseBlockStorageServices implements BlockStorageService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BlockVolumeService volumes() {
		return Apis.get(BlockVolumeService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BlockVolumeSnapshotService snapshots() {
		return Apis.get(BlockVolumeSnapshotService.class);
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public BlockLimits getLimits() {
        return get(CinderBlockLimits.class, "/limits").execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockQuotaSetService quotaSets() {
        return Apis.get(BlockQuotaSetService.class);
    }

    @Override
    public CinderZoneService zones()
    {
       return Apis.get(CinderZoneService.class); 
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SchedulerStatsGetPoolService schedulerStatsPools() { return Apis.get(SchedulerStatsGetPoolService.class); }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BlockVolumeBackupService backups() { 
		return Apis.get(BlockVolumeBackupService.class); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BlockStorageServiceService services() {
		return Apis.get(BlockStorageServiceService.class);
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public AsyncVolumeBackupService asyncBackups() {
		return Apis.get(AsyncVolumeBackupService.class);
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public AsyncVolumeBackupJobService jobs() {
		return Apis.get(AsyncVolumeBackupJobService.class);
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public BlockVolumeBackupPolicyService policies() {
		return Apis.get(BlockVolumeBackupPolicyService.class);
	}

}
