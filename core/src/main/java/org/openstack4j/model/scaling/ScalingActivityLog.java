package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

public interface ScalingActivityLog extends ModelEntity {
	String getStatus();
	
	String getStartTime();
	
	String getEndTime();
	
	String getId();
	
	String getInstanceRemovedList();
	
	String getInstanceDeletedList();
	
	String getInstanceAddedList();
	
	String getScalingValue();
	
	String getDescription();
	
	Integer getInstanceValue();
	
	Integer getDesireValue();
}
