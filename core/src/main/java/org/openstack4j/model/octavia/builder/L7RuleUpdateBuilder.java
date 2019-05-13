package org.openstack4j.model.octavia.builder;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.octavia.L7RuleUpdate;

public interface L7RuleUpdateBuilder extends Buildable.Builder<L7RuleUpdateBuilder, L7RuleUpdate>  {
	L7RuleUpdateBuilder adminStateUp(boolean adminStateUp);

	L7RuleUpdateBuilder compareType(String compareType);
	
	L7RuleUpdateBuilder invert(boolean invert);
	
	L7RuleUpdateBuilder key(String key);
	
	L7RuleUpdateBuilder tags(List<String> tags);
	
	L7RuleUpdateBuilder type(String type);
	
	L7RuleUpdateBuilder value(String value);

}
