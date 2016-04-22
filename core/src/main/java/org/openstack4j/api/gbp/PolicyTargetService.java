package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.PolicyTarget;

/**
 * This interface defines all methods for the manipulation of policy targets
 * 
 * @author vinod borole
 * 
 */
public interface PolicyTargetService{
    /**
     * List all policy target
     * 
     * @return List of policy target
     */
    List<? extends PolicyTarget> list();
    List<? extends PolicyTarget> list(Map<String, String> filteringParams);
    PolicyTarget get(String id);
    ActionResponse delete(String id);
    PolicyTarget create(PolicyTarget policyTarget);
    PolicyTarget update(String policyTargetId,PolicyTarget policyTarget);
}
