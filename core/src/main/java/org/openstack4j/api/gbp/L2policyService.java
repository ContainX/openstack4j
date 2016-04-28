package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.L2Policy;

/**
 * This interface defines all methods for the manipulation of l2policy
 * 
 * @author vinod borole
 * 
 */
public interface L2policyService{
    /**
     * List all l2 policies
     * 
     * @return List of l2 policies
     */
    List<? extends L2Policy> list();
    List<? extends L2Policy> list(Map<String, String> filteringParams);
    L2Policy get(String id);
    ActionResponse delete(String id);
    L2Policy create(L2Policy l2Policy);
    L2Policy update(String l2PolicyId,L2Policy l2Policy);
}
