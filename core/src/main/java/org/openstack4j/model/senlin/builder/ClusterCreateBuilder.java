package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.ClusterCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link ClusterCreate} objects
 * 
 * @author lion
 */
public interface ClusterCreateBuilder extends Buildable.Builder<ClusterCreateBuilder, ClusterCreate> {

	ClusterCreateBuilder name(String name);

	ClusterCreateBuilder metadata(Map<String, Object> metadata);

	ClusterCreateBuilder desired_capacity(String desired_capacity);

	ClusterCreateBuilder max_size(String max_size);

	ClusterCreateBuilder min_size(String min_size);

	ClusterCreateBuilder profile_id(String profile_id);

	ClusterCreateBuilder timeout(String timeout);

}
