package org.openstack4j.common;

import org.openstack4j.openstack.internal.MicroVersion;

/**
 * An API decorator which is a rest consumer for APIs which support micro versions.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface MicroVersionedRestService extends RestService {
    /**
     * @return the micro version used to query the rest service
     */
    MicroVersion getMicroVersion();

    /**
     * Set the micro version to query the rest service with.
     * @param microVersion the micro version to use for queries
     */
    void setMicroVersion(MicroVersion microVersion);
}
