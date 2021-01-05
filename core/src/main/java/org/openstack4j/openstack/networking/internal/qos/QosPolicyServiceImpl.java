package org.openstack4j.openstack.networking.internal.qos;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

import org.openstack4j.api.networking.qos.NetQosPolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosPolicy;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosPolicy;
import org.openstack4j.openstack.networking.domain.qos.NeutronQosPolicy.QosPolicies;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Neutron QoS Policy Service
 *
 * @author Guoshuai Li
 *
 */
public class QosPolicyServiceImpl extends BaseNetworkingServices implements NetQosPolicyService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosPolicy> list() {
        return get(QosPolicies.class, uri("/qos/policies")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosPolicy> list(Map<String, String> filteringParams) {
        Invocation<QosPolicies> policiesInvocation = get(QosPolicies.class, "/qos/policies");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policiesInvocation = policiesInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policiesInvocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicy get(String id) {
        checkNotNull(id);
        return get(NeutronQosPolicy.class, uri("/qos/policies/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/qos/policies/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicy create(NetQosPolicy qosPolicy) {
        checkNotNull(qosPolicy);
        return post(NeutronQosPolicy.class, uri("/qos/policies")).entity(qosPolicy).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicy update(String id, NetQosPolicy qosPolicy) {
        checkNotNull(id);
        return put(NeutronQosPolicy.class, uri("/qos/policies/%s", id)).entity(qosPolicy).execute();
    }
}
