package org.openstack4j.model.octavia;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.octavia.builder.L7RuleUpdateBuilder;

public interface L7RuleUpdate extends ModelEntity, Buildable<L7RuleUpdateBuilder> {
	String getCompareType();

	boolean getInvert();
	
	boolean getAdminStateUp();
	
	String getValue();
	
	String getKey();
	
	String getType();
	
	List<String> getTags();
}
