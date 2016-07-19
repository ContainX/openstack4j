package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.LoadBalancerV2;
import org.openstack4j.model.network.ext.builder.LoadBalancerV2Builder;

import java.util.List;

/**
 * lbaas v2 loadbalancer
 * @author emjburns
 */
@JsonRootName("load_balancer_v2")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronLoadBalancerV2 implements LoadBalancerV2 {

    private String id;

    @JsonProperty("tenant_id")
    private String tenantId;

    private String name;

    private String description;
    /**
     * The ID of the subnet on which to allocate the VIP address.
     */
    @JsonProperty("vip_subnet_id")
    private String vipSubnetId;

    /**
     * The IP address of the VIP.
     */
    private String vipAddress;

    @JsonProperty("admin_state_up")
    private boolean adminStateUp;

    // TODO: Should these be enums?
    private String provisioningStatus;
    private String operatingStatus;

    private List<Listener> listeners;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId(){
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTenantId(){
        return tenantId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVIPSubnetId(){
        return vipSubnetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVIPAddress(){
        return vipAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdminStateUp(){
        return isAdminStateUp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Listener> getListeners(){
        return listeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProvisioningStatus(){
        return provisioningStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOperatingStatus(){
        return operatingStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2Builder toBuilder(){
        //TODO: implement builder
        return null;
    }

    public static LoadBalancerV2Builder builder(){
        //TODO: implement builder
        return null;
    }

    @Override
    public String toString(){
        return "NeutronLoadBalancerV2{" +
                "id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vipSubnetId='" + vipSubnetId + '\'' +
                ", vipAddress='" + vipAddress + '\'' +
                ", adminStateUp=" + adminStateUp +
                ", provisioningStatus='" + provisioningStatus + '\'' +
                ", operatingStatus='" + operatingStatus + '\'' +
                ", listeners=" + listeners +
                '}';
    }
}
