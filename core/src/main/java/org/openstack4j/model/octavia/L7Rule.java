package org.openstack4j.model.octavia;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.octavia.builder.L7RuleBuilder;

public interface L7Rule extends ModelEntity, Buildable<L7RuleBuilder> {

	 String getCompareType();
	 
	 String getProvisioningStatus();
	 
	 boolean getInvert();
	 
	 boolean getAdminStateUp();
	 
	 String getValue();
	 
	 String getKey();
	 
	 String getProjectId();
	 
	 String getType();
	 
	 String getId();
	 
	 String getOperatingStatus();
	 
	 List<String> getTags();
	 
	 String getL7PolicyId();
}
