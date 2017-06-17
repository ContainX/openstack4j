package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

public interface ScalingConfigCreate extends ModelEntity {
	
	String getConfigId();
	
	String getConfigName();
	
	InstanceConfig getInstanceConfig();
}
