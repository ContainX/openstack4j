package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.PolicyRuleSet;

public interface PolicyRuleSetService {
    /**
     * List all policy rule set
     * 
     * @return List of policy rule set
     */
    List<? extends PolicyRuleSet> list();
    List<? extends PolicyRuleSet> list(Map<String, String> filteringParams);
    PolicyRuleSet get(String id);
    ActionResponse delete(String id);
    PolicyRuleSet create(PolicyRuleSet policyRuleSet);
    PolicyRuleSet update(PolicyRuleSet policyRuleSet);
}
