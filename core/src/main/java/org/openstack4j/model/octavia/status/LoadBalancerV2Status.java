package org.openstack4j.model.octavia.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.octavia.domain.LoadBalancerV2StatusTree.OctaviaLoadBalancerV2Status;

import java.util.List;

/**
 * The status of an lbaas v2 loadbalancer
 *
 * @author wei
 */
@JsonDeserialize(as = OctaviaLoadBalancerV2Status.class)
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
