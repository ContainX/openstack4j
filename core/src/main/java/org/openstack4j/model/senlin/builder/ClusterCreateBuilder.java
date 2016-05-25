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

	ClusterCreateBuilder metadata(Map<String, String> metadata);

	ClusterCreateBuilder desiredCapacity(int desiredCapacity);

	ClusterCreateBuilder maxSize(int maxSize);

	ClusterCreateBuilder minSize(int minSize);

	ClusterCreateBuilder profileID(String profileID);

	ClusterCreateBuilder timeout(int timeout);

}
