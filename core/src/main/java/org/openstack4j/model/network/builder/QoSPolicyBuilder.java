package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.QoSPolicy;
import org.openstack4j.model.network.QoSPolicy.Rule;

import java.util.List;

public interface QoSPolicyBuilder extends Builder<QoSPolicyBuilder, QoSPolicy> {
    QoSPolicyBuilder description(String description);
    QoSPolicyBuilder tenantId(String tenantId);
    QoSPolicyBuilder projectId(String projectId);
    QoSPolicyBuilder revisionNumber(int revisionNumber);
    QoSPolicyBuilder shared(boolean shared);
    QoSPolicyBuilder rules(List<? extends Rule> rules);
    QoSPolicyBuilder id(String id);
    QoSPolicyBuilder isDefault(boolean isDefault);
    QoSPolicyBuilder name(String name);
    QoSPolicyBuilder createdAt(String createdAt);
    QoSPolicyBuilder updatedAt(String updatedAt);
}
