package org.openstack4j.model.octavia;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.octavia.builder.L7PolicyUpdateBuilder;

public interface L7PolicyUpdate extends ModelEntity, Buildable<L7PolicyUpdateBuilder> {
	 String getName();
		
	 String getDescription();
	 
	 boolean getAdminStateUp();
	 
	 String getProvisioningStatus();
	 
	 String getRedirectPoolId();
	 
	 String getRedirectUrl();
	 
	 String getAction();
	 
	 int getPosition();
	 
	 String getOperatingStatus();
}
