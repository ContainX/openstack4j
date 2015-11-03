package org.openstack4j.openstack.identity.internal.v3;

import org.openstack4j.api.identity.v3.UserService;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.identity.domain.v3.KeystoneUser.Users;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Identity User based Operations
 * 
 */
public class UserServiceImpl extends BaseOpenStackService implements UserService {
	
	@Override
	public List<? extends User> getByName(String userName) {
	    checkNotNull(userName);
	    return get(Users.class, "/users").param("name", userName).execute().getList();
	}

}
