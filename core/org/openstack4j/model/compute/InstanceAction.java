package org.openstack4j.model.compute;

import src.main.java.org.openstack4j.model.ModelEntity;

/**
 * An action executed on an instance
 * 
 * @author Christian Banse
 */
public interface InstanceAction extends ModelEntity {

	/**
	 * @return the action
	 */
	Action getAction();

	/**
	 * @return the instance uuid
	 */
	String getInstanceUuid();

	/**
	 * @return the message
	 */
	String getMessage();

	/**
	 * @return the project id
	 */
	String getProjectId();

	/**
	 * @return the request id
	 */
	String getRequestId();

	/**
	 * @return the start time
	 */
	Date getStartTime();

	/**
	 * @return the user id
	 */
	String getUserId();
}
