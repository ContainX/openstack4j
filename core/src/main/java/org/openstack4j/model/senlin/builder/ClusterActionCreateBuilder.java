package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.ClusterActionCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link ClusterActionCreate} objects
 * 
 * @author lion
 */
public interface ClusterActionCreateBuilder extends Buildable.Builder<ClusterActionCreateBuilder, ClusterActionCreate> {

	ClusterActionCreateBuilder add_nodes(Map<String, Object> add_nodes);

	ClusterActionCreateBuilder del_nodes(Map<String, Object> del_nodes);

	ClusterActionCreateBuilder scale_out(Map<String, Object> scale_out);

	ClusterActionCreateBuilder scale_in(Map<String, Object> scale_in);

	ClusterActionCreateBuilder resize(Map<String, Object> resize);

	ClusterActionCreateBuilder check(Map<String, Object> check);

	ClusterActionCreateBuilder recover(Map<String, Object> recover);

	ClusterActionCreateBuilder policy_attach(Map<String, Object> policy_attach);

	ClusterActionCreateBuilder policy_detach(Map<String, Object> policy_detach);

	ClusterActionCreateBuilder policy_update(Map<String, Object> policy_update);

}
