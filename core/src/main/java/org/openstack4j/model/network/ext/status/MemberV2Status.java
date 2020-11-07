package org.openstack4j.model.network.ext.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.networking.domain.ext.LoadBalancerV2StatusTree.NeutronMemberV2Status;

/**
 * The status of an lbaas v2 member
 *
 * @author emjburns
 */
@JsonDeserialize(as = NeutronMemberV2Status.class)
public interface MemberV2Status extends ModelEntity {
    /**
     * The id of the member
     *
     * @return id
     */
    String getId();

    /**
     * The address of the memeber
     *
     * @return address
     */
    String getAddress();

    /**
     * The protocol port of the member
     *
     * @return protocol port
     */
    Integer getProtocolPort();

    /**
     * The operating status of the member
     *
     * @return operating status
     */
    String getOperatingStatus();

    /**
     * The provisioning status of the member
     *
     * @return provisioning status
     */
    String getProvisioningStatus();
}
