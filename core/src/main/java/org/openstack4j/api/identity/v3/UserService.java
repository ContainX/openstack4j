package org.openstack4j.api.identity.v3;

import org.openstack4j.model.identity.v3.User;

import java.util.List;

/**
 * Identity User based Operations
 *
 */
public interface UserService {

    /**
     * Gets detailed information about a specified user by name
     *
     * @param userName the user name
     * @return the user
     */
    List<? extends User> getByName(String userName);

}
