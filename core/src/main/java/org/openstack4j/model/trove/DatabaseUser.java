package org.openstack4j.model.trove;

import org.openstack4j.openstack.trove.domain.TroveDatabase;

import java.util.List;

/**
 * Database User Model Entity
 *
 * @author sumit gandhi
 */
public interface DatabaseUser {

    String getUsername();
    List<TroveDatabase> getTroveDatabaseList();
}
