package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.networking.QoSPolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.QoSPolicy;
import org.openstack4j.model.network.options.QoSPolicyListOptions;
import org.openstack4j.openstack.networking.domain.NeutronQoSPolicy;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class QoSPolicyServiceImpl extends BaseNetworkingServices implements QoSPolicyService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends QoSPolicy> list() {
        return get(NeutronQoSPolicy.Policies.class, uri("/qos/policies")).execute().getList();
    }

    @Override
    public List<? extends QoSPolicy> list(QoSPolicyListOptions options) {
        if (options == null)
            return list();

        return get(NeutronQoSPolicy.Policies.class, uri("/qos/policies")).params(options.getOptions()).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QoSPolicy get(String policyId) {
        checkNotNull(policyId);
        return get(NeutronQoSPolicy.class, uri("/qos/policies/%s", policyId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String policyId) {
        checkNotNull(policyId);
        return deleteWithResponse(uri("/qos/policies/%s", policyId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QoSPolicy create(QoSPolicy policy) {
        checkNotNull(policy);
        return post(NeutronQoSPolicy.class, uri("/qos/policies")).entity(policy).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QoSPolicy update(QoSPolicy policy) {
        checkNotNull(policy);
        checkNotNull(policy.getId());
        QoSPolicy p = policy.toBuilder()
                .tenantId(null)
                .build();
        return put(NeutronQoSPolicy.class, uri("/qos/policies/%s", getAndClearIdentifier(p))).entity(p).execute();
    }

    private String getAndClearIdentifier(QoSPolicy policy) {
        String portId = policy.getId();
        policy.setId(null);
        return portId;
    }
}
