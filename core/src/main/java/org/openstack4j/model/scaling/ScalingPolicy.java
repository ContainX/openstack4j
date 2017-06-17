package org.openstack4j.model.scaling;

public interface ScalingPolicy extends ScalingPolicyCreateUpdate {
	String getPolicyStatus();
	
	String getCreateTime();
}
