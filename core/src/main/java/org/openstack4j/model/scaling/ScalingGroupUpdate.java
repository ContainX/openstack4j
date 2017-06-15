package org.openstack4j.model.scaling;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

public interface ScalingGroupUpdate extends ModelEntity {
	/**
	 * @return scaling group id
	 */
	String groupId();
	
	/**
	 * @return the name for the scaling group
	 */
	String groupName();
	
	/**
	 * 
	 * @return desire instance number
	 */
	Integer desireInstanceNumber();
	
	/**
	 * 
	 * @return minimal instance number
	 */
	Integer minInstanceNumber();
	
	/**
	 * 
	 * @return maximal instance number
	 */
	Integer maxInstanceNumber();
	
	/**
	 * 
	 * @return cool down time
	 */
	Integer coolDownTime();
	
	/**
	 * 
	 * @return availability zones
	 */
	List<String> availabilityZones();
	
	/**
	 * 
	 * @return networks information
	 */
	//TODO check
	List<IdResourceEntity> networks();
	
	/**
	 * 
	 * @return security groups information
	 */
	//TODO check
	List<IdResourceEntity> securityGroups();
	
	/**
	 * 
	 * @return load balance listener id
	 */
	String lbListenerId();
	
	/**
	 * 
	 * @return health periodic audit method
	 */
	String healthPeriodicAuditMethod();
	
	/**
	 * 
	 * @return health periodic audit time
	 */
	Integer healthPeriodicAuditTime();
	
	/**
	 * 
	 * @return instance terminate policy
	 */
	String instanceTerminatePolicy();
	
	/**
	 * 
	 * @return scaling configuration id
	 */
	String configId();
	
	/**
	 * 
	 * @return notification method
	 */
	List<String> notifications();
	
	/**
	 * 
	 * @return whether delete server's ip
	 */
	Boolean deletePublicip();
}
