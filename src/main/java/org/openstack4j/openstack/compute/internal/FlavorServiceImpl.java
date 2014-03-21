package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.openstack.compute.domain.NovaFlavor;
import org.openstack4j.openstack.compute.domain.NovaFlavor.Flavors;

/**
 * Flavor service provides CRUD capabilities for Flavor(s).  A flavor is an available hardware configuration/template for a server
 * 
 * @author Jeremy Unruh
 */
public class FlavorServiceImpl extends BaseComputeServices implements FlavorService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Flavor> list() {
		return get(Flavors.class, uri("/flavors/detail")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Flavor get(String flavorId) {
  	checkNotNull(flavorId);
		return get(NovaFlavor.class, uri("/flavors/%s", flavorId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String flavorId) {
  	checkNotNull(flavorId);
  	delete(Void.class, uri("/flavors/%s", flavorId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Flavor create(Flavor flavor) {
		checkNotNull(flavor);
		return post(NovaFlavor.class, uri("/flavors"))
		    .entity(flavor)
		    .execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Flavor create(String name, int ram, int vcpus, int disk, int ephemeral, int swap, float rxtxFactor, boolean isPublic) {
		checkNotNull(name);
		return create(NovaFlavor.builder().name(name).ram(ram).vcpus(vcpus).disk(disk).swap(swap).rxtxFactor(rxtxFactor).isPublic(isPublic).build());
	}

}
