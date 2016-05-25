package org.openstack4j.model.senlin.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.senlin.ReceiverCreate;

import java.util.Map;

/**
 * This interface describes a builder for {@link ReceiverCreate} objects
 * 
 * @author lion
 */
public interface ReceiverCreateBuilder extends Buildable.Builder<ReceiverCreateBuilder, ReceiverCreate> {

	ReceiverCreateBuilder action(String action);

	ReceiverCreateBuilder clusterID(String clusterID);

	ReceiverCreateBuilder name(String name);

	ReceiverCreateBuilder type(String type);

	ReceiverCreateBuilder params(Map<String, String> params);

}
