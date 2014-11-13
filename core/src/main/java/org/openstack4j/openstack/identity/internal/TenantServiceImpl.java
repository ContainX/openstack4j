package org.openstack4j.openstack.identity.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_TENANTS;

import java.util.List;

import org.openstack4j.api.identity.TenantService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.TenantUser;
import org.openstack4j.openstack.identity.domain.KeystoneTenant;
import org.openstack4j.openstack.identity.domain.KeystoneTenant.Tenants;
import org.openstack4j.openstack.identity.domain.KeystoneTenantUser.TenantUsers;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class TenantServiceImpl extends BaseOpenStackService implements TenantService  {

	
	@Override
	public List<? extends Tenant> list() {
		return get(Tenants.class, PATH_TENANTS).execute().getList();
	}

	@Override
	public Tenant get(String tenantId) {
		checkNotNull(tenantId);
		return get(KeystoneTenant.class, PATH_TENANTS, "/", tenantId).execute();
	}

	@Override
	public Tenant getByName(String tenantName) {
		checkNotNull(tenantName);
		return get(KeystoneTenant.class, PATH_TENANTS).param("name", tenantName).execute();
	}

	@Override
	public Tenant create(Tenant tenant) {
		checkNotNull(tenant);
		return post(KeystoneTenant.class, PATH_TENANTS).entity(tenant).execute();
	}

	@Override
	public ActionResponse delete(String tenantId) {
		checkNotNull(tenantId);
		return deleteWithResponse(PATH_TENANTS, "/", tenantId).execute();
	}

	@Override
	public Tenant update(Tenant tenant) {
		checkNotNull(tenant);
		return post(KeystoneTenant.class, uri("/tenants/%s", tenant.getId())).entity(tenant).execute();
	}

    @Override
    public List<? extends TenantUser> listUsers(String tenantId) {
        checkNotNull(tenantId);
        return get(TenantUsers.class, uri("/tenants/%s/users", tenantId)).execute().getList();
    }
}
