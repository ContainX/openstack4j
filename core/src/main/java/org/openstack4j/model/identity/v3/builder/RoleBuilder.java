package org.openstack4j.model.identity.v3.builder;

import java.util.Map;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.v3.Role;

/**
 * A Builder which creates an identity v3 role
 *
 *
 */
public interface RoleBuilder extends Builder<RoleBuilder, Role> {

    /**
     * @see Role#getId()
     */
    RoleBuilder id(String id);

    /**
     * @see Role#getName()
     */
    RoleBuilder name(String name);

    /**
     * @see Role#getLinks()
     */
    RoleBuilder links(Map<String, String> links);

    /**
     * @see Role#getLinks()
     */
	RoleBuilder domainId(String domainId);

}
