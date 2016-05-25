package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Event.
 * All getters map to the possible return values of
 * <code> GET /v1/events/​{event_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Event extends ModelEntity {

	/**
	 * Returns the id of the Event
	 * 
	 * @return the id of the Event
	 */
	String getId();

}
