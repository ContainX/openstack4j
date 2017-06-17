package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

public interface ScalingPolicyCreateUpdate extends ModelEntity {
	String getPolicyId();
	
	String getPolicyName();
	
	String getGroupId();
	
	String getPolicyType();
	
	String getAlarmId();
	
	ScheduledPolicy getScheduledPolicy();
	
	ScalingPolicyAction getScalingPolicyAction();
	
	Integer getCoolDownTime();
}
