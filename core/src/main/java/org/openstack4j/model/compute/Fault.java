package org.openstack4j.model.compute;

import java.util.Date;

import org.openstack4j.model.ModelEntity;

/**
 * Represents a Fault with the Nova OpenStack service
 * 
 * @author Jeremy Unruh
 */
public interface Fault extends ModelEntity {

	/**
	 * @return the fault code
	 */
	int getCode();

	/**
	 * @return the message of the fault
	 */
	String getMessage();

	/**
	 * @return details for the fault
	 */
	String getDetails();

	/**
	 * @return the date the fault was created
	 */
	Date getCreated();
	
}
