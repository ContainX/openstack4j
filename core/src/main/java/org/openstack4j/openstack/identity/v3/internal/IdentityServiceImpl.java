package org.openstack4j.openstack.identity.v3.internal;

import static org.openstack4j.core.transport.ClientConstants.PATH_EXTENSIONS;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.api.identity.v3.DomainService;
import org.openstack4j.api.identity.v3.GroupService;
import org.openstack4j.api.identity.v3.IdentityService;
import org.openstack4j.api.identity.v3.PolicyService;
import org.openstack4j.api.identity.v3.ProjectService;
import org.openstack4j.api.identity.v3.RegionService;
import org.openstack4j.api.identity.v3.RoleService;
import org.openstack4j.api.identity.v3.ServiceEndpointService;
import org.openstack4j.api.identity.v3.TokenService;
import org.openstack4j.api.identity.v3.UserService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.openstack.common.ExtensionValue.ExtensionList;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Identity V3 service implementation
 *
 */
public class IdentityServiceImpl extends BaseOpenStackService implements IdentityService {

    @Override
    public DomainService domains() {
        return Apis.get(DomainService.class);
    }

    @Override
    public ProjectService projects() {
        return Apis.get(ProjectService.class);
    }

    @Override
    public UserService users() {
        return Apis.get(UserService.class);
    }

    @Override
    public RoleService roles() {
        return Apis.get(RoleService.class);
    }

    @Override
    public GroupService groups() {
        return Apis.get(GroupService.class);
    }

    @Override
    public PolicyService policies() {
        return Apis.get(PolicyService.class);
    }

    @Override
    public ServiceEndpointService serviceEndpoints() {
        return Apis.get(ServiceEndpointService.class);
    }

    @Override
    public RegionService regions() {
        return Apis.get(RegionService.class);
    }

    @Override
    public TokenService tokens() {
        return Apis.get(TokenService.class);
    }
    
    @Override
    public List<? extends Extension> listExtensions() {
        return get(ExtensionList.class, PATH_EXTENSIONS).execute().getList();
    }

}
