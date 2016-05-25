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

	NodeCreateBuilder clusterID(String clusterID);

	NodeCreateBuilder metadata(Map<String, String> metadata);

	NodeCreateBuilder name(String name);

	NodeCreateBuilder profileID(String profileID);

	NodeCreateBuilder role(String role);
}
