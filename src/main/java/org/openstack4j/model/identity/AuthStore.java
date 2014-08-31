package org.openstack4j.model.identity;

/**
 * An entity which holds enough information in store to re-authenticate at any given time during a session
 * 
 * @author Jeremy Unruh
 */
public interface AuthStore {

	/**
	 * @return the version of this authentication store type
	 */
	AuthVersion getVersion();
}
