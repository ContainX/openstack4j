package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

public interface ScalingGroupInstance extends ModelEntity {
	String getInstanceId();
	
	String getInstanceName();
	
	String getGroupId();
	
	String getGroupName();
	
	String getLifeCycleState();
	
	String getHealthStatus();
	
	String getConfigName();
	
	String getConfigId();
	
	String getCreateTime();
}
