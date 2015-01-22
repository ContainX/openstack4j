package org.openstack4j.api.networking;

import org.openstack4j.api.networking.ext.HealthMonitorService;
import org.openstack4j.api.networking.ext.LbPoolService;
import org.openstack4j.api.networking.ext.MemberService;
import org.openstack4j.api.networking.ext.NetQuotaService;
import org.openstack4j.api.networking.ext.VipService;
import org.openstack4j.common.RestService;
import org.openstack4j.model.network.ext.LbPool;

/**
 * OpenStack Networking Operations API
 * 
 * @author Jeremy Unruh
 */
public interface NetworkingService extends RestService {

    /**
     * @return the Network Service API
     */
    NetworkService network();

    /**
     * @return the Subnet Service API
     */
    SubnetService subnet();

    /**
     * @return the Port Service API
     */
    PortService port();

    /**
     * @return the Router Service API
     */
    RouterService router();

    /**
     * @return the FloatingIP Service API
     */
    NetFloatingIPService floatingip();

    /**
     *
     * @return the Security Group Service API
     */
    SecurityGroupService securitygroup();

    /**
     *
     * @return the Security Group Rule Service API
     */
    SecurityGroupRuleService securityrule();

    /**
     */
    NetQuotaService quotas();
    /**
    *
    * @return the Lbaas member Service API
    */
   MemberService member();
   
   /**
    * @return the Lbaas vip Service API
    */
   VipService vip();
   /**
    * @return the Lbaas healthmonitor Service API
    */
   HealthMonitorService healthMonitor();
   
   /**
    * @return the Lbaas pool Service API
    */
   LbPoolService lbPool();

}
