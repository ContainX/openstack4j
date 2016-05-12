package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.PolicyAction;
import org.openstack4j.model.gbp.PolicyActionUpdate;

/**
 * This interface defines all methods for the manipulation of policy actions
 * 
 * @author vinod borole
 * 
 */
public interface PolicyActionService {
    /**
     * List all policy actions
     * 
     * @return List of policy actions
     */
    List<? extends PolicyAction> list();
    List<? extends PolicyAction> list(Map<String, String> filteringParams);
    PolicyAction get(String id);
    ActionResponse delete(String id);
    PolicyAction create(PolicyAction policyAction);
    PolicyAction update(String policyActionId,PolicyActionUpdate policyAction);
}
