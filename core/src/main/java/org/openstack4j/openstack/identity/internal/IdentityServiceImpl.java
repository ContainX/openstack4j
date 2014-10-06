package org.openstack4j.openstack.identity.internal;

import static org.openstack4j.core.transport.ClientConstants.PATH_EXTENSIONS;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.identity.RoleService;
import org.openstack4j.api.identity.ServiceManagerService;
import org.openstack4j.api.identity.TenantService;
import org.openstack4j.api.identity.UserService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.openstack.common.ExtensionValue.Extensions;
import org.openstack4j.openstack.identity.domain.KeystoneEndpoint.Endpoints;
import org.openstack4j.openstack.internal.BaseOpenStackService;
import org.openstack4j.openstack.internal.OSClientSession;

public class IdentityServiceImpl extends BaseOpenStackService implements IdentityService {

	@Override
	public TenantService tenants() {
		return Apis.get(TenantService.class);
	}

	@Override
	public UserService users() {
		return Apis.get(UserService.class);
	}

	@Override
	public List<? extends Extension> listExtensions() {
		return get(Extensions.class, PATH_EXTENSIONS).execute().getList();
	}

	@Override
	public RoleService roles() {
		return Apis.get(RoleService.class);
	}

	@Override
	public ServiceManagerService services() {
		return Apis.get(ServiceManagerService.class);
	}

	@Override
	public List<? extends Endpoint> listTokenEndpoints() {
		return get(Endpoints.class, uri("/tokens/%s/endpoints", OSClientSession.getCurrent().getTokenId())).execute().getList();
	}
	
}
