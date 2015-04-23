package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;

/**
 * OpenStack Firewall As a Service (FwaaS) Operations API
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallAsService extends RestService {

    /**
     * OpenStack Firewall As a Service <code>(FwaaS) : Firewall</code> Operations API
     * 
     * @return the Firewall Service API
     */
    FirewallService firewall();
    
    /**
     * @return the Firewall Service API
     */
    //FirewallRuleService firewallrule();
    
    /**
     * @return the Firewall Service API
     */
    //FirewallPolicyService firewallpolicy();
}
