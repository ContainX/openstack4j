package org.openstack4j.api.compute;

import java.util.List;

import javax.annotation.Nullable;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.Keypair;

/**
 * Keypair Service manages SSH Keys within OpenStack Compute (Nova).
 *
 * @author Jeremy Unruh
 */
public interface KeypairService extends RestService {

	/**
	 * Lists keypairs that are associated with the account making the request
	 *
	 * @return the list of keypairs
	 */
	List<? extends Keypair> list();
	
	/**
	 * Gets the keypair by name
	 *
	 * @param name the keypair name
	 * @return the keypair
	 */
	Keypair get(String name);
	
	/**
	 * Deletes the keypair by name
	 *
	 * @param name the keypair name
	 * @return the action response
	 */
	ActionResponse delete(String name);
	
	/**
	 * Generates or imports a keypair
	 *
	 * @param name the name of the keypair
	 * @param publicKey the public key (optional), Null indicates one will be generated
	 * @return the newly created keypair
	 */
	Keypair create(String name, @Nullable String publicKey);
	
}
