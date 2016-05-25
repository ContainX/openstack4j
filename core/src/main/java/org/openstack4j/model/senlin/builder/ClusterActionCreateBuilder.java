package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.ClusterActionCreate;

import java.util.ArrayList;
import java.util.Map;

/**
 * This interface describes a builder for {@link ClusterActionCreate} objects
 * 
 * @author lion
 */
public interface ClusterActionCreateBuilder extends Buildable.Builder<ClusterActionCreateBuilder, ClusterActionCreate> {

	ClusterActionCreateBuilder addNodes(Map<String, ArrayList<String>> addNodes);

	ClusterActionCreateBuilder delNodes(Map<String, ArrayList<String>> delNodes);

	ClusterActionCreateBuilder scaleOut(Map<String, String> scaleOut);

	ClusterActionCreateBuilder scaleIn(Map<String, String> scaleIn);

	ClusterActionCreateBuilder resize(Map<String, String> resize);

	ClusterActionCreateBuilder check(Map<String, String> check);

	ClusterActionCreateBuilder recover(Map<String, String> recover);

	ClusterActionCreateBuilder policyAttach(Map<String, String> policyAttach);

	ClusterActionCreateBuilder policyDetach(Map<String, String> policyDetach);

	ClusterActionCreateBuilder policyUpdate(Map<String, String> policyUpdate);

}
