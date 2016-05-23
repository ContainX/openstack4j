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

	PolicyCreateBuilder adjustment(Map<String, Object> adjustment);

	PolicyCreateBuilder min_step(String min_step);

	PolicyCreateBuilder number(String number);

	PolicyCreateBuilder adjustment_type(String adjustment_type);

	PolicyCreateBuilder event(String event);

	PolicyCreateBuilder spec_type(String spec_type);

	PolicyCreateBuilder version(String version);

}
