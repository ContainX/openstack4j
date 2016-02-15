package org.openstack4j.api.trove;

import org.openstack4j.model.trove.Flavor;

import java.util.List;

/**
 *
 * @author Craig Vyvial
 */
public interface FlavorService {
    List<? extends Flavor> list();
}
