package org.openstack4j.model.octavia.builder;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.octavia.L7Rule;

public interface L7RuleBuilder extends Buildable.Builder<L7RuleBuilder, L7Rule> {
	
	L7RuleBuilder adminStateUp(boolean adminStateUp);

	L7RuleBuilder compareType(String compareType);
	
	L7RuleBuilder invert(boolean invert);
	
	L7RuleBuilder key(String key);
	
	L7RuleBuilder projectId(String projectId);
	
	L7RuleBuilder tags(List<String> tags);
	
	L7RuleBuilder type(String type);
	
	L7RuleBuilder value(String value);

}
