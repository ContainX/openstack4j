package org.openstack4j.model.network;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.BandwidthLimitRuleBuilder;
import org.openstack4j.model.network.builder.DscpMarkingRuleBuilder;
import org.openstack4j.model.network.builder.MinimumBandwidthRuleBuilder;
import org.openstack4j.model.network.builder.QoSPolicyBuilder;

import java.util.List;
import java.util.Set;

public interface QoSPolicy extends Resource, Buildable<QoSPolicyBuilder> {
    String getDescription();
    String getProjectId();
    int getRevisionNumber();
    boolean isShared();
    List<? extends Rule> getRules();
    boolean isDefault();
    String getCreatedAt();
    String getUpdatedAt();

    interface Rule extends Resource {
        String getType();
        Set<String> getTags();
    }

    interface BandwidthLimitRule extends Rule, Buildable<BandwidthLimitRuleBuilder> {
        Integer getMaxKbps();
        Integer getMaxBurstKbps();
        String getDirection();
    }

    interface DscpMarkingRule extends Rule, Buildable<DscpMarkingRuleBuilder> {
        Integer getDscpMark();
    }

    interface MinimumBandwidthRule extends Rule, Buildable<MinimumBandwidthRuleBuilder> {
        Integer getMinKbps();
        String getDirection();
    }
}
