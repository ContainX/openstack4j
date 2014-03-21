package org.openstack4j.model.network;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;

/**
 * An OpenStack (Neutron) network
 * 
 * @author Jeremy Unruh
 */
public interface Network extends ModelEntity, Buildable {

	/**
	 * @return the status of the network
	 */
	State getStatus();

	/**
	 * @return list of subnet identifiers associated with the network
	 */
	List<String> getSubnets();

	/**
	 * @return the name of the network
	 */
	String getName();

	/**
	 * @return the physical network provider or null
	 */
	String getProviderPhyNet();

	/**
	 * @return true if the administrative state is up
	 */
	boolean isAdminStateUp();

	/**
	 * @return the tenant associated with the network
	 */
	String getTenantId();

	/**
	 * @return the network type
	 */
	NetworkType getNetworkType();

	/**
	 * @return true if the router is external
	 */
	boolean isRouterExternal();

	/**
	 * @return the id of this network
	 */
	String getId();


	/**
	 * @return true if the network is shared
	 */
	boolean isShared();

	/**
	 * @return the provider segmentation identifier
	 */
	String getProviderSegID();

}
