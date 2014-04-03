package org.openstack4j.model.compute;

import org.openstack4j.model.ModelEntity;

/**
 * An OpenStack Keypair is an SSH Key
 * 
 * @author Jeremy Unruh
 */
public interface Keypair extends ModelEntity {
	
	/**
	 * The name associated with the keypair
	 *
	 * @return the name of the keypair
	 */
	String getName();
	
	/**
	 * The public SSH key
	 *
	 * @return the public key
	 */
	String getPublicKey();
	
	/**
	 * @return the server fingerprint
	 */
	String getFingerprint();

}
