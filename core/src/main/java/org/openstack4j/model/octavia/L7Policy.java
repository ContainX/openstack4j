package org.openstack4j.model.octavia;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.octavia.builder.L7PolicyBuilder;
import org.openstack4j.openstack.octavia.domain.ListItem;

public interface L7Policy extends ModelEntity, Buildable<L7PolicyBuilder> {
	 String getId();
	 String getName();
	 String getListenerId();
	 String getDescription();
	 boolean getAdminStateUp();
	 List<ListItem> getRules();
	 String getProjectId();
	 String getProvisioningStatus();
	 String getRedirectPoolId();
	 String getRedirectUrl();
	 String getAction();
	 int getPosition();
	 String getOperatingStatus();
}
