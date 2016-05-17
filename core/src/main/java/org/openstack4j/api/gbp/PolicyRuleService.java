package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.PolicyRule;

/**
 * This interface defines all methods for the manipulation of policy rule
 * 
 * @author vinod borole
 *  
 */
public interface PolicyRuleService {
    /**
     * List all policy rules
     * 
     * @return List of policy rules
     */
    List<? extends PolicyRule> list();
    List<? extends PolicyRule> list(Map<String, String> filteringParams);
    PolicyRule get(String id);
    ActionResponse delete(String id);
    PolicyRule create(PolicyRule policyRule);
    PolicyRule update(String policyRuleId,PolicyRule policyRule);
}
