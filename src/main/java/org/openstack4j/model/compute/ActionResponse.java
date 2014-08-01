package org.openstack4j.model.compute;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * A response that is returned when an Action is performed against the server.  If {@link #isSuccess()} is true then the fault will always be null. The fault
 * will indicate why the action has failed.  Any other transport/communication errors will be thrown as with any other API calls.
 *  
 * @author Jeremy Unruh
 */
public class ActionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	String message;
	
	private ActionResponse() { }
	private ActionResponse(String message) { 
		this.message = message;
	}
	
	public static ActionResponse actionSuccess() {
		return new ActionResponse();
	}
	
	public static ActionResponse actionFailed(String message) {
		return new ActionResponse(message);
	}
	
	/**
	 * @return true if the action was successful
	 */
	public boolean isSuccess() {
		return message == null;
	}
	
	/**
	 * @return the fault if the action was unsuccessful otherwise null
	 */
	public String getFault() {
		return message;
	}
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues().add("success", message == null).add("fault", message).toString();
	}
	
}
