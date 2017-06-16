package org.openstack4j.model.scaling;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

public interface ScalingGroupUpdate extends ModelEntity {
	/**
	 * @return scaling group id
	 */
	String getGroupId();
	
	/**
	 * @return the name for the scaling group
	 */
	String getGroupName();
	
	/**
	 * 
	 * @return desire instance number
	 */
	Integer getDesireInstanceNumber();
	
	/**
	 * 
	 * @return minimal instance number
	 */
	Integer getMinInstanceNumber();
	
	/**
	 * 
	 * @return maximal instance number
	 */
	Integer getMaxInstanceNumber();
	
	/**
	 * 
	 * @return cool down time
	 */
	Integer getCoolDownTime();
	
	/**
	 * 
	 * @return availability zones
	 */
	List<String> getAvailabilityZones();
	
	/**
	 * 
	 * @return networks information
	 */
	//TODO check
	List<IdResourceEntity> getNetworks();
	
	/**
	 * 
	 * @return security groups information
	 */
	//TODO check
	List<IdResourceEntity> getSecurityGroups();
	
	/**
	 * 
	 * @return load balance listener id
	 */
	String getLbListenerId();
	
	/**
	 * 
	 * @return health periodic audit method
	 */
	String getHealthPeriodicAuditMethod();
	
	/**
	 * 
	 * @return health periodic audit time
	 */
	Integer getHealthPeriodicAuditTime();
	
	/**
	 * 
	 * @return instance terminate policy
	 */
	String getInstanceTerminatePolicy();
	
	/**
	 * 
	 * @return scaling configuration id
	 */
	String getConfigId();
	
	/**
	 * 
	 * @return notification method
	 */
	List<String> getNotifications();
	
	/**
	 * 
	 * @return whether delete server's ip
	 */
	Boolean getDeletePublicip();
}
