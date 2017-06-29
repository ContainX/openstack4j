package org.openstack4j.model.storage.block;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.ResourceEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:10:39
 */
public interface VolumeBackupPolicy extends ResourceEntity {

	public enum VolumeBackupPolicyStatus {
		ON, OFF;
	}

	/**
	 * 
	 * @return volume backup scheduled policy
	 */
	VolumeBackupScheduledPolicy getScheduledPolicy();

	public interface VolumeBackupScheduledPolicy extends ModelEntity {
		/**
		 * known as "rentention_num" in API documentation
		 * 
		 * @return max backup amount for a volume
		 */
		public Integer getMaxBackupAmount();

		/**
		 * backup scheduled frequency, every "frequency" days
		 * @return
		 */
		public Integer getFrequency();

		/**
		 * if true, the initial data backup in the current month will be retained
		 * @return
		 */
		public Boolean getRetainFirstBackupOfCurrentMonth();

		/**
		 * 
		 * @return scheduled at time, 12:00 for example
		 */
		public String getStartTime();

		/**
		 * 
		 * @return volume backup policy status, includes "ON", "OFF"
		 */
		public VolumeBackupPolicyStatus getStatus();
	}

}
