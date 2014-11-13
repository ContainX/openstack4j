package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.compute.FlavorService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.openstack.compute.domain.ExtraSpecsWrapper;
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
	public ActionResponse delete(String flavorId) {
  	checkNotNull(flavorId);
  	return deleteWithResponse(uri("/flavors/%s", flavorId)).execute();
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> listExtraSpecs(String flavorId) {
		checkNotNull(flavorId);
		return get(ExtraSpecsWrapper.class, uri("/flavors/%s/os-extra_specs", flavorId)).execute().getExtraSpecs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> createAndUpdateExtraSpecs(String flavorId, Map<String, String> spec) {
		checkNotNull(flavorId);
		checkNotNull(spec);
		return post(ExtraSpecsWrapper.class, uri("/flavors/%s/os-extra_specs", flavorId)).entity(ExtraSpecsWrapper.wrap(spec)).execute().getExtraSpecs();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteExtraSpecs(String flavorId, String key) {
		checkNotNull(flavorId);
		checkNotNull(key);
		delete(Void.class, uri("/flavors/%s/os-extra_specs/%s", flavorId, key)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String getSpec(String flavorId, String key) {
		checkNotNull(flavorId);
		checkNotNull(key);
		Map extraSpec = get(HashMap.class, uri("/flavors/%s/os-extra_specs/%s/", flavorId, key)).execute();
		return extraSpec == null ? null : (String) extraSpec.get(key);
	}

}
