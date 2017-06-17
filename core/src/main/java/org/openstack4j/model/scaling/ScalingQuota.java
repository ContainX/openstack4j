package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

public interface ScalingQuota extends ModelEntity {
	String getType();
	
	Integer getUsed();
	
	Integer getQuota();
	
	Integer getMax();
}
