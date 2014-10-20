package org.openstack4j.openstack.compute.domain;

import java.util.Date;

import org.openstack4j.model.compute.Fault;

/**
 * A server Fault
 * 
 * @author Jeremy Unruh
 */
public class NovaFault implements Fault {

	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private String details;
	private Date created;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCode() {
		return code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDetails() {
		return details;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreated() {
		return created;
	}

}
