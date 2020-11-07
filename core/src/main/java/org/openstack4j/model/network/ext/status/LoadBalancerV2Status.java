package org.openstack4j.model.network.ext.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree.NeutronLoadBalancerV2Status;

import java.util.List;

/**
 * The status of an lbaas v2 loadbalancer
 *
 * @author emjburns
 */
@JsonDeserialize(as = NeutronLoadBalancerV2Status.class)
public interface LoadBalancerV2Status extends ModelEntity {
    /**
     * The id of the loadbalancer
     *
     * @return id
     */
    String getId();

    /**
     * The name of the loadbalancer
     *
     * @return name
     */
    String getName();

    /**
     * The operating status of the loadbalancer
     *
     * @return operating status
     */
    String getOperatingStatus();

    /**
     * The provisioning status of the loadbalancer
     *
     * @return provisioning status
     */
    String getProvisioningStatus();

    /**
     * The listeners associated with the loadbalancer
     *
     * @return list of listener statuses
     */
    List<ListenerV2Status> getListenerStatuses();
}
