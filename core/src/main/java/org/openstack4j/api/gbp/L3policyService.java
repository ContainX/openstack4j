package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.L3Policy;

/**
 * This interface defines all methods for the manipulation of l3policy
 * 
 * @author vinod borole
 *  
 */
public interface L3policyService{
    /**
     * List all l3 policies
     * 
     * @return List of l3 policies
     */
    List<? extends L3Policy> list();
    List<? extends L3Policy> list(Map<String, String> filteringParams);
    L3Policy get(String id);
    ActionResponse delete(String id);
    L3Policy create(L3Policy l3Policy);
    L3Policy update(String l3PolicyId,L3Policy l3Policy);
}
