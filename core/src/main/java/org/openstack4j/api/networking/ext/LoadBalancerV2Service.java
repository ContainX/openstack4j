package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.LoadBalancerV2;

import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron Lbaas) V2 loadbalancer Extention API
 * @author emjburns
 */
public interface LoadBalancerV2Service extends RestService {
    /**
     * List all loadbalancers  that the current tenant has access to
     *
     * @return list of all loadbalancers
     */
    List<? extends LoadBalancerV2> list();

    /**
     * Returns list of loadbalancers filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of loadbalancer fitered by filteringParams
     */
    List<? extends LoadBalancerV2> list(Map<String, String> filteringParams);

    /**
     * Get the specified loadbalancer by ID
     *
     * @param loadbalancerId the loadbalancer identifier
     * @return the loadbalancer or null if not found
     */
    LoadBalancerV2 get(String loadbalancerId);

    /**
     * Delete the specified loadbalancer by ID
     * @param loadbalancerId the loadbalancer identifier
     * @return the action response
     */
    ActionResponse delete(String loadbalancerId);
    /**
     * Create a loadbalancer
     * @param loadbalancer
     * @return loadbalancer
     */
    LoadBalancerV2 create(LoadBalancerV2 loadbalancer);
    /**
     * Update a loadbalancer
     * @param loadbalancerId the loadbalancer identifier
     * @param loadbalancer LoadBalancerV2Update
     * @return loadbalancer
     */
//    TODO: implement update
//    LoadBalancerV2 update(String loadbalancerId, LoadBalancerV2Update loadbalancer);

    // TODO: loadbalancer status tree
    //      https://wiki.openstack.org/wiki/Neutron/LBaaS/API_2.0#Retrieve_a_specific_Load_Balancer.27s_Status_Tree
    // TODO: loadbalancer statistics
    //      https://wiki.openstack.org/wiki/Neutron/LBaaS/API_2.0#Retrieve_a_specific_Load_Balancer.27s_Statistics
}
