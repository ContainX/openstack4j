package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.NodeCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link NodeCreate} objects
 * 
 * @author lion
 */
public interface NodeCreateBuilder extends Buildable.Builder<NodeCreateBuilder, NodeCreate> {

	NodeCreateBuilder cluster_id(String cluster_id);

	NodeCreateBuilder metadata(Map<String, Object> metadata);

	NodeCreateBuilder name(String name);

	NodeCreateBuilder profile_id(String profile_id);

	NodeCreateBuilder role(String role);
}
