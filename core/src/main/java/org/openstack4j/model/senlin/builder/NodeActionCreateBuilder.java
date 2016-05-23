package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.NodeActionCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link NodeActionCreate} objects
 * 
 * @author lion
 */
public interface NodeActionCreateBuilder extends Buildable.Builder<NodeActionCreateBuilder, NodeActionCreate> {

	NodeActionCreateBuilder check(Map<String, Object> check);

	NodeActionCreateBuilder recover(Map<String, Object> recover);

}
