package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.PolicyCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link PolicyCreate} objects
 * 
 * @author lion
 */
public interface PolicyCreateBuilder extends Buildable.Builder<PolicyCreateBuilder, PolicyCreate> {

	PolicyCreateBuilder name(String name);

	PolicyCreateBuilder spec(Map<String, Object> spec);

	PolicyCreateBuilder properties(Map<String, Object> properties);

	PolicyCreateBuilder adjustment(Map<String, String> adjustment);

	PolicyCreateBuilder minStep(int minStep);

	PolicyCreateBuilder number(int number);

	PolicyCreateBuilder adjustmentType(String adjustmentType);

	PolicyCreateBuilder event(String event);

	PolicyCreateBuilder specType(String specType);

	PolicyCreateBuilder version(String version);

}
