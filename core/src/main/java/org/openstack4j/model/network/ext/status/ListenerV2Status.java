package org.openstack4j.model.network.ext.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree.NeutronListenerV2Status;

import java.util.List;

@JsonDeserialize(as = NeutronListenerV2Status.class)
public interface ListenerV2Status extends ModelEntity {
    /**
     * The name of the listener
     *
     * @return name
     */
    String getName();

    /**
     * The id of the listener
     *
     * @return id
     */
    String getId();

    /**
     * The operating status of the listener
     *
     * @return operating status
     */
    String getOperatingStatus();

    /**
     * The provisioning status of the listener
     *
     * @return provisioning status
     */
    String getProvisioningStatus();

    /**
     * The statuses of the pools associated with this listener
     *
     * @return list of the status of the pools
     */
    List<LbPoolV2Status> getLbPoolV2Statuses();
}
