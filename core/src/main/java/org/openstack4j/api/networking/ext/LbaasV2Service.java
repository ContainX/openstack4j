package org.openstack4j.api.networking.ext;

/**
 * LBaaS (Load Balancer as a Service) V2 support
 */
public interface LbaasV2Service {

    /**
     * @return the Lbaas loadbalancer Service API
     */
    LoadBalancerV2Service loadbalancerV2();

    /**
     * @return the Lbaas healthmonitor V2 Service API
     */
    HealthMonitorV2Service healthMonitorV2();

    /**
     * @return the Lbaas pool Service API
     */
    LbPoolV2Service lbPoolV2();

    /**
     * @return the Lbaas V2 listener Service API
     */
    ListenerService listener();
}
