package org.openstack4j.model.network;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.TrunkBuilder;
import org.openstack4j.openstack.networking.domain.NeutronSubPort;

/**
 * A network Trunk
 *
 * @author Kashyap Jha
 */
public interface Trunk extends Resource, Buildable<TrunkBuilder> {

    /**
     * @return the current state of the trunk
     */
    State getState();

    /**
     * @return the administrative state of the trunk
     */
    boolean isAdminStateUp();

    /**
     * @return the parent port ID of the trunk
     */
    String getParentPort();

    /**
     * @return a list of subports associated with the trunk
     */
    List<NeutronSubPort> getSubPorts();

    /**
     * @return a string containing the description
     */
    String getDescription();

}
