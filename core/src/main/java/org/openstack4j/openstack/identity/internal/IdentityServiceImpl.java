package org.openstack4j.openstack.identity.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.*;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.openstack.common.ExtensionValue;
import org.openstack4j.openstack.identity.domain.KeystoneEndpoint.Endpoints;
import org.openstack4j.openstack.internal.BaseOpenStackService;
import org.openstack4j.openstack.internal.OSClientSession;

import java.util.List;

import static org.openstack4j.core.transport.ClientConstants.PATH_EXTENSIONS;

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
		return get(ExtensionValue.ExtensionList.class, PATH_EXTENSIONS).execute().getList();
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
