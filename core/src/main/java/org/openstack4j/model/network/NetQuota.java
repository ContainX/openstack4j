package org.openstack4j.model.network;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.builder.NetQuotaBuilder;

/**
 * Network quotas that are bound to a Tenant
 * 
 * @author Jeremy Unruh
 */
public interface NetQuota extends ModelEntity, Buildable<NetQuotaBuilder> {

    /**
     * Number of subnets allowed per tenant
     * 
     * @return number of subnets
     */
    int getSubnet();
    
    /**
     * Number of routers allowed per tenant
     * 
     * @return number of routers
     */
    int getRouter();
    
    /**
     * Number of ports allowed per tenant
     * 
     * @return number of ports
     */
    int getPort();
    
    /**
     * Number of networks allowed per tenant
     * 
     * @return number of networks
     */
    int getNetwork();
    
    /**
     * Number of floating IpAddresses allowed per tenant
     * 
     * @return number of float IpAddresses
     */
    int getFloatingIP();
}
