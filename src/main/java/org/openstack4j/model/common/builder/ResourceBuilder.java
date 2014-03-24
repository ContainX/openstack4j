package org.openstack4j.model.common.builder;

import org.openstack4j.model.common.Resource;
import org.openstack4j.model.identity.Tenant;

/**
 * Abstract Resource Builder
 *
 * @param <M> the model type
 * @param <T> the builder type
 */
public abstract class ResourceBuilder<M extends Resource, T extends ResourceBuilder<M,T>> {

	/**
	 * Sets the name on the resource
	 *
	 * @param name the name
	 * @return the builder
	 */
	public T name(String name) {
		reference().setName(name);
		return self();
	}
	
	/**
	 * Set the Tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the builder
	 */
	public T tenantId(String tenantId) {
		reference().setTenantId(tenantId);
		return self();
	}

	/**
	 * Sets the Tenant.
	 *
	 * @param tenant the tenant
	 * @return the builder
	 */
	public T tenant(Tenant tenant) {
		reference().setTenantId(tenant.getId());
		return self();
	}
	
	/**
	 * Sets the Id.
	 *
	 * @param id the identifier
	 * @return the builder
	 */
	public T id(String id) {
		reference().setId(id);
		return self();
	}
	
	@SuppressWarnings("unchecked")
	private T self() {
		return (T) this;
	}
	
	protected abstract Resource reference();

}
