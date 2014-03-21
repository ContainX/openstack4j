package org.openstack4j.api.exceptions;

import com.google.common.base.Objects;

/**
 * Base Exception for HTTP Errors during Rest Operations
 * 
 * @author Jeremy Unruh
 */
public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 7294957362769575271L;

	protected int status;

	public ResponseException(String message, int status) {
		super(message);
		this.status = status;
	}
	
	public ResponseException(String message, int status, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	/**
	 * @return the raw status code
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				     .add("message", getMessage()).add("status", getStatus())
				     .toString();
	}
	
}
