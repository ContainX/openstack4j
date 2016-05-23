package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Receiver.
 * All getters map to the possible return values of
 * <code> GET /v1/receivers/​{receiver_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface Receiver extends ModelEntity {

	/**
	 * Returns the id of the receiver
	 * 
	 * @return the id of the receiver
	 */
	String getId();

	/**
	 * Returns the name of the receiver
	 * 
	 * @return the name of the receiver
	 */
	String getName();

	/**
	 * Returns the WebHook of the receiver
	 *
	 * @return the WebHook of the receiver
	 */
	String getWebHook();
}
