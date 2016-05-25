package org.openstack4j.model.senlin;

import org.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Receiver.
 * All getters map to the possible return values of
 * <code> GET /v1/receivers/​{receiver_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Receiver extends ResourceEntity {

	/**
	 * Returns the WebHook of the receiver
	 *
	 * @return the WebHook of the receiver
	 */
	String getWebHook();
}
