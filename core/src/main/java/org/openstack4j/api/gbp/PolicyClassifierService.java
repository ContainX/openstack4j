package org.openstack4j.api.gbp;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.PolicyClassifier;

/**
 * This interface defines all methods for the manipulation of policy classifiers
 * 
 * @author vinod borole
 * 
 */
public interface PolicyClassifierService {
    /**
     * List all policy classifier
     * 
     * @return List of policy classifier
     */
    List<? extends PolicyClassifier> list();
    List<? extends PolicyClassifier> list(Map<String, String> filteringParams);
    PolicyClassifier get(String id);
    ActionResponse delete(String id);
    PolicyClassifier create(PolicyClassifier policyClassifier);
    PolicyClassifier update(String policyClassifierId,PolicyClassifier policyClassifier);
}
