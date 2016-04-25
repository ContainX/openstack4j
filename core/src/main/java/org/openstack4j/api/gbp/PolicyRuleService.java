package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.PolicyRule;

/**
 * This interface defines all methods for the manipulation of policy rule
 * 
 * @author vinod borole
 * 
 */
public interface PolicyRuleService {
    List<? extends PolicyRule> list();
    List<? extends PolicyRule> list(Map<String, String> filteringParams);
    PolicyRule get(String id);
    ActionResponse delete(String id);
    PolicyRule create(PolicyRule policyRule);
    PolicyRule update(PolicyRule policyRule);
}
