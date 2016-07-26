package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.LoadBalancerV2Builder;
import org.openstack4j.openstack.networking.domain.ext.ListItem;

import java.util.List;

public interface LoadBalancerV2  extends ModelEntity, Buildable<LoadBalancerV2Builder> {
    /**
     * @return id. The unique ID for the loadbalancer.
     */
    String getId();

    /**
     * @return tenantId.
     * Owner of the loadbalancer. Only an administrative user can specify a tenant ID other than its own.
     */
    String getTenantId();

    /**
     * @return loadbalancer name. Does not have to be unique.
     */
    String getName();

    /**
     * @return Description for the loadbalancer.
     */
    String getDescription();

    /**
     * @return The VIP subnet id of the loadbalancer.
     */
    String getVIPSubnetId();

    /**
     * @return The VIP address of the loadbalancer.
     */
    String getVIPAddress();

    /**
     * @return The administrative state of the loadbalancer, which is up (true) or
     *         down (false).
     */
    boolean isAdminStateUp();

    /**
     * @return The listeners of the loadbalancer.
     */
    List<ListItem> getListeners();

    /**
     * @return provisioningStatus.The provisioning status of the loadbalancer. Indicates whether the
     *         loadbalancer is provisioning.
     *         Either ACTIVE, PENDING_CREATE or ERROR.
     */
    String getProvisioningStatus();

    /**
     * @return operatingStatus.The operating status of the loadbalancer. Indicates whether the
     *         loadbalancer is operational.
     */
    String getOperatingStatus();
}
