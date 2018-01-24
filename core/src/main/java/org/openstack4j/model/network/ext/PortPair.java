package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;

/**
 * A Port Pair Entity.
 *
 * @author Dmitry Gerenrot.
 *
 */
public interface PortPair extends Resource, Buildable<PortPairBuilder> {

    /**
     * @return id : Port Pair  identifer
     */
    String getId();

    /**
     * @return name : Human readable name for the port pair
     */
    String getName();

    /**
     * @return description : Human readable description for the port pair
     */
    String getDescription();

    /**
     * @return description : Id for the egress port
     */
    String getEgressId();

    /**
     * @return description : Id for the ingress port
     */
    String getIngressId();
}

