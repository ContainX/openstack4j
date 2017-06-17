package org.openstack4j.model.scaling;

public interface ScalingConfig extends ScalingConfigCreate {
	String getTenant();
	
	String getCreateTime();
}
