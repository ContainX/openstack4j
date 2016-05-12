package org.openstack4j.openstack.gbp.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.gbp.PolicyClassifierService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.gbp.PolicyClassifier;
import org.openstack4j.model.gbp.PolicyClassifierUpdate;
import org.openstack4j.openstack.gbp.domain.GbpPolicyClassifier;
import org.openstack4j.openstack.gbp.domain.GbpPolicyClassifier.PolicyClassifiers;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

public class PolicyClassifierServiceImpl extends BaseNetworkingServices implements PolicyClassifierService {

    @Override
    public List<? extends PolicyClassifier> list() {
        return get(PolicyClassifiers.class, uri("/grouppolicy/policy_classifiers")).execute().getList();
    }

    @Override
    public List<? extends PolicyClassifier> list(Map<String, String> filteringParams) {
        Invocation<PolicyClassifiers> policyclassifierInvocation = buildInvocation(filteringParams);
        return policyclassifierInvocation.execute().getList();
    }
    
    private Invocation<PolicyClassifiers> buildInvocation(Map<String, String> filteringParams) {
        Invocation<PolicyClassifiers> policyclassifierInvocation = get(PolicyClassifiers.class, "/grouppolicy/policy_classifiers");
        if (filteringParams == null) { 
            return policyclassifierInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policyclassifierInvocation = policyclassifierInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policyclassifierInvocation;
    }

    @Override
    public PolicyClassifier get(String id) {
        checkNotNull(id);
        return get(GbpPolicyClassifier.class, uri("/grouppolicy/policy_classifiers/%s", id)).execute();
    }

    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/policy_classifiers/%s", id)).execute();
    }

    @Override
    public PolicyClassifier create(PolicyClassifier policyClassifier) {
        return post(GbpPolicyClassifier.class, uri("/grouppolicy/policy_classifiers")).entity(policyClassifier).execute();
    }

    @Override
    public PolicyClassifier update(String policyClassifierId, PolicyClassifierUpdate policyClassifier) {
        checkNotNull(policyClassifierId);
        checkNotNull(policyClassifier);
        return put(GbpPolicyClassifier.class, uri("/grouppolicy/policy_classifiers/%s", policyClassifierId)).entity(policyClassifier).execute();
    }

}
