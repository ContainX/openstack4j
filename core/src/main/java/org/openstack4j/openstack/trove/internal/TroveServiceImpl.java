package org.openstack4j.openstack.trove.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.trove.InstanceFlavorService;
import org.openstack4j.api.trove.DatabaseService;
import org.openstack4j.api.trove.DatastoreService;
import org.openstack4j.api.trove.InstanceService;
import org.openstack4j.api.trove.TroveService;
import org.openstack4j.api.trove.UserService;

/**
 * Trove API Implementation
 *
 * @author sumit gandhi
 */
public class TroveServiceImpl extends BaseTroveServices implements TroveService {

    /**
     * {@inheritDoc}
     */
    @Override
    public DatastoreService datastoreService() {
        return Apis.get(DatastoreService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseService databaseService() {
        return Apis.get(DatabaseService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserService databaseUsersService() {
        return Apis.get(UserService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstanceFlavorService flavorService() {
        return Apis.get(InstanceFlavorService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstanceService instanceService() {
        return Apis.get(InstanceService.class);
    }

}
