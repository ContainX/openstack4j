package com.huawei.openstack4j.model.storage.block;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 21:21:16
 */
public interface VolumeBackupPolicyResource extends ModelEntity {
	
	/**
	 * 
	 * @return resource id
	 */
	public String getId();

	/**
	 * 
	 * @return resource type
	 */
	public String getType();

	/**
	 * 
	 * @return resource availability zone
	 */
	public String getAvailabilityZone();

	/**
	 * 
	 * @return the POD resource belongs to
	 */
	public String getOsVolHostAttr();
	
	
	public interface VolumeBackupPolicyResourceActionResult extends ModelEntity {
		
		List<? extends VolumeBackupPolicyResource> getSuccessResources();
		
		List<? extends VolumeBackupPolicyResource> getFailResources();

	}

}

