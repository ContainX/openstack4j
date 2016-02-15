package org.openstack4j.api.trove;

import org.openstack4j.common.RestService;

/**
 * Instance (Trove) Opererations API
 *
 * @author Craig Vyvial
 */
public interface TroveService extends RestService {
    FlavorService flavors();
    InstanceService instances();

}
